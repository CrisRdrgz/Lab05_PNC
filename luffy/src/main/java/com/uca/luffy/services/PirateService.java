package com.uca.luffy.services;

import com.uca.luffy.entities.Pirate;
import com.uca.luffy.repositories.PirateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PirateService {

    private final PirateRepository pirateRepository;

    public PirateService(PirateRepository pirateRepository) {
        this.pirateRepository = pirateRepository;
    }

    public Pirate createPirate(Pirate pirate) {
        return pirateRepository.save(pirate);
    }

    public List<Pirate> getAllPirates() {
        return pirateRepository.findAll();
    }

    public Optional<Pirate> getPirateById(UUID id) {
        return pirateRepository.findById(id);
    }

    public Optional<Pirate> updatePirate(UUID id, Pirate pirateDetails) {
        return pirateRepository.findById(id)
                .map(pirate -> {
                    pirate.setName(pirateDetails.getName());
                    pirate.setBounty(pirateDetails.getBounty());
                    pirate.setCrew(pirateDetails.getCrew());
                    pirate.setIsAlive(pirateDetails.getIsAlive());

                    return pirateRepository.save(pirate);
                });
    }

    public boolean deletePirate(UUID id) {
        if (!pirateRepository.existsById(id)) {
            return false;
        }

        pirateRepository.deleteById(id);
        return true;
    }
}