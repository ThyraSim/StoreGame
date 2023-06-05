package entities;


import jakarta.persistence.*;

@Entity
@Table(name = "compte")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompte;

    private String user;

    private String password;

    public Compte() {
    }

    public Compte(String user, String password, String profileName) {
        this.user = user;
        this.password = password;
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
}