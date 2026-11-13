// Turns the form into a prefilled "new issue" page on GitHub.
const form = document.getElementById("report");
const game = document.getElementById("game");
const gameOther = document.getElementById("game-other");

game.addEventListener("change", () => {
  gameOther.hidden = game.value !== "other";
  if (!gameOther.hidden) {
    gameOther.focus();
  }
});

form.addEventListener("submit", (event) => {
  event.preventDefault();
  const value = (id) => document.getElementById(id).value.trim();
  const gameName = game.value === "other" ? value("game-other") : game.value;
  const section = (heading, text) => (text ? "### " + heading + "\n\n" + text + "\n\n" : "");
  const body =
    section("Jeu", gameName || "Aucun en particulier") +
    section("Type de problème", value("kind")) +
    section("Version d'Erable", value("version") || "Non précisée") +
    section("PC", value("pc")) +
    section("Ce qui se passe", value("what")) +
    section("Où et quand", value("where")) +
    "### Journal\n\n_Glissez ici `eden_log.txt` (dossier `%APPDATA%\\erable\\log`, Erable fermé)._\n";
  const title = (gameName ? "[" + gameName + "] " : "") + value("title");
  const url = "https://github.com/" + REPO + "/issues/new?" +
    new URLSearchParams({ title, body }).toString();
  window.open(url, "_blank", "noopener");
});
