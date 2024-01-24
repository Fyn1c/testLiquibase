package com.company.testliquibase.view.discounts;

import com.company.testliquibase.entity.Discounts;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "discountses", layout = MainView.class)
@ViewController("st_Discounts.list")
@ViewDescriptor("discounts-list-view.xml")
@LookupComponent("discountsesDataGrid")
@DialogMode(width = "64em")
public class DiscountsListView extends StandardListView<Discounts> {
}