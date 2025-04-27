package model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (!id.equals(usuario.id)) return false;
        if (!username.equals(usuario.username)) return false;
        return password.equals(usuario.password);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

}
