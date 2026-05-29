package fr.ecole.tp;
// Console principale - menu interactif
import fr.ecole.tp.service.CatalogueService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CatalogueService service = new CatalogueService();
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        while (choix != 0) {
            afficherMenu();

            try {
                choix = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                choix = -1;
            }

            switch (choix) {
                case 1 -> service.afficherTousProduits();
                case 2 -> service.afficherProduitsPrixCroissant();
                case 3 -> {
                    System.out.print("Categorie (Informatique / Mobilier / Peripheriques / Livres) : ");
                    String cat = scanner.nextLine().trim();
                    service.afficherParCategorie(cat);
                }
                case 4 -> service.afficherProduitsEnPromotionSupA100();
                case 5 -> service.afficherNomsEnMajuscules();
                case 6 -> service.afficherTop3PlusChers();
                case 7 -> {
                    System.out.println("Total (reduce)      : " + service.calculerPrixTotalReduce() + " EUR");
                    System.out.println("Total (mapToDouble) : " + service.calculerPrixTotalMapToDouble() + " EUR");
                }
                case 8 -> service.afficherCategoriesUniques();
                case 9 -> service.afficherTousLesProduitsDansCommandes();
                case 10 -> {
                    service.afficherTotalParCommande();
                    System.out.println("Map id commande -> total :");
                    service.afficherTotauxParCommandeMap();
                }
                case 11 -> {
                    System.out.println("Tous les prix positifs : " + service.tousLesPrixPositifs());
                    System.out.println("Existe une promotion : " + service.existeUnePromotion());
                    System.out.println("Premier produit Informatique : "
                            + service.premierProduitInformatique().orElseThrow());
                    System.out.println("Produit le plus cher : " + service.getProduitLePlusCher().orElseThrow());
                }
                case 12 -> service.demonstrationParallelStream();
                case 13 -> {
                    System.out.print("Page : ");
                    int page = Integer.parseInt(scanner.nextLine().trim());
                    service.afficherPageProduits(page, 5);
                }
                case 14 -> {
                    System.out.println("Prix decroissant :");
                    service.afficherProduitsPrixDecroissant();
                    System.out.println("Categorie puis nom :");
                    service.afficherProduitsCategoriePuisNom();
                }
                case 15 -> service.demonstrationSupplier();
                case 16 -> {
                    System.out.print("Saisie a nettoyer : ");
                    service.demonstrationUnaryOperator(scanner.nextLine());
                }
                case 17 -> service.demonstrationVariableFinale();
                case 0 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println();
        System.out.println("=== CATALOGUE CONSOLE ===");
        System.out.println("1.  Afficher tous les produits");
        System.out.println("2.  Afficher les produits tries par prix croissant");
        System.out.println("3.  Afficher les produits d'une categorie");
        System.out.println("4.  Rechercher les produits en promotion et prix > 100");
        System.out.println("5.  Afficher les noms des produits en majuscules");
        System.out.println("6.  Afficher les 3 produits les plus chers");
        System.out.println("7.  Calculer le prix total du catalogue");
        System.out.println("8.  Afficher les categories uniques");
        System.out.println("9.  Afficher les commandes avec leurs produits");
        System.out.println("10. Calculer le total de chaque commande");
        System.out.println("11. Verifications anyMatch/allMatch/findFirst/max");
        System.out.println("12. Demonstration parallelStream");
        System.out.println("13. Pagination avec skip et limit");
        System.out.println("14. Tris avances");
        System.out.println("15. Demonstration Supplier");
        System.out.println("16. Demonstration UnaryOperator");
        System.out.println("17. Variable effectivement finale");
        System.out.println("0.  Quitter");
        System.out.print("Votre choix : ");
    }
}
