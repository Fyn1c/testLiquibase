package com.company.testliquibase.view.waybill;

import com.company.testliquibase.entity.WayBill;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "wayBills", layout = MainView.class)
@ViewController("tlq_WayBill.list")
@ViewDescriptor("way-bill-list-view.xml")
@LookupComponent("wayBillsDataGrid")
@DialogMode(width = "64em")
public class WayBillListView extends StandardListView<WayBill> {
}