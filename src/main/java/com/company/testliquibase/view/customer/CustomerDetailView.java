package com.company.testliquibase.view.customer;

import com.company.testliquibase.entity.Customer;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "customers/:id", layout = MainView.class)
@ViewController("st_Customer.detail")
@ViewDescriptor("customer-detail-view.xml")
@EditedEntityContainer("customerDc")
public class CustomerDetailView extends StandardDetailView<Customer> {
}