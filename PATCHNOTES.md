# Notes de version de Banst Family

La première section ci-dessous est affichée dans la fenêtre de mise à jour et sur la page de la
version. Les sections suivantes gardent l'historique.

## Nouveautés de cette version

**Interface**
- L'interface est maintenant vraiment en français. Si elle reste en anglais, choisissez Français
  dans Emulation → Configure → General → Interface.

**The Legend of Zelda: Ocarina of Time**
- Les gels de 2 à 3 secondes à la première apparition de certains effets sont nettement réduits.
- Image plus nette et moins de scintillement grâce à AMD FSR 3.1. FSR 4 est proposé en option
  sur les cartes AMD récentes.
- Correction d'un plantage par manque de mémoire.
- Correction du grésillement du son de l'ocarina.

**Tous les jeux**
- Au premier lancement d'un jeu, Banst Family propose de précompiler ses shaders : quelques
  minutes d'attente une seule fois, puis beaucoup moins de saccades en jouant.
- Nouveau cache de shaders, plus rapide à charger. Il se reconstruit une fois : quelques
  saccades sont normales à la première partie après cette mise à jour.
- Rendu plus rapide (Donkey Kong Bananza gagne environ 25 %).
- Mario Kart World : éclairage sombre et routes corrigés.
- Moins de plantages sur les cartes Intel Arc et quand la mémoire vidéo est pleine.
- Nouveaux réglages DLSS (qualité et modèle).
- Prise en charge du format de jeux `.nx`.

**Mises à jour**
- Un téléchargement interrompu est retenté automatiquement.
- Cette fenêtre affiche désormais des notes lisibles.

## build-9

- Correction de la mise à jour automatique.
