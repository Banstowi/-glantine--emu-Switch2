# Banst Family : pistes à explorer

## Build PGO (optimisation guidée par un profil)

- Le compilateur optimise l'émulateur d'après un profil d'utilisation réel. Gain général attendu
  de quelques pourcents ; peu d'effet sur les saccades de la première partie.
- La CI sait déjà le produire : lancer `ci.yml` avec l'entrée `pgo` sur `both`. L'archive est
  `banstdog-windows-x86_64-pgo.zip`.
- Jamais essayé chez nous. Points à vérifier avant de le donner à la famille :
  - le profil vient d'Eglantine, pas de notre code actuel, donc le gain peut être plus faible ;
  - il est compilé avec MinGW clang au lieu de MSVC : comparer le rendu sur Zelda ;
  - la mise à jour automatique n'est activée que dans le build MSVC.
- Essai prévu : sur un seul PC, comparer les images par seconde et les saccades avec le build
  standard dans la même scène.

## Saccades de Zelda Ocarina of Time : mesurer avant et après

Lancer avec `EDEN_NX2_STUTTER_TRACE=1`, jouer le même trajet, fermer l'émulateur, puis lire
`eden_log.txt` avec `python3 -I tools/nx2/cache_metrics.py parse <log>` (ou `compare <a> <b>`).

Repères du build-12 sur le PC i5-14400F / RTX 4070 SUPER (7 minutes de jeu) :

| Réglages | Image médiane | Images > 33 ms | Images > 100 ms |
|---|---|---|---|
| Ancien profil (précision GPU haute) | 31,5 ms | 5021 sur 10841 | 91 |
| Réglages recommandés | 20,5 ms | 950 sur 18446 | 61 |

Ce qui reste, dans l'ordre :

1. Changements de zone : jusqu'à 3,6 s d'image figée. Allocation de mémoire vidéo et création
   d'images (environ 1 s), envoi de textures (environ 1 s), traduction de pipelines (0,6 s).
2. Traduction des pipelines en jeu : 513 pipelines, 5,2 s sur le fil GPU, soit 10 ms chacun.
   La ligne `Runtime pipelines in the last 10 s` du journal donne ce temps par tranche de 10 s.
3. Vitesse de fond : le fil GPU reste le frein dans les zones chargées.

Traduction parallèle : `EDEN_NX2_PARALLEL_POOL_KIB=1024` remet l'ancienne limite (Zelda traduit
alors tout sur le fil GPU), `0` coupe la traduction parallèle. Sans la variable, la limite est de
4 Mio. Dans le journal, « ms on the GPU thread waiting for ... stage translation on workers »
doit devenir non nul quand elle sert.

Mesure du build-13 (même PC, trajets différents) : 9,8 ms par pipeline sur le fil GPU avec
l'ancienne limite, 7,3 ms avec la traduction parallèle. Le fil GPU attend encore la traduction
(4,5 s d'attente pour 6,2 s de travail en 6 minutes, 213 pipelines en 10 s à la découverte d'un
village).

## Traduction asynchrone (activée par défaut depuis le build-15)

Portage du correctif d'Eglantine 0f1ae997c2 (branche `fix/oot-map-change`), adapté à Lichii.
Elle sert avec les shaders asynchrones et le mode Bounded ou Skip ; `EDEN_NX2_ASYNC_TRANSLATION=0`
la coupe pour comparer.

Un nouveau pipeline natif dont le dessin peut être sauté n'est plus traduit sur le fil GPU. Le
fil GPU copie ce que la traduction lit (tampons constants, table de textures, quelques
registres), puis la traduction et la compilation partent sur les fils de `shader_jobs`. Le
dessin attend dans la limite du budget par image (`native_pipeline_wait_ms`), puis est sauté ;
un dessin suivant récupère le pipeline et l'enregistre dans le cache.

Différences avec Eglantine :

- La table de textures est copiée au lieu d'être relue plus tard : la traduction voit l'état du
  moment du dessin.
- Les mots des tampons constants qui contiennent une adresse GPU valide sont repérés sur le fil
  GPU. La traduction ne consulte donc jamais la mémoire du jeu depuis un autre fil.
- Un shader qui lit un pointeur à travers un autre pointeur ne peut pas être traduit ainsi : son
  résultat est jeté, il est retraduit sur le fil GPU, et ses pipelines suivants y vont directement.
- Le travail passe par la file `shader_jobs` de Lichii, pas par des fils dédiés.

À lire dans le journal, toutes les 10 s : `Async translation in the last 10 s: N queued (...),
N not detached, N adopted (...), N sent back to the GPU thread, N ready within the wait, N
lookups skipped, ... pending`. À comparer avec `Runtime pipelines in the last 10 s` et avec les
lignes `Nx2Stutter` du même trajet sans la variable.

À surveiller : objets absents plus longtemps qu'avant, végétation ou textures fausses (les
pipelines « sent back » devraient les couvrir), plantage à la fermeture.

Mesure du build-14 avec `EDEN_NX2_ASYNC_TRANSLATION=1` (même PC, 5 minutes, autre trajet) :

| | Build-13, traduction parallèle | Build-14, traduction asynchrone |
|---|---|---|
| Images de plus de 33 ms | 1552 sur 13230 (11,7 %) | 467 sur 14229 (3,3 %) |
| Temps de construction sur le fil GPU | 4,9 s | 1,5 s |
| Image médiane | 24,5 ms | 20,5 ms |

196 pipelines traduits hors du fil GPU (1,1 ms de préparation chacun, 5,3 s de travail sur les
autres cœurs), aucun renvoyé au fil GPU, aucun échec. 193 restent traduits sur le fil GPU
(6,1 ms chacun) : dessins qui ne peuvent pas être sautés, et 38 environnements non détachés.
Les images de plus d'une seconde qui restent viennent des textures aux chargements.
