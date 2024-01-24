package com.company.testliquibase.view.atmosphere;

import com.company.testliquibase.entity.Atmosphere;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "atmospheres", layout = MainView.class)
@ViewController("st_Atmosphere.list")
@ViewDescriptor("atmosphere-list-view.xml")
@LookupComponent("atmospheresDataGrid")
@DialogMode(width = "64em")
public class AtmosphereListView extends StandardListView<Atmosphere> {
}