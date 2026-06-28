package com.uca.luffy.services;

import com.uca.luffy.entities.Pirate;
import com.uca.luffy.repositories.PirateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PirateServiceTest {

    @Mock
    private PirateRepository pirateRepository;

    @InjectMocks
    private PirateService pirateService;

    @Test
    void createPirate_ShouldSavePirate() {
        Pirate pirate = new Pirate();
        pirate.setName("Monkey D. Luffy");
        pirate.setBounty(3000000000.0);
        pirate.setCrew("Sombrero de Paja");
        pirate.setIsAlive(true);

        when(pirateRepository.save(pirate)).thenReturn(pirate);

        Pirate result = pirateService.createPirate(pirate);

        assertNotNull(result);
        assertEquals("Monkey D. Luffy", result.getName());
        verify(pirateRepository).save(pirate);
    }

    @Test
    void getAllPirates_ShouldReturnPiratesList() {
        Pirate luffy = new Pirate();
        luffy.setName("Monkey D. Luffy");

        Pirate zoro = new Pirate();
        zoro.setName("Roronoa Zoro");

        when(pirateRepository.findAll()).thenReturn(List.of(luffy, zoro));

        List<Pirate> result = pirateService.getAllPirates();

        assertEquals(2, result.size());
        verify(pirateRepository).findAll();
    }

    @Test
    void getPirateById_WhenPirateExists_ShouldReturnPirate() {
        UUID id = UUID.randomUUID();

        Pirate pirate = new Pirate();
        pirate.setId(id);
        pirate.setName("Trafalgar Law");

        when(pirateRepository.findById(id)).thenReturn(Optional.of(pirate));

        Optional<Pirate> result = pirateService.getPirateById(id);

        assertTrue(result.isPresent());
        assertEquals("Trafalgar Law", result.get().getName());
        verify(pirateRepository).findById(id);
    }

    @Test
    void updatePirate_WhenPirateExists_ShouldUpdatePirate() {
        UUID id = UUID.randomUUID();

        Pirate existingPirate = new Pirate();
        existingPirate.setId(id);
        existingPirate.setName("Buggy");
        existingPirate.setBounty(15000000.0);
        existingPirate.setCrew("Buggy Pirates");
        existingPirate.setIsAlive(true);

        Pirate updatedData = new Pirate();
        updatedData.setName("Buggy The Clown");
        updatedData.setBounty(3189000000.0);
        updatedData.setCrew("Cross Guild");
        updatedData.setIsAlive(true);

        when(pirateRepository.findById(id)).thenReturn(Optional.of(existingPirate));
        when(pirateRepository.save(existingPirate)).thenReturn(existingPirate);

        Optional<Pirate> result = pirateService.updatePirate(id, updatedData);

        assertTrue(result.isPresent());
        assertEquals("Buggy The Clown", result.get().getName());
        assertEquals("Cross Guild", result.get().getCrew());

        verify(pirateRepository).findById(id);
        verify(pirateRepository).save(existingPirate);
    }

    @Test
    void deletePirate_WhenPirateExists_ShouldDeletePirate() {
        UUID id = UUID.randomUUID();

        when(pirateRepository.existsById(id)).thenReturn(true);

        boolean result = pirateService.deletePirate(id);

        assertTrue(result);
        verify(pirateRepository).existsById(id);
        verify(pirateRepository).deleteById(id);
    }

    @Test
    void deletePirate_WhenPirateDoesNotExist_ShouldReturnFalse() {
        UUID id = UUID.randomUUID();

        when(pirateRepository.existsById(id)).thenReturn(false);

        boolean result = pirateService.deletePirate(id);

        assertFalse(result);
        verify(pirateRepository).existsById(id);
        verify(pirateRepository, never()).deleteById(id);
    }
}