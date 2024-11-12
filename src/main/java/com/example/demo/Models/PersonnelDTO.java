package com.example.demo.Models;

public class PersonnelDTO {
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String motdepasse;
    private String numTelephone;
    private Long id; // Si vous souhaitez exposer l'ID
    private String poste;
    private String grade;
    private long idcnss;
    private long cin;
    private byte enfantsacharge;
    private String categorie;
    private int soldeconge;
	private String role_User;
	private boolean actif;
	
    public String getRole_User() {
		return role_User;
	}

	public void setRole_User(String role_User) {
		this.role_User = role_User;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }


    public long getIdcnss() {
        return idcnss;
    }

    public void setIdcnss(long idcnss) {
        this.idcnss = idcnss;
    }

    public long getCin() {
        return cin;
    }

    public void setCin(long cin) {
        this.cin = cin;
    }

    // Constructeurs
    public PersonnelDTO() {}

    public PersonnelDTO(Long id, String poste, String grade, long idcnss, long cin,
                        byte enfantsacharge, String categorie, int soldeconge) {
        this.id = id;
        this.poste = poste;
        this.grade = grade;
        this.idcnss = idcnss;
        this.cin = cin;
        this.enfantsacharge = enfantsacharge;
        this.categorie = categorie;
        this.soldeconge = soldeconge;
    }
    // Constructeur avec tous les attributs
    public PersonnelDTO(String nom, String prenom, String adresse, String email,
                        String motdepasse, String numTelephone, Long id,
                        String poste, String grade, long idcnss, long cin,
                        byte enfantsacharge, String categorie, int soldeconge) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.motdepasse = motdepasse;
        this.numTelephone = numTelephone;
        this.id = id;
        this.poste = poste;
        this.grade = grade;
        this.idcnss = idcnss;
        this.cin = cin;
        this.enfantsacharge = enfantsacharge;
        this.categorie = categorie;
        this.soldeconge = soldeconge;
    }
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }



    public byte getEnfantsacharge() {
        return enfantsacharge;
    }

    public void setEnfantsacharge(byte enfantsacharge) {
        this.enfantsacharge = enfantsacharge;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getSoldeconge() {
        return soldeconge;
    }

    public void setSoldeconge(int soldeconge) {
        this.soldeconge = soldeconge;
    }


}

