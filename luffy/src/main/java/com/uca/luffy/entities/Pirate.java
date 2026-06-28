package com.uca.luffy.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pirates")
public class Pirate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double bounty;

    @Column(nullable = false)
    private String crew;

    @Column(nullable = false)
    private Boolean isAlive;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBounty() {
        return bounty;
    }

    public String getCrew() {
        return crew;
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public void setIsAlive(Boolean alive) {
        isAlive = alive;
    }
}
