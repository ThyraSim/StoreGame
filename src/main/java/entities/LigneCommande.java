package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "LigneCommande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLigne;

    @ManyToOne
    @JoinColumn(name = "idJeu")
    private Jeu jeu;

    @ManyToOne
    @JoinColumn(name = "idCommande")
    private Commande commande;


    public LigneCommande() {
    }

    public LigneCommande(Jeu jeu, Commande commande) {
        this.jeu = jeu;
        this.commande = commande;
    }

    public entities.Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idBiblio) {
        this.idLigne = idBiblio;
    }

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "idBiblio=" + idLigne +
                ", jeu=" + jeu +
                '}';
    }
}