package Controllers;
import Entities.Produit;
import Utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CatalogueController implements Initializable {





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
        private TableColumn<Produit, String> menu_col_Price;

        @FXML
        private TableColumn<Produit, String> menu_col_productName;

        @FXML
        private TableColumn<Produit, String> menu_col_quantity;
        @FXML
        private TableView<Produit> tableView;

        @FXML
        private GridPane menu_gridPane;

        @FXML
        private ScrollPane menu_scrollPane;
        private Connection connect;
        private ResultSet result;
        private PreparedStatement prepare;

        private ObservableList<Produit> cardListData = FXCollections.observableArrayList();

        int cID;



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
menuDisplayTotal();
                menuDisplayCard();


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
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }

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

        private ObservableList<Produit> menuListData;
        public void menushowData() {
                menuListData = catalogueDisplay();
                menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("Nom"));
                menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("Stock"));
                menu_col_Price.setCellValueFactory(new PropertyValueFactory<>("Prix"));
                tableView.setItems(menuListData);
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

}