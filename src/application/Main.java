package application;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Appeler l'insertion des appareils dans la base de données
        	//ici on a plus besoin d'appeler la methode de connexion a la bd car elle a deja ete
        	//appeller dans insertDevices  qui est appeller ici
            InsertDevices.insertDevices();  

            // Charger l'interface FXML
            URL path = getClass().getResource("/main/ressources/index.fxml");
            System.out.println(path);
            FXMLLoader loader = new FXMLLoader(path);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setTitle("Mon Application JavaFX");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("bonjour");
        launch(args);
    }
}
