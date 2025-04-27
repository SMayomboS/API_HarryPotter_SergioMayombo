package model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String desc;

    // Atributos para los combates
    private int magia;
    private int fuerza;
    private int resistencia;

    // Relación con películas
    @ManyToMany
    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "id_personaje"),
            inverseJoinColumns = @JoinColumn(name = "id_pelicula")
    )
    private Set<Pelicula> peliculas = new HashSet<>();

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getMagia() {
        return magia;
    }

    public void setMagia(int magia) {
        this.magia = magia;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public Set<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Set<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
