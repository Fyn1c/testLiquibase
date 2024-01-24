package com.company.testliquibase.view.company;

import com.company.testliquibase.entity.Company;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "companies/:id", layout = MainView.class)
@ViewController("st_Company.detail")
@ViewDescriptor("company-detail-view.xml")
@EditedEntityContainer("companyDc")
public class CompanyDetailView extends StandardDetailView<Company> {
}