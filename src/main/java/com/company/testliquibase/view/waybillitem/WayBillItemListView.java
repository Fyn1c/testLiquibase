package com.company.testliquibase.view.waybillitem;

import com.company.testliquibase.entity.WayBillItem;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "wayBillItems", layout = MainView.class)
@ViewController("tlq_WayBillItem.list")
@ViewDescriptor("way-bill-item-list-view.xml")
@LookupComponent("wayBillItemsDataGrid")
@DialogMode(width = "64em")
public class WayBillItemListView extends StandardListView<WayBillItem> {
}