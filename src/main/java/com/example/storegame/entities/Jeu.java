package com.example.storegame.entities;

import jakarta.persistence.*;



@Entity
@Table(name = "jeu")
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJeu;


    private String nomJeu;

    private double prix;

    private String genre;

    private String description;

    // Constructeurs, getters et setters

    public Jeu() {
    }

    public Jeu(String nomJeu, double prix, String genre, String description) {
        this.nomJeu = nomJeu;
        this.prix = prix;
        this.genre = genre;
        this.description = description;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
