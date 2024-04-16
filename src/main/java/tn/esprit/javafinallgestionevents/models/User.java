package tn.esprit.javafinallgestionevents.models;

public class User {

    private  int id;
    private String roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roles='" + roles + '\'' +
                '}';
    }
}
