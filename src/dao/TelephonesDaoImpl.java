package dao;

import model.Telephones;
import connexion.connexionBD;

import java.sql.*;

public class TelephonesDaoImpl implements TelephonesDao {

    private final Connection conn;

    public TelephonesDaoImpl() {
        conn = connexionBD.Connect();
    }

    @Override
    public Telephones findByNumSerie(String numSerie) {
        try {
            String sql = "SELECT * FROM telephones WHERE numSerie = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numSerie);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Telephones(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("numSerie"),
                    rs.getBoolean("vole")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(Telephones tel) {
        try {
            String sql = "INSERT INTO telephones(nom, numSerie, vole) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tel.getNom());
            stmt.setString(2, tel.getNumSerie());
            stmt.setBoolean(3, tel.isVole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Telephones tel) {
        try {
            String sql = "UPDATE telephones SET vole = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, tel.isVole());
            stmt.setInt(2, tel.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
