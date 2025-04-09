package dao;

import model.Ordinateurs;

public interface OrdinateursDao {
    Ordinateurs findByNumSerie(String numSerie);
    void save(Ordinateurs ordi);
    void update(Ordinateurs ordi);
}
