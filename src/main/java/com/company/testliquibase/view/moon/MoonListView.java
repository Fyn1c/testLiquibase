package com.company.testliquibase.view.moon;

import com.company.testliquibase.entity.Moon;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "moons", layout = MainView.class)
@ViewController("st_Moon.list")
@ViewDescriptor("moon-list-view.xml")
@LookupComponent("moonsDataGrid")
@DialogMode(width = "64em")
public class MoonListView extends StandardListView<Moon> {
}