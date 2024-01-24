package com.company.testliquibase.entity;

import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TLQ_SPACE_PORT", indexes = {
        @Index(name = "IDX_TLQ_SPACE_PORT_PLANET", columnList = "PLANET_ID"),
        @Index(name = "IDX_TLQ_SPACE_PORT_MOON", columnList = "MOON_ID")
})
@Entity(name = "tlq_SpacePort")
public class SpacePort {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "PLANET_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @JoinColumn(name = "MOON_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Moon moon;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "COORDINATES_LATITUDE")),
            @AttributeOverride(name = "longitude", column = @Column(name = "COORDINATES_LONGITUDE"))
    })
    private Coordinates coordinates;

    @JoinTable(name = "TLQ_SPACE_PORT_CARRIER_LINK",
            joinColumns = @JoinColumn(name = "SPACE_PORT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CARRIER_ID"))
    @ManyToMany
    private List<Carrier> carriers;

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}