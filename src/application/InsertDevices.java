package application;

import connexion.connexionBD; // Importer la classe de connexion
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDevices {

    public static void insertDevices() {
        
        Connection conn = connexionBD.Connect();
        
        if (conn == null) {
            System.out.println("Erreur de connexion à la base de données.");
            return;
        }

        // Insertion dans la table "telephones"
        String insertPhoneSQL = "INSERT INTO telephones (nom, numSerie, vole) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertPhoneSQL)) {
            stmt.setString(1, "iPhone 13");
            stmt.setString(2, "ABC123");
            stmt.setBoolean(3, true);
            stmt.executeUpdate();

            stmt.setString(1, "Samsung A12");
            stmt.setString(2, "DEF456");
            stmt.setBoolean(3, false);
            stmt.executeUpdate();

            stmt.setString(1, "Nokia 5.4");
            stmt.setString(2, "XYZ789");
            stmt.setBoolean(3, false);
            stmt.executeUpdate();

            stmt.setString(1, "Huawei P30");
            stmt.setString(2, "LMN321");
            stmt.setBoolean(3, true);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Insertion dans la table "ordinateurs"
        String insertComputerSQL = "INSERT INTO ordinateurs (nom, numSerie, vole) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertComputerSQL)) {
            stmt.setString(1, "HP Pavilion");
            stmt.setString(2, "GHI789");
            stmt.setBoolean(3, true);
            stmt.executeUpdate();

            stmt.setString(1, "Dell Inspiron");
            stmt.setString(2, "JKL321");
            stmt.setBoolean(3, false);
            stmt.executeUpdate();

            stmt.setString(1, "MacBook Pro");
            stmt.setString(2, "XYZ234");
            stmt.setBoolean(3, false);
            stmt.executeUpdate();

            stmt.setString(1, "Lenovo ThinkPad");
            stmt.setString(2, "ABC987");
            stmt.setBoolean(3, true);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Appareils insérés avec succès.");
    }
}
