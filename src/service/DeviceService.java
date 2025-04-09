package service;

import dao.OrdinateursDao;
import dao.OrdinateursDaoImpl;
import dao.TelephonesDao;
import dao.TelephonesDaoImpl;
import model.Ordinateurs;
import model.Telephones;

public class DeviceService {

    private final TelephonesDao telephonesDao = new TelephonesDaoImpl();
    private final OrdinateursDao ordinateursDao = new OrdinateursDaoImpl();

    public boolean isStolen(String type, String numSerie) {
        if (type.equalsIgnoreCase("Téléphone")) {
            Telephones tel = telephonesDao.findByNumSerie(numSerie);
            return tel != null && tel.isVole();
        } else if (type.equalsIgnoreCase("Ordinateur")) {
            Ordinateurs ordi = ordinateursDao.findByNumSerie(numSerie);
            return ordi != null && ordi.isVole();
        }
        return false;
    }

    public void reportStolen(String type, String numSerie) {
        if (type.equalsIgnoreCase("Téléphone")) {
            Telephones tel = telephonesDao.findByNumSerie(numSerie);
            if (tel != null) {
                tel.setVole(true);
                telephonesDao.update(tel);
            }
        } else if (type.equalsIgnoreCase("Ordinateur")) {
            Ordinateurs ordi = ordinateursDao.findByNumSerie(numSerie);
            if (ordi != null) {
                ordi.setVole(true);
                ordinateursDao.update(ordi);
            }
        }
    }

    public void enregistrerAppareil(String type, String nom, String numSerie, boolean vole) {
        if (type.equalsIgnoreCase("Téléphone")) {
            Telephones tel = new Telephones(0, nom, numSerie, vole);
            telephonesDao.save(tel);
        } else if (type.equalsIgnoreCase("Ordinateur")) {
            Ordinateurs ordi = new Ordinateurs(0, nom, numSerie, vole);
            ordinateursDao.save(ordi);
        }
    }
}
