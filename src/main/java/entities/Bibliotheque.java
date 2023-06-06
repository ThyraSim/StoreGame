package entities;



import jakarta.persistence.*;


@Entity
@Table(name = "bibliotheque")
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBiblio;


    @ManyToOne
    @JoinColumn(name = "idJeu")
    private Jeu jeu;

    @OneToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;

    private boolean favori;
    private boolean panier;

    public Bibliotheque() {
    }

    public Bibliotheque(Jeu jeu, Compte compte, boolean favori, boolean panier) {
        this.jeu = jeu;
        this.compte = compte;
        this.favori = favori;
        this.panier = panier;
    }

    public entities.Jeu getJeu() {
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

    public int getIdBiblio() {
        return idBiblio;
    }

    public void setIdBiblio(int idBiblio) {
        this.idBiblio = idBiblio;
    }

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "idBiblio=" + idBiblio +
                ", jeu=" + jeu +
                ", compte=" + compte +
                ", favori=" + favori +
                ", panier=" + panier +
                '}';
    }
}

