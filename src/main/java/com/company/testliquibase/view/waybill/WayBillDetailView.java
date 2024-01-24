package com.company.testliquibase.view.waybill;

import com.company.testliquibase.entity.Company;
import com.company.testliquibase.entity.Customer;
import com.company.testliquibase.entity.Individual;
import com.company.testliquibase.entity.WayBill;

import com.company.testliquibase.view.company.CompanyDetailView;
import com.company.testliquibase.view.individual.IndividualDetailView;
import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.Metadata;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.action.BaseAction;
import io.jmix.flowui.kit.component.valuepicker.CustomValueSetEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import io.jmix.flowui.view.builder.LookupWindowBuilder;
import io.jmix.flowui.view.builder.LookupWindowBuilderProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Route(value = "wayBills/:id", layout = MainView.class)
@ViewController("tlq_WayBill.detail")
@ViewDescriptor("way-bill-detail-view.xml")
@EditedEntityContainer("wayBillDc")
public class WayBillDetailView extends StandardDetailView<WayBill> {
    @ViewComponent
    protected InstanceContainer<WayBill> wayBillDc;
    @ViewComponent
    protected JmixRadioButtonGroup consigneeChange;
    @ViewComponent
    protected JmixRadioButtonGroup shippersChange;
    @ViewComponent
    protected EntityComboBox<Customer> shipperComboBox;
    @ViewComponent
    protected EntityPicker<Customer> consigneeField;
    @ViewComponent
    protected CollectionContainer<Customer> companyDc;
    @ViewComponent
    protected CollectionContainer<Customer> individualDc;
    private String changedTypeCustomer;
    @Autowired
    protected Metadata metadata;

    @Subscribe
    protected void onInitEntity(final InitEntityEvent<WayBill> event) {
        consigneeChange.setItems("Individual", "Company");
        shippersChange.setItems("Individual", "Company");

    }

    @Subscribe("shippersChange")
    protected void onShippersChangeComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<?>, ?> event) {
        String value = (String) event.getValue();

        if(value.equals("Individual")){
            shipperComboBox.setItems(individualDc);
        }else {
            shipperComboBox.setItems(companyDc);
        }
    }

    @Subscribe("consigneeChange")
    protected void onConsigneeChangeComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<?>, ?> event) {
        changedTypeCustomer = (String) event.getValue();
    }
    
    

    @Subscribe("consigneeField.entityLookup")
    protected void onConsigneeFieldEntityLookup(final ActionPerformedEvent event) {

        if(changedTypeCustomer.equals("Individual")){

        } else {
            consigneeField.setMetaClass(metadata.getClass(Company.class));
        }
    }



}