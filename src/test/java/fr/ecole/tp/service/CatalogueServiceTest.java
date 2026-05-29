package fr.ecole.tp.service;

import fr.ecole.tp.model.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CatalogueServiceTest {
    private CatalogueService service;

    @BeforeEach
    void setUp() {
        service = new CatalogueService();
    }

    @Test
    void doitFiltrerLesProduitsEnPromotion() {
        List<Produit> resultat = service.produitsEnPromotion();

        assertEquals(5, resultat.size());
        assertTrue(resultat.stream().allMatch(Produit::isPromotion));
    }

    @Test
    void doitFiltrerLesProduitsAvecPrixSuperieurA100() {
        List<Produit> resultat = service.produitsPrixSuperieurA100();

        assertEquals(4, resultat.size());
        assertTrue(resultat.stream().allMatch(p -> p.getPrix() > 100));
    }

    @Test
    void doitTransformerLesNomsEnMajuscules() {
        List<String> resultat = service.nomsEnMajuscules();

        assertTrue(resultat.contains("CLAVIER MECANIQUE"));
        assertTrue(resultat.stream().allMatch(nom -> nom.equals(nom.toUpperCase())));
    }

    @Test
    void doitCalculerLeTotalCatalogueAvecReduce() {
        assertEquals(1769.89, service.calculerPrixTotalReduce(), 0.001);
    }

    @Test
    void doitCalculerLeTotalCatalogueAvecMapToDouble() {
        assertEquals(1769.89, service.calculerPrixTotalMapToDouble(), 0.001);
    }

    @Test
    void doitRecupererLesCategoriesUniques() {
        assertEquals(4, service.categoriesUniquesSet().size());
        assertTrue(service.categoriesUniquesSet().contains("Informatique"));
        assertTrue(service.categoriesUniquesSet().contains("Mobilier"));
    }

    @Test
    void doitCalculerLeTotalDUneCommande() {
        assertEquals(209.97, service.calculerTotalCommande(service.getCommandes().get(0)), 0.001);
    }

    @Test
    void doitTrouverLeProduitLePlusCher() {
        Produit produit = service.getProduitLePlusCher().orElseThrow();

        assertEquals("Ecran 27 pouces", produit.getNom());
        assertEquals(549.99, produit.getPrix(), 0.001);
    }
}
