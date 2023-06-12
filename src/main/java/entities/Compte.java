package entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Commande> commandes = new ArrayList<>();

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

    public List<Commande> getCommandes() {
        return commandes;
    }

    public Commande trouvePanier(){
        for(Commande commande1: commandes){
            if(commande1.isPanier()){
                return commande1;
            }
        }
        return null;
    }

    public void updateCommande(Commande commande){
        for(Commande commande1:commandes){
            if(commande1.getIdCommande() == commande.getIdCommande()){
                commandes.set(commandes.indexOf(commande1), commande);
            }
        }
    }

    public void removeCommande(Commande commande){
        for(Commande commande1:commandes){
            if(commande1.getIdCommande() == commande.getIdCommande()){
                commandes.remove(commande1);
            }
        }
    }

    public void createPanier(Commande panier){
        commandes.add(panier);
    }

    public void setCommande(List<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "idCompte=" + idCompte +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", profileName='" + profileName + '\'' +
                '}';
    }
}