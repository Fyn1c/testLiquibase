package com.company.testliquibase.view.spaceport;


import com.company.testliquibase.entity.SpacePort;
import com.company.testliquibase.services.SpacePortService;
import com.company.testliquibase.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "spacePorts/:id", layout = MainView.class)
@ViewController("tlq_SpacePort.detail")
@ViewDescriptor("space-port-detail-view.xml")
@EditedEntityContainer("spacePortDc")
public class SpacePortDetailView extends StandardDetailView<SpacePort> {
    @ViewComponent
    protected InstanceContainer<SpacePort> spacePortDc;
    @Autowired
    protected SpacePortService spacePortService;

    @Subscribe
    protected void onValidation(final ValidationEvent event) {
        spacePortService.checkMoonAndPlanet(spacePortDc.getItem());
        System.out.println("hi");
    }

}