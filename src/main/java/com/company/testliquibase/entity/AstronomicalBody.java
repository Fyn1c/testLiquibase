package com.company.testliquibase.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@JmixEntity(name = "st_AstronomicalBody")
@MappedSuperclass
public class AstronomicalBody {

    @Column(name = "ID", nullable = false)
    @JmixGeneratedValue
    @Id
    private UUID id;


    @Column(name = "NAME")
    @InstanceName
    private String name;


    @Column(name = "MASS")
    private Double mass;

    @Column(name = "PICTURE")
    private FileRef picture;

    public void setPicture(FileRef picture) {
        this.picture = picture;
    }

    public FileRef getPicture() {
        return picture;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
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