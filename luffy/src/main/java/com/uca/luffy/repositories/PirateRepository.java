package com.uca.luffy.repositories;

import com.uca.luffy.entities.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PirateRepository extends JpaRepository<Pirate, UUID> {
}