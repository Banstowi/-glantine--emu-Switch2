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

Étape suivante si le point 2 reste gênant : porter la traduction asynchrone d'Eglantine
(0f1ae997c2, branche `fix/oot-map-change`), où le fil GPU n'attend plus la traduction.
