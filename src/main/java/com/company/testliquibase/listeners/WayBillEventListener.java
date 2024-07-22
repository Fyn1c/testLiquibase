package com.company.testliquibase.listeners;

import com.company.testliquibase.entity.WayBill;
import com.company.testliquibase.services.WayBillService;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.SaveContext;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("tlq_WayBillEventListener")
public class WayBillEventListener {
    protected final DataManager dataManager;
    protected final WayBillService wayBillService;

    public WayBillEventListener(DataManager dataManager, WayBillService wayBillService) {
        this.dataManager = dataManager;
        this.wayBillService = wayBillService;
    }

    @EventListener
    public void onWayBillChangedBeforeCommit(final EntityChangedEvent<WayBill> event) {
        SaveContext saveContext = new SaveContext();

        Id<Object> id = event.getChanges().getOldReferenceId("id");
        if(id == null) return;

        WayBill wayBill = (WayBill) dataManager.load(id).optional().orElse(null);
        if(wayBill == null) return;

        saveContext.saving(wayBillService.recalculateTotalCharge(wayBill.getItems(), wayBill.getShipper()));
        saveContext.saving(wayBillService.recalculateTotalWeight(wayBill.getItems()));

        dataManager.save(saveContext);
    }
    
    
}