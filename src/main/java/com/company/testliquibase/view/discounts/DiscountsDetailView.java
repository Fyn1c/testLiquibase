package com.company.testliquibase.view.discounts;

import com.company.testliquibase.entity.Discounts;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "discountses/:id", layout = MainView.class)
@ViewController("st_Discounts.detail")
@ViewDescriptor("discounts-detail-view.xml")
@EditedEntityContainer("discountsDc")
public class DiscountsDetailView extends StandardDetailView<Discounts> {
}