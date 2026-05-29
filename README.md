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

### Utilisation de `filter`

Nous utilisons `filter` pour selectionner uniquement les elements qui respectent une condition precise. Dans le projet, cette operation sert par exemple a recuperer les produits en promotion, les produits dont le prix depasse un seuil, ou les produits appartenant a une categorie donnee.

Ce choix rend le code lisible, car chaque regle metier est exprimee sous forme de predicat.

```java
produits.stream()
    .filter(Produit::isPromotion)
    .toList();
```

### Utilisation de `map`

Nous utilisons `map` lorsqu'il faut transformer une donnee en une autre. Par exemple, un `Produit` peut etre transforme en son nom, puis ce nom peut etre transforme en majuscules.

```java
produits.stream()
    .map(Produit::getNom)
    .map(String::toUpperCase)
    .toList();
```

`map` est adapte ici car il conserve le meme nombre d'elements, tout en modifiant leur forme.

### Utilisation de `flatMap`

Nous utilisons `flatMap` pour transformer une structure imbriquee en une seule liste plate. Dans notre projet, une commande contient plusieurs lignes de commande. Chaque ligne contient un produit. Pour recuperer tous les produits commandes dans toutes les commandes, `flatMap` evite d'obtenir une liste de listes.

```java
commandes.stream()
    .flatMap(c -> c.getLignes().stream())
    .map(LigneCommande::getProduit)
    .toList();
```

`flatMap` est donc utile des qu'un element contient lui-meme une collection.

### Difference entre `reduce` et `mapToDouble().sum()`

Nous avons utilise deux facons de calculer le total du catalogue.

`reduce` est une methode generale. Elle permet d'accumuler progressivement des valeurs pour produire un resultat final. Elle est flexible et peut servir a additionner, concatener ou combiner des objets.

```java
produits.stream()
    .map(Produit::getPrix)
    .reduce(0.0, Double::sum);
```

`mapToDouble().sum()` est plus specialise. Il est concu pour travailler directement avec des nombres de type `double`. Pour calculer une somme de prix, cette solution est plus directe, plus lisible et mieux adaptee.

```java
produits.stream()
    .mapToDouble(Produit::getPrix)
    .sum();
```

Dans ce projet, `reduce` montre le principe d'accumulation, tandis que `mapToDouble().sum()` represente la solution la plus simple pour additionner des prix.

### Limites de `parallelStream`

Nous avons ajoute une demonstration de `parallelStream()` pour comparer un traitement sequentiel et un traitement parallele sur une grande liste de nombres.

Le traitement parallele peut etre plus rapide lorsque la liste contient beaucoup d'elements, lorsque le calcul effectue sur chaque element est suffisamment couteux, et lorsque les elements peuvent etre traites independamment.

Cependant, `parallelStream()` n'est pas toujours plus rapide. La creation des taches, leur repartition entre plusieurs threads et la fusion des resultats ont un cout. Pour une petite liste ou un traitement tres simple, un `stream()` classique peut etre plus efficace.

Nous evitons aussi de modifier une collection externe partagee dans un `parallelStream`, car cela peut provoquer des problemes de concurrence. Nous preferons produire une nouvelle liste avec `map(...).toList()`.

## Repartition du travail

- Ahmed-Rami ASSOUL : referent modele / donnees. Il a travaille sur les classes metier comme `Produit`, `Client`, `Commande` et `LigneCommande`, ainsi que sur la creation des donnees de depart dans `DataFactory`.

- Mohammed Zuhayr MOHAMMED : referent traitements Stream. Il a travaille sur les traitements fonctionnels du catalogue : filtrage, tri, transformation, calculs statistiques, `flatMap`, `reduce`, collecte vers `Set` et `Map`.

- Othman HMAILI : referent console / Git / tests. Il a travaille sur le menu console, l'organisation Gitflow, les tests unitaires JUnit 5, la generation du JAR executable et la documentation du projet.

Cette repartition est indicative. Les membres du groupe se sont entraides sur les differentes parties afin de garantir un projet coherent, compilable et conforme aux exigences du TP.

## Difficultes rencontrees

### Gestion des conflits Git

Le travail en groupe a necessite l'utilisation de plusieurs branches `feature/*`. La principale difficulte a ete de garder un historique Git coherent entre `main`, `develop` et les branches de fonctionnalites.

Nous avons resolu cela en isolant les fonctionnalites dans des branches dediees, puis en les fusionnant dans `develop` avant la version finale sur `main`.

### Configuration Maven sur Windows

La configuration de Maven a demande une attention particuliere, notamment pour que la commande `mvn` soit reconnue dans PowerShell et pour que Java 17 soit correctement utilise.

Nous avons verifie l'installation avec :

```bash
java -version
mvn -version
```

Puis nous avons valide le projet avec :

```bash
mvn clean test
mvn package
```

### Creation d'un JAR executable

Au debut, le fichier `.jar` pouvait etre genere mais ne se lancait pas directement avec `java -jar`, car la classe principale n'etait pas declaree dans le manifest.

Nous avons corrige cela dans le `pom.xml` en configurant le plugin Maven JAR avec la classe principale `fr.ecole.tp.Main`.

### Respect du Gitflow

Le TP demandait un Gitflow precis avec `main`, `develop`, plusieurs branches `feature/*`, des fusions et un tag final `v1.0.0`.

Nous avons donc organise le depot pour rendre visibles les branches demandees et pour placer la version finale stable sur `main`.

### Tests unitaires

Il fallait garantir au minimum 8 tests JUnit detectes par Maven.

Nous avons ajoute des tests cibles sur les fonctionnalites principales : filtrage, transformation, calculs, categories uniques, total de commande et produit le plus cher. La commande `mvn clean test` permet de verifier automatiquement leur bon fonctionnement.
