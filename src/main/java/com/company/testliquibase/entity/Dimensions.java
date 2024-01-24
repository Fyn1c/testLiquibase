package com.company.testliquibase.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@JmixEntity(name = "st_Dimensions")
@Embeddable
public class Dimensions {
    @Column(name = "LENGHT")
    private Double lenght;

    @Column
    private Double width;

    @Column
    private Double height;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLenght() {
        return lenght;
    }

    public void setLenght(Double lenght) {
        this.lenght = lenght;
    }
}