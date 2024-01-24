package com.company.testliquibase.view.atmosphericgas;

import com.company.testliquibase.entity.AtmosphericGas;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "atmosphericGases", layout = MainView.class)
@ViewController("st_AtmosphericGas.list")
@ViewDescriptor("atmospheric-gas-list-view.xml")
@LookupComponent("atmosphericGasesDataGrid")
@DialogMode(width = "64em")
public class AtmosphericGasListView extends StandardListView<AtmosphericGas> {
}