package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dao.CommandeDao;
import jakarta.persistence.*;
import util.Utilitaire;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCommande;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idCompte")
    Compte compte;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(
            name = "commande_jeu",
            joinColumns = @JoinColumn(name="idcommande"),
            inverseJoinColumns = @JoinColumn(name="idJeu")
    )
    private List<Jeu> jeux = new ArrayList<>();

    private boolean panier;

    public Commande() {
    }

    public Commande(Compte compte, boolean panier) {
        this.compte = compte;
        this.panier = panier;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public boolean isPanier() {
        return panier;
    }

    public void setPanier(boolean panier) {
        this.panier = panier;
    }

    public List<Jeu> getJeux() {
        return jeux;
    }

    public void setJeux(List<Jeu> jeux) {
        this.jeux = jeux;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", compte=" + compte +
                ", jeux=" + jeux +
                ", panier=" + panier +
                '}';
    }

    public void removeJeu(int idJeu){
        for(Jeu jeu: jeux){
            if(jeu.getIdJeu() == idJeu){
                jeux.remove(jeu);
            }
        }
        CommandeDao.update(this);
    }

    public void addJeu(Jeu jeu){
        Utilitaire.addJeu(this, jeu);
    }
}
