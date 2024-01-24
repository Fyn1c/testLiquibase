package com.company.testliquibase.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;


@JmixEntity
@Table(name = "ST_PLANET", indexes = {
        @Index(name = "IDX_ST_PLANET_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "st_Planet")
public class Planet extends AstronomicalBody {

    @JoinColumn(name = "ATMOSPHERE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    @Column(name = "SEMI_MAJOR_AXIS")
    private Double semiMajorAxis;

    @Column(name = "ORBITAL_PERIOD")
    private Double orbitalPeriod;

    @Column(name = "RINGS")
    private Boolean rings;

    @Column(name = "ROTATION_PERIOD")
    private Double rotationPeriod;

    public Boolean getRings() {
        return rings;
    }

    public void setRings(Boolean rings) {
        this.rings = rings;
    }

    public void setOrbitalPeriod(Double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public Double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Double getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(Double rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public Double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(Double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

}