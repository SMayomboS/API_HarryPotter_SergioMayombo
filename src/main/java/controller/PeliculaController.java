package controller;

import model.Pelicula;
import model.Personaje;
import service.PeliculaService;
import service.PersonajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;
    private final PersonajeService personajeService;

    public PeliculaController(PeliculaService peliculaService, PersonajeService personajeService) {
        this.peliculaService = peliculaService;
        this.personajeService = personajeService;
    }

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.findAll();
    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = peliculaService.save(pelicula);
        System.out.println("Película creada con ID: " + nuevaPelicula.getId()); // 🎯 Usamos getId()
        return nuevaPelicula;
    }


    @GetMapping("/{id}")
    public Pelicula getPelicula(@PathVariable Long id) {
        return peliculaService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula peliculaDetails) {
        Pelicula pelicula = peliculaService.findById(id).orElseThrow();

        pelicula.setTitle(peliculaDetails.getTitle());
        pelicula.setLength(peliculaDetails.getLength());
        pelicula.setSinopsis(peliculaDetails.getSinopsis());
        pelicula.setYear(peliculaDetails.getYear());
        pelicula.setPersonajes(peliculaDetails.getPersonajes());

        return peliculaService.save(pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculaService.delete(id);
    }

    @GetMapping("/{id}/personajes")
    public Set<Personaje> getPersonajesDePelicula(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.findById(id).orElseThrow();
        return pelicula.getPersonajes();
    }
}
