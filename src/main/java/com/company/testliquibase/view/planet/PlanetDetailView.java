package com.company.testliquibase.view.planet;

import com.company.testliquibase.entity.Planet;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "planets/:id", layout = MainView.class)
@ViewController("st_Planet.detail")
@ViewDescriptor("planet-detail-view.xml")
@EditedEntityContainer("planetDc")
public class PlanetDetailView extends StandardDetailView<Planet> {
}