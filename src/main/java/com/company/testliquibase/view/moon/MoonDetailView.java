package com.company.testliquibase.view.moon;

import com.company.testliquibase.entity.Moon;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "moons.detail/:id", layout = MainView.class)
@ViewController("st_Moon.detail")
@ViewDescriptor("moon-detail-view.xml")
@EditedEntityContainer("moonDc")
public class MoonDetailView extends StandardDetailView<Moon> {
}