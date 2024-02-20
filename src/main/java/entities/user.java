package entities;

public class user {
    private String pseudo;
    private int cin;
    private String prenom;
    private String nom;
    private int age;
    private int numTel;
    private String email;
    private String mdp;
    private String role;

    public user(String pseudo, int cin, String prenom, String nom, int age, int numTel, String email, String mdp, String role) {
        this.pseudo = pseudo;
        this.cin = cin;
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.numTel = numTel;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    public user() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "user{" +
                "pseudo='" + pseudo + '\'' +
                ", cin=" + cin +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", age='" + age + '\'' +
                ", numTel=" + numTel +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
