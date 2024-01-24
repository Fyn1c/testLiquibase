package com.company.testliquibase.view.company;

import com.company.testliquibase.entity.Company;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "companies", layout = MainView.class)
@ViewController("st_Company.list")
@ViewDescriptor("company-list-view.xml")
@LookupComponent("companiesDataGrid")
@DialogMode(width = "64em")
public class CompanyListView extends StandardListView<Company> {
}