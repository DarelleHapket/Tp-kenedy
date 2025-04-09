package application.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connexion.connexionBD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class IndexController implements Initializable {

    private enum typeApp {
        ordinateurs, telephones
    };

    @FXML
    private TextField cherche;

    @FXML
    private ChoiceBox<typeApp> liste;

    @FXML
    private Label vole;

    @FXML
    private RadioButton searchBySerialRadio; // Pour le choix par numéro de série

    @FXML
    private RadioButton searchByNameRadio; // Pour le choix par nom de l'appareil

    @FXML
    private TextArea detail;
    
    // Méthode pour effectuer la recherche dans la base de données
    private void rechercheAppareil(typeApp type, String searchValue, boolean searchBySerial) {
        String query = "";
        if (searchBySerial) {
            // Recherche par numéro de série
            if (type == typeApp.ordinateurs) {
                query = "SELECT * FROM ordinateurs WHERE numSerie = ?";
            } else if (type == typeApp.telephones) {
                query = "SELECT * FROM telephones WHERE numSerie = ?";
            }
        } else {
            // Recherche par nom de l'appareil
            if (type == typeApp.ordinateurs) {
                query = "SELECT * FROM ordinateurs WHERE nom = ?";
            } else if (type == typeApp.telephones) {
                query = "SELECT * FROM telephones WHERE nom = ?";
            }
        }

        try (Connection conn = connexionBD.Connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, searchValue);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean isVole = rs.getBoolean("vole");
                if (isVole) {
                    vole.setText("Appareil volé");
                } else {
                    vole.setText("Appareil non volé");
                }

                // Affichage des détails de l'appareil dans le TextArea uniquement si l'appareil est trouvé
                String details = "Numéro de série: " + rs.getString("numSerie") + "\n";
                details += "Nom: " + rs.getString("nom") + "\n";
                // Ajoutez d'autres champs de la base de données si nécessaire
                detail.setText(details);  // Mettre à jour le TextArea avec les détails

            } else {
                vole.setText("Aucun appareil trouvé");
                detail.clear();  // Vide le TextArea si l'appareil n'est pas trouvé
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSearch(MouseEvent event) {
        String searchValue = cherche.getText();
        typeApp selectedType = liste.getValue();
        
        if (searchValue != null && !searchValue.isEmpty() && selectedType != null) {
            boolean searchBySerial = searchBySerialRadio.isSelected(); // Détermine si on recherche par numéro de série
            rechercheAppareil(selectedType, searchValue, searchBySerial);
        } else {
            vole.setText("Veuillez entrer un critère de recherche et sélectionner un appareil.");
            detail.clear(); // Vide le TextArea si les critères de recherche ne sont pas valides
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Ajout des types d'appareils dans le ChoiceBox
        liste.getItems().addAll(typeApp.values());
    }
}
