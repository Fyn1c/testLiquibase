package com.company.testliquibase.view.waybill;

import com.company.testliquibase.entity.WayBill;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "wayBills", layout = MainView.class)
@ViewController("tlq_WayBill.list")
@ViewDescriptor("way-bill-list-view.xml")
@LookupComponent("wayBillsDataGrid")
@DialogMode(width = "64em")
public class WayBillListView extends StandardListView<WayBill> {
}