package Controllers;
import Entities.Panier;
import Entities.Produit;
import Utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CatalogueController implements Initializable {


        @FXML
        private TableView<Panier> tableView;

        @FXML
        private AnchorPane form;

        @FXML
        private TextField menu_Amount;

        @FXML
        private Label menu_Change;

        @FXML
        private Button menu_Pay;

        @FXML
        private Button menu_Recipt;

        @FXML
        private Button menu_Remove;

        @FXML
        private Label menu_Total;

        @FXML
        private TableColumn<?, ?> menu_col_Price;

        @FXML
        private TableColumn<?, ?> menu_col_productName;

        @FXML
        private TableColumn<?, ?> menu_col_quantity;

        @FXML
        private GridPane menu_gridPane;

        @FXML
        private ScrollPane menu_scrollPane;
        private Connection connect;
        private ResultSet result;
        private PreparedStatement prepare;

        private ObservableList<Produit> cardListData = FXCollections.observableArrayList();

        int cID;

        private void panierID() {
                if (cID == 0) { // Si cID n'a pas encore été initialisé
                        String sql = "SELECT MAX(panier_id) FROM PANIER";
                        connect = MyDB.getConnection();
                        try {
                                prepare = connect.prepareStatement(sql);
                                result = prepare.executeQuery();
                                if (result.next()) {
                                        cID = result.getInt(1) + 1;
                                } else {
                                        cID = 1;
                                }
                                Data.cID = cID;
                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }
                }
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                menuDisplayCard();
                menuDisplayTotal();





        }
        public ObservableList<Produit> menuGetData() {
                ObservableList<Produit> cardListData = FXCollections.observableArrayList();

                String sql = "SELECT * FROM PRODUITS";
                Connection connect = MyDB.getInstance().getConnection();

                try {
                        PreparedStatement prepare = connect.prepareStatement(sql);
                        ResultSet result = prepare.executeQuery();
                        Produit prod;

                        while (result.next()) {
                                prod = new Produit(
                                        result.getInt("ID_Produit"),
                                        result.getInt("Stock"),
                                        result.getString("Category"),
                                        result.getString("Nom"),
                                        result.getInt("Prix"),
                                        result.getString("Description")
                                );

                                // Ajoutez ces messages de débogage
                                System.out.println("Nom du produit depuis la base de données : " + prod.getNom());
                                System.out.println("Prix du produit depuis la base de données : " + prod.getPrix());

                                cardListData.add(prod);
                        }
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }

                return cardListData;
        }
        private int totalP;
        public void menuDisplayTotal() {
                panierID();
                String total = "SELECT SUM(price) FROM PANIER WHERE panier_id=" + cID;
                connect = MyDB.getConnection();
                try {
                        prepare = connect.prepareStatement(total);
                        result = prepare.executeQuery();
                        if (result.next()) {
                                totalP = result.getInt("SUM(price)");
                                System.out.println("Total Price: " + totalP); // Ajout de débogage
                        }
                        menu_Total.setText("$" + totalP);
                        updateTableView();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

        public void menuDisplayCard() {
                cardListData.clear();
                cardListData.addAll(menuGetData());

                int row = 0;
                int column = 0;
                menu_gridPane.getRowConstraints().clear();
                menu_gridPane.getColumnConstraints().clear();

                for (int q = 0; q < cardListData.size(); q++) {
                        try {
                                FXMLLoader load = new FXMLLoader();
                                load.setLocation(getClass().getResource("/CardPanier.fxml"));
                                AnchorPane pane = load.load();
                                CatalogueCardController cardC = load.getController();
                                cardC.setData(cardListData.get(q));

                                if (column == 2) {
                                        column = 0;
                                        row += 1;
                                }
                                menu_gridPane.add(pane, column++, row);
                        } catch (IOException e) {
                                throw new RuntimeException(e);
                        }
                }
        }
        public  ObservableList<Produit> catalogueDisplay()
        {
                ObservableList<Produit> listData=FXCollections.observableArrayList();
                String sql="SELECT * FROM Panier";
                connect =MyDB.getConnection();
                try {

                        prepare=connect.prepareStatement(sql);
                        result=prepare.executeQuery();
                        Produit prod;
                        while(result.next()){
                                prod= new Produit(result.getInt("IDProduct")

                                        ,result.getInt("Stock")
                                        ,result.getString("Category")
                                        ,result.getString("Nom")
                                        ,result.getInt("Prix")
                                        ,result.getString("Description")


                                );
                                listData.add(prod);
                        }

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
                return listData;
        }
        private ObservableList<Panier> getProductsFromDatabase() {
                ObservableList<Panier> productList = FXCollections.observableArrayList();

                // Code de connexion à la base de données et récupération des données
                try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laugh_tale", "root", "");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM Panier where panier_id="+cID);


                        while (resultSet.next()) {
                                String productName = resultSet.getString("prod_name");
                                int quantity = resultSet.getInt("quantity");
                                int price = resultSet.getInt("price");

                                Panier panier = new Panier(productName, quantity, price);
                                productList.add(panier);
                        }

                        resultSet.close();
                        statement.close();
                        connection.close();
                } catch (SQLException e) {
                        e.printStackTrace();
                }

                return productList;
        }

        private void updateTableView() {
                menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
                menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                menu_col_Price.setCellValueFactory(new PropertyValueFactory<>("price"));

                tableView.setItems(getProductsFromDatabase());
        }
}