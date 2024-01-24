package com.company.testliquibase.view.gas;

import com.company.testliquibase.entity.Gas;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "gases", layout = MainView.class)
@ViewController("st_Gas.list")
@ViewDescriptor("gas-list-view.xml")
@LookupComponent("gasesDataGrid")
@DialogMode(width = "64em")
public class GasListView extends StandardListView<Gas> {
}