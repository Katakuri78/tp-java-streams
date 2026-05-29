package fr.ecole.tp.util;

import fr.ecole.tp.model.Client;
import fr.ecole.tp.model.Commande;
import fr.ecole.tp.model.LigneCommande;
import fr.ecole.tp.model.Produit;

import java.util.List;

public class DataFactory {

    public static List<Produit> getProduits() {
        return List.of(
                new Produit("P01", "Clavier mecanique", "Informatique", 89.99, true),
                new Produit("P02", "Souris gamer", "Informatique", 59.99, false),
                new Produit("P03", "Ecran 27 pouces", "Informatique", 549.99, false),
                new Produit("P04", "Casque audio", "Informatique", 129.99, true),
                new Produit("P05", "Bureau standing", "Mobilier", 349.99, false),
                new Produit("P06", "Chaise ergonomique", "Mobilier", 299.99, true),
                new Produit("P07", "Lampe de bureau", "Mobilier", 45.00, false),
                new Produit("P08", "Webcam HD", "Peripheriques", 79.99, false),
                new Produit("P09", "Hub USB-C", "Peripheriques", 49.99, true),
                new Produit("P10", "Tapis de souris XL", "Peripheriques", 29.99, false),
                new Produit("P11", "Livre Java", "Livres", 39.99, false),
                new Produit("P12", "Livre Design Patterns", "Livres", 44.99, true)
        );
    }

    public static List<Client> getClients() {
        return List.of(
                new Client("C01", "Alice Martin", "alice@mail.com"),
                new Client("C02", "Bob Dupont", "bob@mail.com"),
                new Client("C03", "Clara Petit", "clara@mail.com"),
                new Client("C04", "David Bernard", "david@mail.com")
        );
    }

    public static List<Commande> getCommandes() {
        List<Produit> produits = getProduits();
        List<Client> clients = getClients();

        return List.of(
                new Commande("CMD01", clients.get(0), List.of(
                        new LigneCommande(produits.get(0), 1),
                        new LigneCommande(produits.get(1), 2)
                )),
                new Commande("CMD02", clients.get(1), List.of(
                        new LigneCommande(produits.get(2), 1)
                )),
                new Commande("CMD03", clients.get(2), List.of(
                        new LigneCommande(produits.get(4), 1),
                        new LigneCommande(produits.get(5), 1),
                        new LigneCommande(produits.get(6), 2)
                )),
                new Commande("CMD04", clients.get(3), List.of(
                        new LigneCommande(produits.get(10), 1),
                        new LigneCommande(produits.get(11), 1)
                )),
                new Commande("CMD05", clients.get(0), List.of(
                        new LigneCommande(produits.get(7), 1),
                        new LigneCommande(produits.get(8), 1),
                        new LigneCommande(produits.get(9), 1)
                ))
        );
    }
}
