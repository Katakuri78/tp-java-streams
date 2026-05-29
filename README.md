# Catalogue Console

## Membres du groupe

- HMAILI Othman ING4 IA & Données de santé
- MOHAMMED Mohammed Zuhayr ING4 IA & Données de santé
- ASSOUL Ahmed-Rami ING4 IA & Données de santé

Application console Java Maven permettant de manipuler un catalogue de produits et des commandes avec les lambdas et l'API Stream.

## Fonctionnalites

- Affichage des produits
- Filtrage par categorie et promotion
- Transformation avec `map`
- Tri par prix, par categorie puis par nom
- Categories uniques avec `distinct` et `Collectors.toSet`
- Pagination avec `skip` et `limit`
- Calculs avec `reduce`
- Calculs avec `mapToDouble().sum()`
- Commandes avec `flatMap`
- Collecte vers `Map`
- Comptage avec `count`
- Demonstrations `Supplier`, `UnaryOperator`, variable effectivement finale et `parallelStream`

## Gitflow

- `main` : version stable finale
- `develop` : integration du travail en cours
- `feature/modeles` : classes metier et donnees
- `feature/services-stream` : traitements Stream
- `feature/console` : menu console
- `feature/tests` : tests unitaires JUnit 5

Chaque branche `feature/*` est fusionnee dans `develop`, puis `develop` est fusionnee dans `main` pour produire la version finale.

## Lancer le projet

```bash
mvn clean compile
mvn test
mvn package
java -jar target/tp-java-streams-1.0.0.jar
```

## Choix techniques

- `filter` : selectionner des elements selon un critere.
- `map` : transformer chaque element en autre chose.
- `flatMap` : aplatir des listes de listes en un seul stream.
- `reduce` vs `mapToDouble().sum()` : `reduce` est generique, `mapToDouble().sum()` est plus direct et optimise pour additionner des `double`.
- `parallelStream` : peut etre plus rapide sur de grandes listes, mais pas toujours, car le decoupage et la synchronisation ont un cout. Il ne faut pas modifier de collection partagee dans un flux parallele.

## Repartition du travail

- Ahmed-Rami ASSOUL : Référent modèle / données | Classes métier, données de test, génération de données
- Mohammed Zuhayr MOHAMMED : Référent traitements Stream | Filtres, tris, transformations, statistiques
- Othman HMAILI : Référent console / Git / tests | Menu console, Gitflow, tests unitaires, documentation

Cette répartition est indicative. Nous nous sommes entraidés pour chaque partie.

## Difficultes rencontrees

- Gestion des conflits Git lors du travail en equipe.
- Configuration Maven sur Windows.
- Respect du Gitflow avec branches, pull requests et tag final.
