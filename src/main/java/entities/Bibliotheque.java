package entities;



import jakarta.persistence.*;


@Entity
@Table(name = "bibliotheque")
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


//    @ManyToOne
//    @JoinColumn(name = "idjeu")
    private Jeu jeu;

//    @ManyToOne
//    @JoinColumn(name = "idcompte")
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
}

