package EH.test;
import EH.models.Facture;
import EH.models.Livraison;
import EH.services.ServiceFacture;
import EH.services.ServiceLivraison;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

class Main {
    public static void main(String[] args) {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        ServiceFacture serviceFacture = new ServiceFacture();

        // Ajouter une livraison
        Livraison livraison = new Livraison(1,5,10,587,200.0f,LocalDateTime.now());
        serviceLivraison.ajouter(livraison);

        // Afficher toutes les livraisons
        try {
            List<Livraison> livraisons = serviceLivraison.afficher();
            for (Livraison l : livraisons) {
                System.out.println(l);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des livraisons : " + e.getMessage());
        }

        // Modifier une livraison
        Livraison livraisonModifiee = new Livraison(1,2,3, 300,452.0f,LocalDateTime.now());
        try {
            serviceLivraison.modifier(1, livraisonModifiee);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une livraison : " + e.getMessage());
        }

        // Supprimer une livraison
        try {
            serviceLivraison.supprimer(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression d'une livraison : " + e.getMessage());
        }

        // Ajouter une facture
        try {
            serviceFacture.AjouterFacture(1, new Facture(1, LocalDateTime.now(), 10));
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout d'une facture : " + e.getMessage());
        }

        // Afficher toutes les factures
        try {
            List<Facture> factures = serviceFacture.afficher();
            for (Facture f : factures) {
                System.out.println(f);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des factures : " + e.getMessage());
        }

        // Modifier une facture
        try {
            serviceFacture.modifier(1, new Facture(1, LocalDateTime.now(), 20));
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification d'une facture : " + e.getMessage());
        }

        // Supprimer une facture
        try {
            serviceFacture.supprimer(1);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression d'une facture : " + e.getMessage());
        }
    }
}

