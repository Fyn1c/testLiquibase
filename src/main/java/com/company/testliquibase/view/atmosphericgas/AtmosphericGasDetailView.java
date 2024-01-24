package com.company.testliquibase.view.atmosphericgas;

import com.company.testliquibase.entity.AtmosphericGas;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "atmosphericGases/:id", layout = MainView.class)
@ViewController("st_AtmosphericGas.detail")
@ViewDescriptor("atmospheric-gas-detail-view.xml")
@EditedEntityContainer("atmosphericGasDc")
public class AtmosphericGasDetailView extends StandardDetailView<AtmosphericGas> {
}