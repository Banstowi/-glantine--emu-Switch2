# Notes de version de Banst Family

La première section ci-dessous est affichée dans la fenêtre de mise à jour et sur la page de la
version. Les sections suivantes gardent l'historique.

## Nouveautés de cette version

**Nouvelle interface**
- Thème Banst Family : sombre, gris chauds et couleur érable de notre feuille. Il s'applique au
  premier lancement ; les anciens thèmes restent dans Configurer > Général > Interface.
- Liste de jeux redessinée, avec favoris, et fenêtres de réglages qui tiennent à l'écran.
- Une fenêtre « Quoi de neuf » présente les changements (Aide > Quoi de neuf).

**Jeu à plusieurs**
- Jeu en réseau local : les jeux en sans-fil local peuvent passer par le réseau de la maison,
  sans salon. Désactivé par défaut : Configurer > Réseau > LAN play.

**Performances**
- Les textures récentes restent en mémoire tant que la carte graphique a de la place, au lieu
  d'être jetées puis recréées.
- Le processeur travaille moins quand le jeu attend.

**Moins de saccades à la découverte d'une zone**
- Les nouveaux shaders sont préparés sur les autres cœurs du processeur pendant que le jeu
  continue. Sur nos mesures dans Zelda Ocarina of Time, les saccades sont trois fois moins
  nombreuses. Un objet peut apparaître avec un très léger retard la première fois.

**Discord**
- Banst Family affiche le jeu en cours dans votre statut Discord. Pour ne pas l'afficher,
  décochez « Afficher le jeu en cours dans le Statut Discord » dans
  Émulation → Configurer → Général → Web.

**The Legend of Zelda: Ocarina of Time**
- Correction du gel dans la maison des gardes.
- Revenir dans une zone déjà visitée ne retraduit plus ses shaders : ils sont gardés en mémoire.

**Tous les jeux**
- Les shaders construits restent disponibles tant que la mémoire du PC le permet, au lieu d'être
  jetés après quelques minutes.
- Sortie HDR native (Off, Auto, On).
- Nouvel affichage des statistiques : images par seconde, charge du processeur et de la carte
  graphique, graphique des 5 dernières secondes.
- Rendu et émulation du processeur allégés sur plusieurs points.
- Yoshi and the Mysterious Book : le monde 3D s'affiche.

## build-13

- Banst Family applique ses propres réglages recommandés par défaut : précision GPU basse,
  présentation asynchrone, décodage vidéo par la carte graphique et shaders compilés en
  arrière-plan. Sur nos PC, Zelda Ocarina of Time passe d'environ 25 à 40-55 images par seconde.
- Ils s'appliquent tout seuls aux réglages jamais modifiés. Le menu Aide → Appliquer les
  réglages recommandés reste disponible, avec retour en arrière.

## build-12

**The Legend of Zelda: Ocarina of Time**
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
- Mario Kart World : éclairage sombre corrigé.
- L'émulateur ne plante plus quand la mémoire vidéo est pleine.
- Nouveaux réglages DLSS (qualité et modèle).
- Prise en charge du format de jeux `.nx`.

**Mises à jour**
- Un téléchargement interrompu est retenté automatiquement.
- Cette fenêtre affiche désormais des notes lisibles.

## build-10

- L'interface est vraiment en français. Si elle reste en anglais, choisissez Français dans
  Emulation → Configure → General → Interface.
- Zelda Ocarina of Time : les gels de 2 à 3 secondes à la première apparition de certains effets
  sont nettement réduits.
- Mario Kart World : routes corrigées.
- Moins de plantages sur les cartes Intel Arc.

## build-9

- Correction de la mise à jour automatique.
