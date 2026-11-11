# Raccourcis Banst Family

Chemins et réglages utiles sous Windows. Collez un chemin dans la barre d'adresse de
l'Explorateur, ou dans Exécuter (Win + R).

## Dossiers

| Quoi | Chemin |
|---|---|
| Journaux (`eden_log.txt`) | `%APPDATA%\banstdog\log` |
| Réglages (`qt-config.ini`) | `%APPDATA%\banstdog\config` |
| Cache de shaders, un dossier par jeu | `%APPDATA%\banstdog\shader` |
| Cache de shaders de Zelda Ocarina of Time | `%APPDATA%\banstdog\shader\0400486024dec000` |
| Rapports de compatibilité | `%APPDATA%\banstdog\reports` |
| Toutes les données | `%APPDATA%\banstdog` |

Le journal n'est complet qu'une fois Banst Family fermé.

## Lanceur de diagnostic

Fichier `banstdog-trace.bat`, à côté de `banstdog.exe` :

```bat
set EDEN_NX2_STUTTER_TRACE=1
start "" banstdog.exe
```

Lignes à ajouter avant `start` selon le test :

| Ligne | Effet |
|---|---|
| `set EDEN_NX2_ASYNC_TRANSLATION=1` | Traduit les nouveaux shaders hors du fil graphique (expérimental) |
| `set EDEN_NX2_PARALLEL_POOL_KIB=1024` | Remet l'ancienne limite de la traduction parallèle, pour comparer |

## Menus

| Quoi | Où |
|---|---|
| Réglages recommandés, et retour en arrière | Aide → Appliquer les réglages recommandés |
| Langue de l'interface | Émulation → Configurer → Général → Interface |
| Précision GPU, shaders asynchrones | Émulation → Configurer → Graphismes → Avancé |
| Dossier du cache d'un jeu | Clic droit sur le jeu → Ouvrir le cache de pipelines transférable |
| Rapport de compatibilité | Clic droit sur le jeu → Rapport de compatibilité… |

## Liens

- Lancer un build : https://github.com/Banstowi/-glantine--emu-Switch2/actions/workflows/ci.yml
- Versions publiées : https://github.com/Banstowi/-glantine--emu-Switch2/releases
- Notes de version : `PATCHNOTES.md`
- Pistes et mesures : `docs/banst-pistes.md`
