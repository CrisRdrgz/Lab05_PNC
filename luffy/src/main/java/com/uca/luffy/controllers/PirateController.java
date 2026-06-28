package com.uca.luffy.controllers;

import com.uca.luffy.entities.Pirate;
import com.uca.luffy.services.PirateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pirates")
public class PirateController {

    private final PirateService pirateService;

    public PirateController(PirateService pirateService) {
        this.pirateService = pirateService;
    }

    @PostMapping
    public Pirate createPirate(@RequestBody Pirate pirate) {
        return pirateService.createPirate(pirate);
    }

    @GetMapping
    public List<Pirate> getAllPirates() {
        return pirateService.getAllPirates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pirate> getPirateById(@PathVariable UUID id) {
        return pirateService.getPirateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pirate> updatePirate(@PathVariable UUID id, @RequestBody Pirate pirateDetails) {
        return pirateService.updatePirate(id, pirateDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePirate(@PathVariable UUID id) {
        boolean deleted = pirateService.deletePirate(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}