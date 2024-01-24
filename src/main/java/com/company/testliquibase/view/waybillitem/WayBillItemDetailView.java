package com.company.testliquibase.view.waybillitem;

import com.company.testliquibase.entity.WayBillItem;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "wayBillItems/:id", layout = MainView.class)
@ViewController("tlq_WayBillItem.detail")
@ViewDescriptor("way-bill-item-detail-view.xml")
@EditedEntityContainer("wayBillItemDc")
public class WayBillItemDetailView extends StandardDetailView<WayBillItem> {
}