package controller;

import model.Personaje;
import service.CombateService;
import service.PersonajeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combate")
public class CombateController {

    private final CombateService combateService;
    private final PersonajeService personajeService;

    public CombateController(CombateService combateService, PersonajeService personajeService) {
        this.combateService = combateService;
        this.personajeService = personajeService;
    }

    @GetMapping("/{id}")
    public Personaje getEstadoPersonaje(@PathVariable Long id) {
        return personajeService.findById(id).orElseThrow();
    }

    @PostMapping
    public String combatir(@RequestParam Long p1, @RequestParam Long p2) {
        return combateService.combatir(p1, p2);
    }

    @PutMapping("/{id}")
    public Personaje modificarAtributos(@PathVariable Long id, @RequestBody Personaje personaje) {
        Personaje personajeExistente = personajeService.findById(id).orElseThrow();
        personajeExistente.setMagia(personaje.getMagia());
        personajeExistente.setFuerza(personaje.getFuerza());
        personajeExistente.setResistencia(personaje.getResistencia());
        return personajeService.save(personajeExistente);
    }
}
