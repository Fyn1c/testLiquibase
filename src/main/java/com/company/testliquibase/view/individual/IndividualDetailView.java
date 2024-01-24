package com.company.testliquibase.view.individual;

import com.company.testliquibase.entity.Individual;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "individuals/:id", layout = MainView.class)
@ViewController("st_Individual.detail")
@ViewDescriptor("individual-detail-view.xml")
@EditedEntityContainer("individualDc")
public class IndividualDetailView extends StandardDetailView<Individual> {
}