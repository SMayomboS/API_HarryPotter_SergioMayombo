package model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private int length; // duración en minutos

    @Column(length = 2000)
    private String sinopsis;

    private int year;

    // Relación inversa con personajes
    @ManyToMany(mappedBy = "peliculas")
    private Set<Personaje> personajes = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(Set<Personaje> personajes) {
        this.personajes = personajes;
    }
}
