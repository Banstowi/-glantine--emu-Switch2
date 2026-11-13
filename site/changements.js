// Versions (GitHub releases, with their notes) and the latest commits of the default branch.
const tabs = { releases: document.getElementById("tab-releases"), commits: document.getElementById("tab-commits") };
const loaded = new Set();

function show(name) {
  for (const [key, tab] of Object.entries(tabs)) {
    tab.setAttribute("aria-selected", String(key === name));
    document.getElementById(key).hidden = key !== name;
  }
  if (!loaded.has(name)) {
    loaded.add(name);
    (name === "releases" ? loadReleases : loadCommits)();
  }
  history.replaceState(null, "", name === "commits" ? "#code" : "#");
}

function fail(section, error) {
  section.replaceChildren(el("p", { class: "status error" }, "Impossible de charger : " + error.message));
}

async function loadReleases() {
  const section = document.getElementById("releases");
  try {
    const releases = (await getJson("/releases?per_page=15")).filter((release) => !release.draft);
    if (releases.length === 0) {
      section.replaceChildren(el("p", { class: "status" }, "Aucune version publiée pour l'instant."));
      return;
    }
    section.replaceChildren(...releases.map((release) => {
      const notes = el("div", { class: "notes" });
      notes.innerHTML = DOMPurify.sanitize(marked.parse(release.body || "Pas de notes pour cette version."));
      return el("article", { class: "card release" },
        el("header", {},
          el("h3", {}, el("a", { href: release.html_url }, release.name || release.tag_name)),
          el("span", { class: "meta" }, formatDate(release.published_at))),
        notes);
    }));
  } catch (error) {
    fail(section, error);
  }
}

async function loadCommits() {
  const section = document.getElementById("commits");
  try {
    const commits = await getJson("/commits?per_page=40");
    const list = el("ul", { class: "commits card" });
    for (const commit of commits) {
      const [title, ...rest] = commit.commit.message.split("\n");
      // The body without its trailers (Co-Authored-By, Signed-off-by...)
      const body = rest.join("\n").replace(/^(Co-Authored-By|Claude-Session|Signed-off-by): .*$/gim, "").trim();
      const item = el("li", {},
        el("div", { class: "title" }, el("a", { href: commit.html_url }, title)),
        el("div", { class: "meta" }, formatDate(commit.commit.author.date) + " · " + commit.sha.slice(0, 7)));
      if (body) {
        const details = el("details", {}, el("summary", { class: "meta" }, "Détails"));
        details.append(el("p", { style: "white-space: pre-line; margin: 8px 0 0" }, body));
        item.append(details);
      }
      list.append(item);
    }
    section.replaceChildren(list,
      el("p", { class: "meta" }, el("a", { href: "https://github.com/" + REPO + "/commits" }, "Tout l'historique sur GitHub")));
  } catch (error) {
    fail(section, error);
  }
}

tabs.releases.addEventListener("click", () => show("releases"));
tabs.commits.addEventListener("click", () => show("commits"));
show(location.hash === "#code" ? "commits" : "releases");
