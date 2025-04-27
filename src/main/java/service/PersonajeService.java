package service;

import model.Personaje;
import repository.PersonajeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {
    private final PersonajeRepository personajeRepository;

    public PersonajeService(PersonajeRepository personajeRepository) {
        this.personajeRepository = personajeRepository;
    }

    public List<Personaje> findAll() {
        return personajeRepository.findAll();
    }

    public Optional<Personaje> findById(Long id) {
        return personajeRepository.findById(id);
    }

    public Personaje save(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }
}
