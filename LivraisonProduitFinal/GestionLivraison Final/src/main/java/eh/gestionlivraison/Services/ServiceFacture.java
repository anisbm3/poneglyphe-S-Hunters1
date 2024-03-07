package eh.gestionlivraison.Services;
import eh.gestionlivraison.models.Facture;
import eh.gestionlivraison.interfaces.IService;
import eh.gestionlivraison.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceFacture implements IService<Facture> {
    private final Connection cnx;

    public ServiceFacture() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    private int recupereIdLivraison(String NomPrenomClient, String Adresse, int panier_id, float montant, Date date) throws SQLException {
        int ID_Livraison = 0;
        String query = "SELECT ID_Livraison FROM livraison WHERE NomPrenomClient=? AND Adresse=? AND panier_id=? AND montant=? AND date=?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, NomPrenomClient);
            statement.setString(2, Adresse);
            statement.setInt(3, panier_id);
            statement.setFloat(4, montant);
            statement.setDate(5, date);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ID_Livraison = resultSet.getInt("ID_Livraison");
                    System.out.println("Livraison trouvée avec ID : " + ID_Livraison);
                } else {
                    System.out.println("Aucune livraison trouvée pour les critères spécifiés.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID_Livraison;
    }

    @Override
    public void add(Facture facture) throws SQLException {
        if (facture.getDateFacture() != null) {
            // Assurez-vous que les valeurs de panier_id et facture.getIdFacture() sont correctement initialisées
            int panier_id = 18; // exemple
            facture.setIdFacture(1); // exemple

            // Récupérer l'ID de livraison
            int ID_Livraison = recupereIdLivraison(facture.getNomPrenomClient(), facture.getAdresse(), panier_id, facture.getMontant(), facture.getDate());
            if (ID_Livraison != 0) {
                System.out.println("ID_Livraison: " + ID_Livraison);

                // Récupérer les détails de la livraison pour mettre à jour la facture
                String queryLivraison = "SELECT * FROM livraison WHERE ID_Livraison = ?";
                try (PreparedStatement stmLivraison = cnx.prepareStatement(queryLivraison)) {
                    stmLivraison.setInt(1, ID_Livraison);
                    ResultSet rsLivraison = stmLivraison.executeQuery();
                    if (rsLivraison.next()) {
                        facture.setNomPrenomClient(rsLivraison.getString("NomPrenomClient"));
                        facture.setAdresse(rsLivraison.getString("Adresse"));
                        facture.setPanier_id(rsLivraison.getInt("panier_id"));
                        facture.setMontant(rsLivraison.getFloat("montant"));
                        facture.setDate(rsLivraison.getDate("date"));
                    }
                }

                // Insérer la facture dans la table "facture" après avoir récupéré les détails nécessaires
                String queryFacture = "INSERT INTO facture (IdFacture, Remise, MontantAvecRemise, dateFacture, ID_Livraison) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmFacture = cnx.prepareStatement(queryFacture)) {
                    stmFacture.setInt(1, facture.getIdFacture());
                    stmFacture.setInt(2, facture.getRemise());
                    stmFacture.setFloat(3, facture.getMontantAvecRemise());
                    stmFacture.setDate(4, facture.getDateFacture());
                    stmFacture.setInt(5, ID_Livraison);
                    stmFacture.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Aucune livraison trouvée pour les critères spécifiés.");
            }
        } else {
            System.err.println("La date de facture est null");
        }
    }

    @Override
    public ArrayList<Facture> getAll() {
        ArrayList<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM facture";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Facture facture = new Facture();
                facture.setIdFacture(rs.getInt("IdFacture"));
                facture.setRemise(rs.getInt("Remise"));
                facture.setMontantAvecRemise(rs.getFloat("MontantAvecRemise"));
                facture.setDateFacture(rs.getDate("dateFacture"));

                int idLivraison = rs.getInt("ID_Livraison");
                // Récupérer les détails de la livraison depuis la table "livraison"
                String queryLivraison = "SELECT * FROM livraison WHERE ID_Livraison = ?";
                try (PreparedStatement stmLivraison = cnx.prepareStatement(queryLivraison)) {
                    stmLivraison.setInt(1, idLivraison);
                    ResultSet rsLivraison = stmLivraison.executeQuery();
                    if (rsLivraison.next()) {
                        facture.setNomPrenomClient(rsLivraison.getString("NomPrenomClient"));
                        facture.setAdresse(rsLivraison.getString("Adresse"));
                        facture.setMontant(rsLivraison.getFloat("montant"));
                        facture.setDate(rsLivraison.getDate("date"));
                    } else {
                        facture.setNomPrenomClient("NomPrenomClient non trouvé pour cet ID");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    facture.setNomPrenomClient("Erreur de récupération des NomPrenom");
                }

                // Récupérer le produit du panier depuis la table "panier"

                factures.add(facture);

                // Afficher les données de la facture
                System.out.println("IdFacture: " + facture.getIdFacture());
                System.out.println("Remise: " + facture.getRemise());
                System.out.println("Montant avec remise: " + facture.getMontantAvecRemise());
                System.out.println("Date de facture: " + facture.getDateFacture());
                System.out.println("ID Livraison: " + idLivraison);
                System.out.println("NomPrenomClient: " + facture.getNomPrenomClient());
                System.out.println("Adresse: " + facture.getAdresse());
                System.out.println("Montant: " + facture.getMontant());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return factures;
    }

    @Override
    public boolean delete(Facture facture) {
        String query = "DELETE FROM facture WHERE IdFacture = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, facture.getIdFacture());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        String query = "DELETE FROM facture";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Facture getElementByIndex(int id) {
        String query = "SELECT * FROM facture WHERE IdFacture = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Facture facture = new Facture();
                    facture.setIdFacture(rs.getInt("idFacture"));
                    facture.setRemise(rs.getInt("Remise"));
                    facture.setMontantAvecRemise(rs.getFloat("MontantAvecRemise"));
                    facture.setDateFacture(rs.getDate("dateFacture"));
                    facture.setID_Livraison(rs.getInt("ID_Livraison"));

                    return facture;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Facture facture) {
        String query = "UPDATE facture SET Remise=?, MontantAvecRemise=?, dateFacture=? WHERE IdFacture=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, facture.getRemise());
            stm.setFloat(2, facture.getMontantAvecRemise());
            stm.setDate(3, facture.getDateFacture());
            stm.setInt(4, facture.getIdFacture());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Facture updated: " + facture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}