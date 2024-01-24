package com.company.testliquibase.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TLQ_WAY_BILL", indexes = {
        @Index(name = "IDX_TLQ_WAY_BILL_CREATOR", columnList = "CREATOR_ID"),
        @Index(name = "IDX_TLQ_WAY_BILL_SHIPPER", columnList = "SHIPPER_ID"),
        @Index(name = "IDX_TLQ_WAY_BILL_CONSIGNEE", columnList = "CONSIGNEE_ID"),
        @Index(name = "IDX_TLQ_WAY_BILL_DEPARTURE_PORT", columnList = "DEPARTURE_PORT_ID"),
        @Index(name = "IDX_TLQ_WAY_BILL_DESTINATION_PORT", columnList = "DESTINATION_PORT_ID"),
        @Index(name = "IDX_TLQ_WAY_BILL_CARRIER", columnList = "CARRIER_ID")
})
@Entity(name = "tlq_WayBill")
public class WayBill {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "REFERNCE")
    private String refernce;

    @JoinColumn(name = "CREATOR_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    @JoinColumn(name = "SHIPPER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer shipper;

    @JoinColumn(name = "CONSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer consignee;

    @JoinColumn(name = "DEPARTURE_PORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SpacePort departurePort;

    @JoinColumn(name = "DESTINATION_PORT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SpacePort destinationPort;

    @JoinColumn(name = "CARRIER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carrier carrier;

    @Composition
    @OneToMany(mappedBy = "wayBill")
    private List<WayBillItem> items;

    @Column(name = "TOTAL_WEIGHT")
    private Double totalWeight;

    @Column(name = "TOTAL_CHARGE")
    private Double totalCharge;

    public Double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<WayBillItem> getItems() {
        return items;
    }

    public void setItems(List<WayBillItem> items) {
        this.items = items;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public SpacePort getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(SpacePort destinationPort) {
        this.destinationPort = destinationPort;
    }

    public SpacePort getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(SpacePort departurePort) {
        this.departurePort = departurePort;
    }

    public Customer getConsignee() {
        return consignee;
    }

    public void setConsignee(Customer consignee) {
        this.consignee = consignee;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getRefernce() {
        return refernce;
    }

    public void setRefernce(String refernce) {
        this.refernce = refernce;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}