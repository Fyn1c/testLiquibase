package com.company.testliquibase.view.spaceport;

import com.company.testliquibase.entity.SpacePort;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "spacePorts", layout = MainView.class)
@ViewController("tlq_SpacePort.list")
@ViewDescriptor("space-port-list-view.xml")
@LookupComponent("spacePortsDataGrid")
@DialogMode(width = "64em")
public class SpacePortListView extends StandardListView<SpacePort> {
}