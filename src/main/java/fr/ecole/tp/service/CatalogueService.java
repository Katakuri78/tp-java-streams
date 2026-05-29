package fr.ecole.tp.service;

import fr.ecole.tp.model.Commande;
import fr.ecole.tp.model.LigneCommande;
import fr.ecole.tp.model.Produit;
import fr.ecole.tp.util.DataFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class CatalogueService {

    private final List<Produit> produits = DataFactory.getProduits();
    private final List<Commande> commandes = DataFactory.getCommandes();

    private final Predicate<Produit> estEnPromotion = Produit::isPromotion;
    private final Predicate<Produit> prixSuperieurA100 = p -> p.getPrix() > 100;
    private final Predicate<Produit> categorieInformatique =
            p -> p.getCategorie().equalsIgnoreCase("Informatique");

    public void afficherTousProduits() {
        Consumer<Produit> afficherProduit = System.out::println;
        produits.forEach(afficherProduit);
    }

    public void afficherProduitsFormates() {
        Function<Produit, String> formater = p ->
                "[" + p.getCategorie().toUpperCase() + "] " + p.getNom() + " - " + p.getPrix() + " EUR";
        produits.stream().map(formater).forEach(System.out::println);
    }

    public List<Produit> produitsEnPromotion() {
        return produits.stream().filter(estEnPromotion).toList();
    }

    public List<Produit> produitsPrixSuperieurA100() {
        return produits.stream().filter(prixSuperieurA100).toList();
    }

    public List<Produit> produitsEnPromotionSupA100() {
        return produits.stream()
                .filter(estEnPromotion)
                .filter(prixSuperieurA100)
                .toList();
    }

    public void afficherProduitsEnPromotionSupA100() {
        produitsEnPromotionSupA100().forEach(System.out::println);
    }

    public List<String> nomsEnMajuscules() {
        return produits.stream()
                .map(Produit::getNom)
                .map(String::toUpperCase)
                .toList();
    }

    public void afficherNomsEnMajuscules() {
        nomsEnMajuscules().forEach(System.out::println);
    }

    public List<Produit> produitsPrixCroissant() {
        return produits.stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix))
                .toList();
    }

    public void afficherProduitsPrixCroissant() {
        produitsPrixCroissant().forEach(System.out::println);
    }

    public List<Produit> produitsPrixDecroissant() {
        return produits.stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix).reversed())
                .toList();
    }

    public void afficherProduitsPrixDecroissant() {
        produitsPrixDecroissant().forEach(System.out::println);
    }

    public List<Produit> produitsCategoriePuisNom() {
        return produits.stream()
                .sorted(Comparator.comparing(Produit::getCategorie).thenComparing(Produit::getNom))
                .toList();
    }

    public void afficherProduitsCategoriePuisNom() {
        produitsCategoriePuisNom().forEach(System.out::println);
    }

    public void afficherParCategorie(String categorie) {
        produits.stream()
                .filter(p -> p.getCategorie().equalsIgnoreCase(categorie))
                .forEach(System.out::println);
    }

    public List<Produit> top3PlusChers() {
        return produits.stream()
                .sorted(Comparator.comparingDouble(Produit::getPrix).reversed())
                .limit(3)
                .toList();
    }

    public void afficherTop3PlusChers() {
        top3PlusChers().forEach(System.out::println);
    }

    public List<String> categoriesUniquesDistinct() {
        return produits.stream()
                .map(Produit::getCategorie)
                .distinct()
                .sorted()
                .toList();
    }

    public Set<String> categoriesUniquesSet() {
        return produits.stream()
                .map(Produit::getCategorie)
                .collect(Collectors.toSet());
    }

    public void afficherCategoriesUniques() {
        System.out.println("Avec distinct() :");
        categoriesUniquesDistinct().forEach(System.out::println);
        System.out.println("Avec Collectors.toSet() :");
        categoriesUniquesSet().forEach(System.out::println);
    }

    public List<Produit> pageProduits(int page, int taillePage) {
        return produits.stream()
                .skip((long) (page - 1) * taillePage)
                .limit(taillePage)
                .toList();
    }

    public void afficherPageProduits(int page, int taillePage) {
        pageProduits(page, taillePage).forEach(System.out::println);
    }

    public double calculerPrixTotalReduce() {
        return produits.stream()
                .map(Produit::getPrix)
                .reduce(0.0, Double::sum);
    }

    public double calculerPrixTotalMapToDouble() {
        return produits.stream()
                .mapToDouble(Produit::getPrix)
                .sum();
    }

    public List<Produit> tousLesProduitsCommandes() {
        return commandes.stream()
                .flatMap(c -> c.getLignes().stream())
                .map(LigneCommande::getProduit)
                .toList();
    }

    public void afficherTousLesProduitsDansCommandes() {
        tousLesProduitsCommandes().forEach(System.out::println);
    }

    public double calculerTotalCommande(Commande commande) {
        BiFunction<Produit, Integer, Double> calculLigne =
                (produit, quantite) -> produit.getPrix() * quantite;

        return commande.getLignes().stream()
                .map(ligne -> calculLigne.apply(ligne.getProduit(), ligne.getQuantite()))
                .reduce(0.0, Double::sum);
    }

    public void afficherTotalParCommande() {
        commandes.forEach(cmd ->
                System.out.println(cmd + " -> " + calculerTotalCommande(cmd) + " EUR"));
    }

    public Map<String, Double> totauxParCommande() {
        return commandes.stream()
                .collect(Collectors.toMap(Commande::getId, this::calculerTotalCommande));
    }

    public void afficherTotauxParCommandeMap() {
        totauxParCommande().forEach((id, total) -> System.out.println(id + " -> " + total + " EUR"));
    }

    public boolean tousLesPrixPositifs() {
        return produits.stream().allMatch(p -> p.getPrix() > 0);
    }

    public boolean existeUnePromotion() {
        return produits.stream().anyMatch(Produit::isPromotion);
    }

    public Optional<Produit> premierProduitInformatique() {
        return produits.stream()
                .filter(categorieInformatique)
                .findFirst();
    }

    public Optional<Produit> getProduitLePlusCher() {
        return produits.stream()
                .max(Comparator.comparingDouble(Produit::getPrix));
    }

    public void demonstrationSupplier() {
        Supplier<String> idGenerator = () -> "CMD-" + UUID.randomUUID();
        System.out.println("ID genere : " + idGenerator.get());
    }

    public void demonstrationUnaryOperator(String saisie) {
        UnaryOperator<String> nettoyerSaisie = s -> s.trim().toLowerCase();
        System.out.println("Saisie nettoyee : " + nettoyerSaisie.apply(saisie));
    }

    public void demonstrationParallelStream() {
        List<Integer> nombres = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            nombres.add(i);
        }

        long debut = System.currentTimeMillis();
        long sommeSeq = nombres.stream().mapToLong(Integer::longValue).sum();
        long tempsSeq = System.currentTimeMillis() - debut;

        debut = System.currentTimeMillis();
        long sommePar = nombres.parallelStream().mapToLong(Integer::longValue).sum();
        long tempsPar = System.currentTimeMillis() - debut;

        System.out.println("Sequentiel : " + sommeSeq + " en " + tempsSeq + "ms");
        System.out.println("Parallele  : " + sommePar + " en " + tempsPar + "ms");
    }

    public void demonstrationVariableFinale() {
        // prefixe est effectivement finale : elle n'est jamais reassignee.
        // C'est obligatoire pour etre capturee dans une lambda.
        String prefixe = "[PRODUIT] ";
        produits.forEach(p -> System.out.println(prefixe + p.getNom()));
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }
}
