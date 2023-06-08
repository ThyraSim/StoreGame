package entities;

import jakarta.persistence.*;

public class LigneBibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLigne;

    @ManyToOne
    @JoinColumn(name = "idBiblio")
    private Bibliotheque bibliotheque;

    @ManyToOne
    @JoinColumn(name = "idJeu")
    private Jeu jeu;

    @OneToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;

    private boolean favori;
    private boolean panier;

    public LigneBibliotheque() {
    }

    public LigneBibliotheque(int idLigne, Bibliotheque bibliotheque, Jeu jeu, Compte compte, boolean favori, boolean panier) {
        this.idLigne = idLigne;
        this.bibliotheque = bibliotheque;
        this.jeu = jeu;
        this.compte = compte;
        this.favori = favori;
        this.panier = panier;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public Bibliotheque getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public boolean isPanier() {
        return panier;
    }

    public void setPanier(boolean panier) {
        this.panier = panier;
    }

    @Override
    public String toString() {
        return "LigneBibliotheque{" +
                "idLigne=" + idLigne +
                ", bibliotheque=" + bibliotheque +
                ", jeu=" + jeu +
                ", compte=" + compte +
                ", favori=" + favori +
                ", panier=" + panier +
                '}';
    }
}
