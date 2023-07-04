package entities;

import jakarta.persistence.*;


@Entity
public class Client {

    @Id
    private int idClient;
    private String nom;
    private String prenom;
    private String adressePhysique;
    private String adresseCourriel;

    @OneToOne
    @MapsId
    private Compte compte;

    public Client() {
    }

    public Client(String nom, String prenom, String adressePhysique, String adresseCourriel, Compte compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.adressePhysique = adressePhysique;
        this.adresseCourriel = adresseCourriel;
        this.compte = compte;
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

    public String getAdressePhysique() {
        return adressePhysique;
    }

    public void setAdressePhysique(String adressePhysique) {
        this.adressePhysique = adressePhysique;
    }

    public String getAdresseCourriel() {
        return adresseCourriel;
    }

    public void setAdresseCourriel(String adresseCourriel) {
        this.adresseCourriel = adresseCourriel;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int id) {
        this.idClient = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adressePhysique='" + adressePhysique + '\'' +
                ", adresseCourriel='" + adresseCourriel + '\'' +
                '}';
    }
}

