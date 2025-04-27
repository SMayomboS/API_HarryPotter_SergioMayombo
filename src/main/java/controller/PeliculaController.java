package controller;

import model.Pelicula;
import service.PeliculaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> getAllPeliculas() {
        return peliculaService.findAll();
    }

    @PostMapping
    public Pelicula addPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula getPelicula(@PathVariable Long id) {
        return peliculaService.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        pelicula.setId(id);
        return peliculaService.save(pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculaService.delete(id);
    }
}
