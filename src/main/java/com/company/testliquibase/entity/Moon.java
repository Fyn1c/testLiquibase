package com.company.testliquibase.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

@JmixEntity
@Table(name = "ST_MOON", indexes = {
        @Index(name = "IDX_ST_MOON_PLANET", columnList = "PLANET_ID"),
        @Index(name = "IDX_ST_MOON_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "st_Moon")
public class Moon extends AstronomicalBody {

    @JoinColumn(name = "PLANET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @JoinColumn(name = "ATMOSPHERE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

}