package entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompte")
    private int idCompte;

    @Column(name = "username")
    private String user;

    @Column(name = "password")
    private String password;

    @Column(name = "profilename")
    private String profileName;


    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Commande> commande;

    public Compte() {
    }

    public Compte(String user, String password, String profileName) {
        this.user = user;
        this.password = password;
        this.profileName = profileName;
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

    public List<Commande> getCommande() {
        return commande;
    }

    public Commande getPanier(){
        for(Commande commande1: commande){
            if(commande1.isPanier()){
                return commande1;
            }
        }
        return null;
    }

    public void setCommande(List<Commande> commande) {
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "idCompte=" + idCompte +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", profileName='" + profileName + '\'' +
                ", commande=" + commande +
                '}';
    }
}