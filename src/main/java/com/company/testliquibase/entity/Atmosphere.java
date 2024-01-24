package com.company.testliquibase.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_ATMOSPHERE", indexes = {
        @Index(name = "IDX_ST_ATMOSPHERE_PLANET", columnList = "PLANET_ID")
})
@Entity(name = "st_Atmosphere")
public class Atmosphere {
    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    @Id
    private UUID id;

    @Column(name = "DESCRIPTION")
    @InstanceName
    private String description;

    @Column(name = "PRESSURE")
    private Double pressure;

    @Composition
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "atmosphere")
    private List<AtmosphericGas> gases;


    @JoinColumn(name = "PLANET_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @JoinColumn(name = "MOON_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Moon moon;

    public Moon getMoon() {
        return moon;
    }

    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }



    public List<AtmosphericGas> getGases() {
        return gases;
    }

    public void setGases(List<AtmosphericGas> gases) {
        this.gases = gases;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}