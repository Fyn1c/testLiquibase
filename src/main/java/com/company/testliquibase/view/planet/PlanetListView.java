package com.company.testliquibase.view.planet;

import com.company.testliquibase.entity.Planet;

import com.company.testliquibase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.component.validation.DateTimeRangeValidator;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sound.midi.Receiver;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Route(value = "planets", layout = MainView.class)
@ViewController("st_Planet.list")
@ViewDescriptor("planet-list-view.xml")
@LookupComponent("planetsDataGrid")
@DialogMode(width = "64em")
public class PlanetListView extends StandardListView<Planet> {
    @ViewComponent
    private FileStorageUploadField uploadPlanets;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @Autowired
    private DataManager dataManager;
    @ViewComponent
    private CollectionLoader<Planet> planetsDl;

    @Subscribe("uploadPlanets")
    public void onUploadPlanetsFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) throws IOException {
        FileTemporaryStorageBuffer receiver = event.getReceiver();

            UUID fileId = receiver.getFileData().getFileInfo().getId();
            File file = temporaryStorage.getFile(fileId);

            List<String> planetNames = FileUtils.readLines(file, StandardCharsets.UTF_8);

            List<Planet> uploadedPlanets = new ArrayList<>();


            for (String name : planetNames) {
                String[] vars = name.split(",");
                String namePlanet = vars[0];
                Planet planet = dataManager.load(Planet.class)
                        .query("select e from st_Planet e where e.name = :name")
                        .parameter("name", namePlanet)
                        .list().stream().findFirst().orElse(null);

                if (planet == null)
                    planet = dataManager.create(Planet.class);

                planet.setName(namePlanet);
                planet.setMass(Double.valueOf(vars[2]));
                planet.setSemiMajorAxis(Double.valueOf(vars[3]));
                planet.setOrbitalPeriod(Double.valueOf(vars[4]));
                planet.setRotationPeriod(Double.valueOf(vars[7]));
                planet.setRings(Objects.equals(vars[10], "yes"));

                uploadedPlanets.add(planet);
            }
            SaveContext saveContext = new SaveContext();
            saveContext.saving(uploadedPlanets);
            dataManager.save(saveContext.setDiscardSaved(true));

            planetsDl.load();
    }
    
}