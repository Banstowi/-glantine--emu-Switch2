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
