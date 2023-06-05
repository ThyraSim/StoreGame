package entities;



import jakarta.persistence.*;


@Entity
@Table(name = "bibliotheque")
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "idJeu")
    private Jeu jeu;

    @OneToOne
    @JoinColumn(name = "idCompte")
    private Compte compte;

    private boolean favori;

    public Bibliotheque() {
    }

    public Bibliotheque(Jeu jeu, Compte compte, boolean favori) {
        this.jeu = jeu;
        this.compte = compte;
        this.favori = favori;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "id=" + id +
                ", jeu=" + jeu +
                ", compte=" + compte +
                ", favori=" + favori +
                '}';
    }
}

