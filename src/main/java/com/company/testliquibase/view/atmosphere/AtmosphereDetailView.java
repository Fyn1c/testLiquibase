package com.company.testliquibase.view.atmosphere;

import com.company.testliquibase.entity.Atmosphere;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "atmospheres/:id", layout = MainView.class)
@ViewController("st_Atmosphere.detail")
@ViewDescriptor("atmosphere-detail-view.xml")
@EditedEntityContainer("atmosphereDc")
public class AtmosphereDetailView extends StandardDetailView<Atmosphere> {
}