package entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "jeu")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJeu;

    private String nomJeu;

    private double prix;

    @ManyToOne
    @JoinColumn(name="idGenre")
    private Genre genre;

    private String description;
    private String imagePath;


    @ManyToMany
    @JoinTable(
            name = "commande_jeu",
            joinColumns = @JoinColumn(name="idJeu"),
            inverseJoinColumns = @JoinColumn(name="idCommande")
    )
    private List<Commande> commandes = new ArrayList<>();

    public Jeu() {
    }

    public Jeu(String nomJeu, double prix, Genre genre, String description) {
        this.nomJeu = nomJeu;
        this.prix = prix;
        this.genre = genre;
        this.description = description;
    }

    public Jeu(String nomJeu, double prix, Genre genre, String description, String imagePath) {
        this.nomJeu = nomJeu;
        this.prix = prix;
        this.genre = genre;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getIdJeu() {
        return idJeu;
    }

    public void setIdJeu(int idJeu) {
        this.idJeu = idJeu;
    }

    public String getNomJeu() {
        return nomJeu;
    }

    public void setNomJeu(String nomJeu) {
        this.nomJeu = nomJeu;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Jeu{" +
                "idJeu=" + idJeu +
                ", nomJeu='" + nomJeu + '\'' +
                ", prix=" + prix +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jeu jeu = (Jeu) o;
        return idJeu == jeu.idJeu;
    }

    @Override
    public int hashCode(){
        return Objects.hash(idJeu);
    }

}
