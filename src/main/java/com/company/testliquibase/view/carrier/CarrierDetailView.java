package com.company.testliquibase.view.carrier;

import com.company.testliquibase.entity.Carrier;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "carriers/:id", layout = MainView.class)
@ViewController("st_Carrier.detail")
@ViewDescriptor("carrier-detail-view.xml")
@EditedEntityContainer("carrierDc")
public class CarrierDetailView extends StandardDetailView<Carrier> {
}