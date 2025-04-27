package controller;

import model.Pelicula;
import model.Personaje;
import service.PersonajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private final PersonajeService personajeService;

    public PersonajeController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    public List<Personaje> getAllPersonajes() {
        return personajeService.findAll();
    }

    @PostMapping
    public Personaje createPersonaje(@RequestBody Personaje personaje) {
        Personaje nuevoPersonaje = personajeService.save(personaje);
        System.out.println("Personaje creado con ID: " + nuevoPersonaje.getId());
        return nuevoPersonaje;
    }


    @GetMapping("/{id}")
    public Personaje getPersonaje(@PathVariable Long id) {
        return personajeService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Personaje updatePersonaje(@PathVariable Long id, @RequestBody Personaje personaje) {
        personaje.setId(id);
        return personajeService.save(personaje);
    }

    @DeleteMapping("/{id}")
    public void deletePersonaje(@PathVariable Long id) {
        personajeService.delete(id);
    }

    @GetMapping("/{id}/peliculas")
    public Set<Pelicula> getPeliculasDePersonaje(@PathVariable Long id) {
        Personaje personaje = personajeService.findById(id).orElseThrow();
        return personaje.getPeliculas();
    }

}
