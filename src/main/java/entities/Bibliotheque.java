package entities;



import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "bibliotheque")
public class Bibliotheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBiblio;

    @OneToOne(mappedBy = "bibliotheque")
    private Compte compte;

    @OneToMany(mappedBy = "bibliotheque")
    private List<LigneBibliotheque> lignes;

    public Bibliotheque() {
    }

    public Bibliotheque(int idBiblio, Compte compte, List<LigneBibliotheque> lignes) {
        this.idBiblio = idBiblio;
        this.compte = compte;
        this.lignes = lignes;
    }

    public int getIdBiblio() {
        return idBiblio;
    }

    public void setIdBiblio(int idBiblio) {
        this.idBiblio = idBiblio;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<LigneBibliotheque> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneBibliotheque> lignes) {
        this.lignes = lignes;
    }

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "idBiblio=" + idBiblio +
                ", compte=" + compte +
                ", lignes=" + lignes +
                '}';
    }
}

