package entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompte;

    private String user;

    private String password;

    private String profileName;

    @OneToOne
    @JoinColumn(name = "idBiblio")
    private Bibliotheque bibliotheque;

    public Compte() {
    }

    public Compte(int idCompte, String user, String password, String profileName, Bibliotheque bibliotheque) {
        this.idCompte = idCompte;
        this.user = user;
        this.password = password;
        this.profileName = profileName;
        this.bibliotheque = bibliotheque;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "idCompte=" + idCompte +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", profileName='" + profileName + '\'' +
                ", bibliotheque=" + bibliotheque +
                '}';
    }
}