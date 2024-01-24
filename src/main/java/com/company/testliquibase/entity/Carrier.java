package com.company.testliquibase.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_CARRIER")
@Entity(name = "st_Carrier")
public class Carrier {
    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    @Id
    private UUID id;

    @Column(name = "NAME")
    @NotNull
    @InstanceName
    private String name;
    @JoinTable(name = "TLQ_SPACE_PORT_CARRIER_LINK",
            joinColumns = @JoinColumn(name = "CARRIER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPACE_PORT_ID"))
    @ManyToMany
    private List<SpacePort> spacePorts;

    public List<SpacePort> getSpacePorts() {
        return spacePorts;
    }

    public void setSpacePorts(List<SpacePort> spacePorts) {
        this.spacePorts = spacePorts;
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