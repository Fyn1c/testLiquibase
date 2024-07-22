package com.company.testliquibase.listeners;

import com.company.testliquibase.entity.WayBill;
import com.company.testliquibase.entity.WayBillItem;
import com.company.testliquibase.services.WayBillItemService;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.SaveContext;
import io.jmix.core.event.AttributeChanges;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("tlq_WayBillItemEventListener")
public class WayBillItemEventListener {

    protected final DataManager dataManager;
    protected final WayBillItemService wayBillItemService;

    public WayBillItemEventListener(DataManager dataManager, WayBillItemService wayBillItemService) {
        this.dataManager = dataManager;
        this.wayBillItemService = wayBillItemService;
    }

    @EventListener
    public void onWayBillItemChangedBeforeCommit(final EntityChangedEvent<WayBillItem> event) {
        if(event.getType().equals(EntityChangedEvent.Type.DELETED)) {
            SaveContext saveContext = new SaveContext();

            Id<Object> id = event.getChanges().getOldReferenceId("wayBill");
            if(id == null) return;

            WayBill wayBill = (WayBill) dataManager.load(id).optional().orElse(null);

            if(wayBill == null) return;


            saveContext.saving(wayBillItemService.recalculateNum(wayBill.getItems()));
            saveContext.saving(wayBill);

            dataManager.save(saveContext);
        }
    }
}