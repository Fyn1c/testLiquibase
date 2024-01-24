package com.company.testliquibase.view.carrier;

import com.company.testliquibase.entity.Carrier;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "carriers", layout = MainView.class)
@ViewController("st_Carrier.list")
@ViewDescriptor("carrier-list-view.xml")
@LookupComponent("carriersDataGrid")
@DialogMode(width = "64em")
public class CarrierListView extends StandardListView<Carrier> {
}