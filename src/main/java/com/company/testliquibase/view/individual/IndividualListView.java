package com.company.testliquibase.view.individual;

import com.company.testliquibase.entity.Individual;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "individuals", layout = MainView.class)
@ViewController("st_Individual.list")
@ViewDescriptor("individual-list-view.xml")
@LookupComponent("individualsDataGrid")
@DialogMode(width = "64em")
public class IndividualListView extends StandardListView<Individual> {
}