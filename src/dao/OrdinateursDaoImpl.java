package dao;

import model.Ordinateurs;
import connexion.connexionBD;

import java.sql.*;

public class OrdinateursDaoImpl implements OrdinateursDao {

    private final Connection conn;

    public OrdinateursDaoImpl() {
        conn = connexionBD.Connect();
    }

    @Override
    public Ordinateurs findByNumSerie(String numSerie) {
        try {
            String sql = "SELECT * FROM ordinateurs WHERE numSerie = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, numSerie);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Ordinateurs(
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
    public void save(Ordinateurs ordi) {
        try {
            String sql = "INSERT INTO ordinateurs(nom, numSerie, vole) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ordi.getNom());
            stmt.setString(2, ordi.getNumSerie());
            stmt.setBoolean(3, ordi.isVole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ordinateurs ordi) {
        try {
            String sql = "UPDATE ordinateurs SET vole = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, ordi.isVole());
            stmt.setInt(2, ordi.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
