package com.company.testliquibase.view.gas;

import com.company.testliquibase.entity.Gas;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "gases/:id", layout = MainView.class)
@ViewController("st_Gas.detail")
@ViewDescriptor("gas-detail-view.xml")
@EditedEntityContainer("gasDc")
public class GasDetailView extends StandardDetailView<Gas> {
}