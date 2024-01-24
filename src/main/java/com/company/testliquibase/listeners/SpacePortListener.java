package com.company.testliquibase.listeners;

import com.company.testliquibase.entity.AstronomicalBody;
import com.company.testliquibase.entity.SpacePort;
import com.company.testliquibase.services.SpacePortService;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.SaveContext;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpacePortListener {
    protected final SpacePortService spacePortService;
    @Autowired
    private DataManager dataManager;

    public SpacePortListener(SpacePortService spacePortService) {
        this.spacePortService = spacePortService;
    }

    @EventListener
    public void onSpacePortChangedBeforeCommit(final EntityChangedEvent<SpacePort> event) {
        Id<SpacePort> entityId = event.getEntityId();
        SpacePort changedSpacePort = dataManager.load(entityId).one();
        Object obj = changedSpacePort.getPlanet() != null ? changedSpacePort.getPlanet() : changedSpacePort.getMoon();
        if (obj == null || !changedSpacePort.getIsDefault()) return;

        String query = "select e from tlq_SpacePort e " +
                "where  e.isDefault = true and e.id <> :id";

        if (changedSpacePort.getPlanet() != null) {
            query = query + " and e.planet = :object";
        } else {
            query = query + " and e.moon = :object";
        }

        List<SpacePort> spacePorts = new ArrayList<>();
        SaveContext saveContext = new SaveContext();

        spacePorts = dataManager.load(SpacePort.class)
                .query(query)
                .parameter("object", obj)
                .parameter("id", entityId)
                .list().stream().collect(Collectors.toList());

        for (SpacePort sp : spacePorts) {
            sp.setIsDefault(false);
        }

        saveContext.saving(spacePorts);
        if (spacePorts.size() > 0) dataManager.save(saveContext);


        dataManager.save(changedSpacePort);
    }
}