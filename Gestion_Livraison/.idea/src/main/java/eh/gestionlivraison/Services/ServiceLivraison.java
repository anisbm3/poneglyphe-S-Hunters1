package eh.gestionlivraison.Services;
import eh.gestionlivraison.models.Livraison;
import eh.gestionlivraison.interfaces.IService;
import eh.gestionlivraison.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceLivraison implements IService<Livraison> {
    private final Connection cnx;

    public ServiceLivraison() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    private int recupereIdPannier(String Produits) throws SQLException {
        int ID_Pannier = 0;
        String query = "SELECT ID_Pannier FROM panier WHERE Produits = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setString(1, Produits);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ID_Pannier = resultSet.getInt("ID_Pannier");
                }
            }
        }
        return ID_Pannier;
    }

    public void add(Livraison livraison) {
        // Vérifiez si la date de livraison est nulle
        if (livraison.getDate() == null) {
            throw new IllegalArgumentException("La date de livraison ne peut pas être null");
        }

        try {
            int ID_Pannier = recupereIdPannier(livraison.getProduits()); // Utilisez getProduits pour obtenir le nom du produit
            // Insérez la livraison dans la table "livraison"
            String queryLivraison = "INSERT INTO livraison (ID_Livraison, NomPrenomClient, Adresse, ID_Pannier, quantity, montant, Date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stm = cnx.prepareStatement(queryLivraison)) {
                stm.setInt(1, livraison.getID_Livraison());
                stm.setString(2, livraison.getNomPrenomClient());
                stm.setString(3, livraison.getAdresse());
                stm.setInt(4, ID_Pannier); // Utilisez l'ID_Pannier récupéré
                stm.setInt(5, livraison.getQuantity());
                stm.setFloat(6, livraison.getMontant());
                stm.setDate(7, livraison.getDate());
                stm.executeUpdate();
            }

            // Récupérez le nom du produit depuis la table "panier" en utilisant l'ID du panier
            String queryProduit = "SELECT Produits FROM panier WHERE ID_Pannier = ?";
            try (PreparedStatement stmProduits = cnx.prepareStatement(queryProduit)) {
                stmProduits.setInt(1, ID_Pannier); // Utilisez l'ID_Pannier récupéré
                ResultSet rsProduit = stmProduits.executeQuery();
                if (rsProduit.next()) {
                    livraison.setProduits(rsProduit.getString("Produits"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Livraison> getAll() {
        ArrayList<Livraison> livraisons = new ArrayList<>();
        String query = "SELECT NomPrenomClient, Adresse, quantity, montant, date, ID_Pannier FROM livraison";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Livraison livraison = new Livraison();
                livraison.setNomPrenomClient(rs.getString("NomPrenomClient"));
                livraison.setAdresse(rs.getString("Adresse"));
                livraison.setQuantity(rs.getInt("quantity"));
                livraison.setMontant(rs.getFloat("montant"));
                livraison.setDate(rs.getDate("date"));

                int idPannier = rs.getInt("ID_Pannier");

                // Récupérer le nom du produit depuis la table "panier"
                // Récupérer le nom du produit depuis la table "panier"
                String queryProduit = "SELECT Produits FROM panier WHERE ID_Pannier = ?";
                try {
                    PreparedStatement stmProduit = cnx.prepareStatement(queryProduit);
                    stmProduit.setInt(1, idPannier);
                    ResultSet rsProduit = stmProduit.executeQuery();
                    if (rsProduit.next()) {
                        String Produits = rsProduit.getString("Produits");
                        livraison.setProduits(Produits != null ? Produits : "Produit non disponible");
                    } else {
                        livraison.setProduits("Produit non trouvé pour cet ID");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    livraison.setProduits("Erreur de récupération des produits");
                }

                livraisons.add(livraison);

                // Afficher les données de la livraison
                System.out.println("NomPrenomClient: " + livraison.getNomPrenomClient());
                System.out.println("Adresse: " + livraison.getAdresse());
                System.out.println("Quantity: " + livraison.getQuantity());
                System.out.println("Montant: " + livraison.getMontant());
                System.out.println("Date: " + livraison.getDate());
                System.out.println("ID_Pannier: " + idPannier);
                System.out.println("Produits: " + livraison.getProduits());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livraisons;
    }



    @Override
    public  boolean delete(Livraison livraison) {
        String query = "DELETE FROM livraison WHERE ID_Livraison = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setInt(1, livraison.getID_Livraison());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public  boolean deleteAll() {
        String query = "DELETE FROM livraison";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            int rowsDeleted = stm.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllByPannierId(int ID_Pannier) {
        try {
            // Supprimer les livraisons liées à une pannier spécifié
            String query = "DELETE FROM livraison WHERE ID_Pannier = ?";
            int rowsDeleted;
            try (PreparedStatement stm = cnx.prepareStatement(query)) {
                stm.setInt(1, ID_Pannier);
                rowsDeleted = stm.executeUpdate();
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Livraison getElementByIndex(int id) {
        String query = "SELECT * FROM livraison WHERE ID_Livraison = ?";
        try (PreparedStatement stm = cnx.prepareStatement(query)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Livraison livraison = new Livraison();
                    livraison.setID_Livraison(rs.getInt("ID_Livraison"));
                    livraison.setNomPrenomClient(rs.getString("NomPrenomClient"));
                    livraison.setAdresse(rs.getString("Adresse"));
                    livraison.setID_Pannier(rs.getInt("ID_Pannier"));
                    livraison.setQuantity(rs.getInt("quantity"));
                    livraison.setMontant(rs.getFloat("montant"));
                    livraison.setDate(rs.getDate("date"));
                    return livraison;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean update(Livraison livraison) {
        String query = "UPDATE livraison SET NomPrenomClient=?,Adresse=?,ID_Pannier=?, quantity=?, montant=?, date=? WHERE ID_Livraison=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1, livraison.getNomPrenomClient());
            stm.setString(2, livraison.getAdresse());
            stm.setInt(3, livraison.getID_Pannier());
            stm.setInt(4, livraison.getQuantity());
            stm.setFloat(5, livraison.getMontant());
            stm.setDate(6, livraison.getDate());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livraison updated: " + livraison);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }}

