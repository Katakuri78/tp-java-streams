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

Nous avons utilise `filter` pour garder uniquement les elements qui respectent une condition : produits en promotion, prix superieur a un seuil, ou categorie choisie par l'utilisateur.

`map` sert a transformer les donnees. Par exemple, on part d'un `Produit`, on recupere son nom avec `Produit::getNom`, puis on le passe en majuscules avec `String::toUpperCase`.

`flatMap` est utilise pour les commandes. Comme chaque commande contient plusieurs lignes, il permet de recuperer tous les produits commandes dans une seule liste simple, au lieu d'avoir une liste de listes.

Pour le total du catalogue, nous avons garde deux approches :

- `reduce`, pour montrer le principe d'accumulation ;
- `mapToDouble().sum()`, plus direct et plus adapte pour additionner des prix.

La demonstration `parallelStream` compare un traitement sequentiel et un traitement parallele. Le parallele peut etre plus rapide sur de gros volumes, mais ce n'est pas automatique : creer et synchroniser plusieurs traitements a aussi un cout. Nous evitons donc de modifier une liste partagee dans un `parallelStream`.

## Repartition du travail

- Ahmed-Rami ASSOUL : modeles et donnees (`Produit`, `Client`, `Commande`, `LigneCommande`, `DataFactory`).
- Mohammed Zuhayr MOHAMMED : traitements Stream, filtres, tris, calculs, `flatMap`, `reduce`, `Set` et `Map`.
- Othman HMAILI : menu console, Gitflow, tests JUnit, JAR executable et documentation.

La repartition nous a servi de base, mais nous nous sommes aides sur les differentes parties pour obtenir un projet coherent.

## Difficultes rencontrees

Nous avons du configurer correctement Java 17 et Maven sous Windows, puis verifier le projet avec :

```bash
mvn clean test
mvn package
```

Un autre point a ete le JAR executable. Au debut, le fichier `.jar` etait genere, mais ne pouvait pas etre lance avec `java -jar`. Nous avons corrige le `pom.xml` pour declarer `fr.ecole.tp.Main` comme classe principale.

Enfin, nous avons complete les tests JUnit pour couvrir les fonctionnalites principales du TP : filtres, transformations, calculs, categories, commandes et produit le plus cher.

