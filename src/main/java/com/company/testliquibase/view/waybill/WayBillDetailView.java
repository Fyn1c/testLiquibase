package com.company.testliquibase.view.waybill;

import com.company.testliquibase.entity.*;

import com.company.testliquibase.services.SpacePortService;
import com.company.testliquibase.services.WayBillItemService;
import com.company.testliquibase.services.WayBillService;
import com.company.testliquibase.view.company.CompanyDetailView;
import com.company.testliquibase.view.company.CompanyListView;
import com.company.testliquibase.view.individual.IndividualDetailView;
import com.company.testliquibase.view.individual.IndividualListView;
import com.company.testliquibase.view.main.MainView;

import com.company.testliquibase.view.moon.MoonListView;
import com.company.testliquibase.view.planet.PlanetDetailView;
import com.company.testliquibase.view.planet.PlanetListView;
import com.company.testliquibase.view.waybillitem.WayBillItemDetailView;
import com.company.testliquibase.view.waybillitem.WayBillItemListView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.ColumnReorderEvent;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.core.Metadata;
import io.jmix.core.metamodel.model.MetaClass;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.radiobuttongroup.JmixRadioButtonGroup;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.action.BaseAction;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.valuepicker.CustomValueSetEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.util.RemoveOperation;
import io.jmix.flowui.view.*;
import io.jmix.flowui.view.builder.LookupWindowBuilder;
import io.jmix.flowui.view.builder.LookupWindowBuilderProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    protected SpacePortService spacePortService;
    @ViewComponent
    protected EntityPicker<SpacePort> departurePortField;
    @ViewComponent
    protected EntityPicker<SpacePort> destinationPortField;
    @ViewComponent
    protected CollectionLoader<Carrier> carrierDl;
    @ViewComponent
    protected CollectionContainer<AstronomicalBody> astronomicalBodyDc;
    @ViewComponent
    protected CollectionContainer<WayBillItem> itemsDc;
    @Autowired
    protected WayBillItemService wayBillItemService;
    @ViewComponent
    protected DataGrid<WayBillItem> itemsDataGrid;
    @ViewComponent("itemsDataGrid.upAct")
    protected BaseAction itemsDataGridUpAct;
    @Autowired
    protected WayBillService wayBillService;
    private String changedTypeCustomer;
    @Autowired
    protected DialogWindows dialogWindows;
    @ViewComponent
    protected CollectionLoader<AstronomicalBody> astronomicalBodyDl;
    @ViewComponent
    protected CollectionContainer<Customer> customerDc;

    @Subscribe
    protected void onInit(final InitEvent event) {
        consigneeChange.setItems("Individual", "Company");
        shippersChange.setItems("Individual", "Company");
        
    }


    public void setWayBillDc(WayBill wayBill){
        setConsigneeVisible(wayBill);
        setShipperVisible(wayBill);
    }


    @Subscribe(id = "wayBillDc", target = Target.DATA_CONTAINER)
    protected void onWayBillDcItemChange(final InstanceContainer.ItemChangeEvent<WayBill> event) {
        WayBill wayBill = wayBillDc.getItem();
        setWayBillDc(wayBill);
    }

    @Subscribe("shippersChange")
    protected void onShippersChangeComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<?>, ?> event) {
        String value = (String) event.getValue();
        Customer shipper = wayBillDc.getItem().getShipper();
        if (value.equals("Individual")) {
            customerDc.setItems(individualDc.getItems());

        } else if (value.equals("Company")) {
            customerDc.setItems(companyDc.getItems());

        }

    }

    @Subscribe("consigneeChange")
    protected void onConsigneeChangeComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixRadioButtonGroup<?>, ?> event) {
        changedTypeCustomer = (String) event.getValue();
    }


    @Subscribe("consigneeField.entityLookup")
    protected void onConsigneeFieldEntityLookup(final ActionPerformedEvent event) {
        if (changedTypeCustomer.equals("Individual")) {
            dialogWindows.lookup(this, Individual.class).withViewClass(IndividualListView.class).withSelectHandler(individuals -> {
                Individual individual = individuals.stream().toList().get(0);
                consigneeField.setValue(individual);
            }).open();
        } else if (changedTypeCustomer.equals("Company")) {
            dialogWindows.lookup(this, Company.class).withViewClass(CompanyListView.class).withSelectHandler(companies -> {
                Company company = companies.stream().toList().get(0);
                consigneeField.setValue(company);
            }).open();
        }
    }

    @Install(to = "astronomicalBodyDl", target = Target.DATA_LOADER)
    protected List<AstronomicalBody> astronomicalBodyDlLoadDelegate(final LoadContext<AstronomicalBody> loadContext) {
        return spacePortService.getPlanetsAndMoonForLoader();
    }



    @Subscribe("departurePoint")
    protected void onDeparturePointComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<AstronomicalBody>, AstronomicalBody> event) {
        AstronomicalBody body = event.getValue();
        SpacePort spacePort = spacePortService.getSpasePortForBody(body);
        if(spacePort != null){
            departurePortField.setValue(spacePort);
        }
    }

    @Subscribe("destinationPoint")
    protected void onDestinationPointComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityComboBox<AstronomicalBody>, AstronomicalBody> event) {
        AstronomicalBody body = event.getValue();
        SpacePort spacePort = spacePortService.getSpasePortForBody(body);
        if(spacePort != null){
            destinationPortField.setValue(spacePort);
        }
    }

    @Subscribe("departurePortField")
    protected void onDeparturePortFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<SpacePort>, SpacePort> event) {
        refreshCarriers(event.getValue(), destinationPortField.getValue());
    }

    @Subscribe("destinationPortField")
    protected void onDestinationPortFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<SpacePort>, SpacePort> event) {
       refreshCarriers(departurePortField.getValue(), event.getValue());
    }

    private void refreshCarriers(SpacePort departure, SpacePort destination){
        List<SpacePort> ports =  new ArrayList<>();
        if(departure != null) ports.add(departure);
        if(destination != null) ports.add(destination);
        if(ports.isEmpty()) return;

        carrierDl.setParameter("ports", ports);
        carrierDl.load();
    }

    private void setConsigneeVisible(WayBill waybill){

        String idx = "Company";

        if (waybill == null || waybill.getConsignee() == null) return;

        Customer customer = waybill.getConsignee();
        if (customer instanceof Individual) {
            idx = "Individual";
        }


        consigneeChange.setValue(idx);

    }
    private void setShipperVisible(WayBill waybill){

        String idx = "Company";

        if (waybill == null || waybill.getShipper() == null) return;

        Customer customer = waybill.getShipper();
        if (customer instanceof Individual){
            idx = "Individual";
        }


        shippersChange.setValue(idx);
    }
    
    @Install(to = "itemsDataGrid.remove", subject = "afterActionPerformedHandler")
    protected void itemsDataGridRemoveAfterActionPerformedHandler(final RemoveOperation.AfterActionPerformedEvent<WayBillItem> afterActionPerformedEvent) {
        List<WayBillItem> items = itemsDc.getItems();
        wayBillItemService.recalculateNum(items);
    }

    @Subscribe("itemsDataGrid.upAct")
    protected void onItemsDataGridUpAct(final ActionPerformedEvent event) {
        List<WayBillItem> wayBillItems = wayBillItemService.swapNumbers(itemsDc.getItems(), itemsDc.getItem(), 1);
        itemsDc.setItems(wayBillItems);
    }

    @Subscribe("itemsDataGrid.downAct")
    protected void onItemsDataGridDownAct(final ActionPerformedEvent event) {
        List<WayBillItem> wayBillItems = wayBillItemService.swapNumbers(itemsDc.getItems(), itemsDc.getItem(), -1);
        itemsDc.setItems(wayBillItems);
    }

    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    protected void onItemsDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<WayBillItem> event) {
        wayBillDc.getItem().setTotalCharge(wayBillService.recalculateTotalCharge(itemsDc.getItems(), wayBillDc.getItem().getShipper()));
        wayBillDc.getItem().setTotalWeight(wayBillService.recalculateTotalWeight(itemsDc.getItems()));
    }


    
    

    
    
}