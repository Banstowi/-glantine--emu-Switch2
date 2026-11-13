<p align="center">
  <img src="dist/lichii.png" alt="Banst Family" width="160">
</p>

# SPDX-FileCopyrightText: 2018 yuzu Emulator Project
# Banst Family

Banst Family est l'émulateur Nintendo Switch 2 de la famille, en français, avec ses propres
réglages, son thème et ses optimisations pour nos jeux, en particulier The Legend of Zelda:
Ocarina of Time.

Il n'est pas affilié à Nintendo. Utilisez uniquement des jeux, des clés et un firmware tirés de
votre propre console : rien de tout cela n'est fourni.

## Télécharger

Téléchargez Banst Family uniquement depuis la
[page des versions](https://github.com/Banstowi/-glantine--emu-Switch2/releases) de ce dépôt.
Sous Windows, extrayez tout le zip puis lancez **banstdog.exe**. Les mises à jour sont ensuite
proposées automatiquement au démarrage, avec leurs notes de version en français.

## Tutoriels

- [Installer Banst Family](docs/tutoriel/index.html) : installation, firmware et clés de votre
  console, jeux en français, manette.
- [Optimiser Zelda Ocarina of Time](docs/tutoriel/zelda-ocarina.html) : réglages, DLSS,
  précompilation des shaders et partage du cache entre PC.
- [Raccourcis](raccourcis.md) : dossiers utiles, lanceur de diagnostic et menus.

## Vos données

Banst Family garde ses données dans `%APPDATA%\banstdog` : réglages, sauvegardes, cache de
shaders, journaux et rapports de compatibilité.

## Ce que Banst Family ajoute

- Interface en français et thème Banst Family.
- Réglages par défaut choisis pour nos PC.
- Traduction des nouveaux shaders hors du fil graphique, pour moins de saccades à la découverte
  d'une zone.
- Précompilation des shaders proposée au premier lancement d'un jeu.
- Rapports de compatibilité enregistrés sur le PC et envoyables par e-mail.
- Mises à jour automatiques depuis ce dépôt, notes de version lisibles et statut Discord.

Le détail de chaque version est dans [PATCHNOTES.md](PATCHNOTES.md), et les mesures de
performances dans [docs/banst-pistes.md](docs/banst-pistes.md).

## Signaler un problème

Clic droit sur le jeu, puis **Rapport de compatibilité…**, ou ouvrez une
[issue](https://github.com/Banstowi/-glantine--emu-Switch2/issues) en joignant `eden_log.txt`
(dossier `%APPDATA%\banstdog\log`, une fois l'émulateur fermé).

## Crédits et licence

**Banst Family** est créé et maintenu par Banstowi pour la famille.

Banst Family est construit sur [Lichii](https://github.com/zortos293/Lichii), lui-même issu
d'Eglantine, Eden, yuzu et Sudachi. Leurs mentions de copyright et les licences des
bibliothèques tierces sont conservées dans les fichiers sources, comme l'exige la licence GPL.

Banst Family est distribué sous licence GPL : voir [LICENSE.txt](LICENSE.txt) et le dossier
[LICENSES](LICENSES).
