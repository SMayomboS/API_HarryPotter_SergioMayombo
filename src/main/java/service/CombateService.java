package service;

import model.Personaje;
import repository.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CombateService {

    private final PersonajeRepository personajeRepository;
    private final Random random = new Random();

    public CombateService(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    public String combatir(Long id1, Long id2) {
        Personaje p1 = personajeRepository.findById(id1).orElseThrow();
        Personaje p2 = personajeRepository.findById(id2).orElseThrow();

        int p1poder = p1.getMagia() + p1.getFuerza() + random.nextInt(20);
        int p2poder = p2.getMagia() + p2.getFuerza() + random.nextInt(20);

        String resultado;
        if (p1poder > p2poder) {
            p1.setResistencia(p1.getResistencia() + 5); // Subimos resistencia
            resultado = p1.getName() + " ganó!";
        } else {
            p2.setResistencia(p2.getResistencia() + 5);
            resultado = p2.getName() + " ganó!";
        }

        personajeRepository.save(p1);
        personajeRepository.save(p2);

        return resultado;
    }
}
