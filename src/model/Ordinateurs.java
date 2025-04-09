package model;

public class Ordinateurs {
	    private int id;
	    private String nom;
	    private String numSerie;
	    private boolean vole;


	    // Constructeur
	    public Ordinateurs(int  id, String nom, String numSerie , boolean vole) {
	        this.id = id;
	        this.nom = nom;
	        this.numSerie = numSerie;
	        this.vole = vole;
	    }


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getNom() {
			return nom;
		}


		public void setNom(String nom) {
			this.nom = nom;
		}


		public String getNumSerie() {
			return numSerie;
		}


		public void setNumSerie(String numSerie) {
			this.numSerie = numSerie;
		}


		public boolean isVole() {
			return vole;
		}


		public void setVole(boolean vole) {
			this.vole = vole;
		}

	   
	}


