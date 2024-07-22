package com.company.testliquibase.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "TLQ_WAY_BILL_ITEM", indexes = {
        @Index(name = "IDX_TLQ_WAY_BILL_ITEM_WAY_BILL", columnList = "WAY_BILL_ID")
})
@Entity(name = "tlq_WayBillItem")
public class WayBillItem {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    @Column(name = "NUM")
    private Integer num;

    @Column(name = "WEIGHT")
    private Double weight;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lenght", column = @Column(name = "DIMMENSION_LENGHT")),
            @AttributeOverride(name = "width", column = @Column(name = "DIMMENSION_WIDTH")),
            @AttributeOverride(name = "height", column = @Column(name = "DIMMENSION_HEIGHT"))
    })
    private Dimensions dim;

    @Column(name = "CHARGE")
    private Double charge;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "WAY_BILL_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WayBill wayBill;

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Dimensions getDim() {
        return dim;
    }

    public void setDim(Dimensions dim) {
        this.dim = dim;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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