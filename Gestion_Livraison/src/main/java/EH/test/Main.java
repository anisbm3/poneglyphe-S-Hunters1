package EH.test;

import EH.models.Livraison;
import EH.services.ServiceLivraison;
import EH.models.Facture;
import EH.services.ServiceFacture;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Livraison l1 = new Livraison(1, LocalDateTime.now(), "tunis", "eya", 52414880);
        Livraison l2 = new Livraison(2, LocalDateTime.now(), "tunis", "vvvvv", 52646898);


        ServiceLivraison sl = new ServiceLivraison();
        sl.add(l1);
        sl.add(l2);

        System.out.println("Avant la suppression :");
        System.out.println(sl.getAll());

        // Supprimer la livraison avec l'ID 1
        sl.delete(l1);

        System.out.println("Après la suppression :");
        System.out.println(sl.getAll());

        // Mettre à jour la livraison avec l'ID 2
        l2.setAdresse("nouvelle adresse");
        l2.setNom_Client("nouveau nom client");
        l2.setNumTel(12345678);
        sl.update(l2);

        System.out.println("Après la mise à jour :");
        System.out.println(sl.getAll());



        Facture f1 = new Facture(1, "Nom Prenom 1", "ville 1", "100", LocalDateTime.now(), "en cours");
        Facture f2 = new Facture(2, "Nom Prenom 2", "ville 2", "200", LocalDateTime.now(), "en cours");
        ServiceFacture sf = new ServiceFacture();
        sf.add(f1);
        sf.add(f2);

        System.out.println("Avant la suppression :");
        System.out.println(sf.getAll());

        // Supprimer la facture avec l'ID 1
        sf.delete(f1);

        System.out.println("Après la suppression :");
        System.out.println(sf.getAll());

        // Mettre à jour la facture avec l'ID 2
        f2.setVille("nouvelle ville");
        f2.setNomPrenom("nouveau nom prenom");
        f2.setMontant_a_paye("300");
        sf.update(f2);

        System.out.println("Après la mise à jour :");
        System.out.println(sf.getAll());
    }
}

