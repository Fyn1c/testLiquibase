package com.company.testliquibase.view.waybillitem;

import com.company.testliquibase.entity.WayBillItem;

import com.company.testliquibase.services.WayBillItemService;
import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "wayBillItems/:id", layout = MainView.class)
@ViewController("tlq_WayBillItem.detail")
@ViewDescriptor("way-bill-item-detail-view.xml")
@EditedEntityContainer("wayBillItemDc")
public class WayBillItemDetailView extends StandardDetailView<WayBillItem> {

    @ViewComponent
    protected InstanceContainer<WayBillItem> wayBillItemDc;
    @Autowired
    protected WayBillItemService wayBillItemService;

    @Subscribe
    protected void onInitEntity(final InitEntityEvent<WayBillItem> event) {
        if(event.getEntity().getWayBill().getItems() == null) return;
        List<WayBillItem> list = event.getEntity().getWayBill().getItems();
        Integer num = list.size() + 1;
        event.getEntity().setNum(num);

    }
    @Subscribe(id = "wayBillItemDc", target = Target.DATA_CONTAINER)
    protected void onWayBillItemDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<WayBillItem> event) {
        wayBillItemDc.getItem().setCharge(
                wayBillItemService.calculateCharge(
                        wayBillItemDc.getItem().getDim(),
                        wayBillItemDc.getItem().getWeight()
                )
        );
    }

    @Subscribe("dimLenghtField")
    protected void onDimLenghtFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<Double>, Double> event) {
        wayBillItemDc.getItem().setCharge(
                wayBillItemService.calculateCharge(
                        wayBillItemDc.getItem().getDim(),
                        wayBillItemDc.getItem().getWeight()
                )
        );
    }

    @Subscribe("dimWidthField")
    protected void onDimWidthFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<Double>, Double> event) {
        wayBillItemDc.getItem().setCharge(
                wayBillItemService.calculateCharge(
                        wayBillItemDc.getItem().getDim(),
                        wayBillItemDc.getItem().getWeight()
                )
        );
    }

    @Subscribe("dimHeightField")
    protected void onDimHeightFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<TypedTextField<Double>, Double> event) {
        wayBillItemDc.getItem().setCharge(
                wayBillItemService.calculateCharge(
                        wayBillItemDc.getItem().getDim(),
                        wayBillItemDc.getItem().getWeight()
                )
        );
    }

    


}