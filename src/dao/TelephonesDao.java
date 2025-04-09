package dao;

import model.Telephones;

public interface TelephonesDao {
    Telephones findByNumSerie(String numSerie);
    void save(Telephones tel);
    void update(Telephones tel);
}	

