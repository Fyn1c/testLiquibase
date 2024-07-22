package com.company.testliquibase.services;

import com.company.testliquibase.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.FluentLoader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SpacePortService {
    @Autowired
    ModelMapper modelMapper;
    protected final DataManager dataManager;

    public SpacePortService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void checkMoonAndPlanet(SpacePort spacePort){
        if(spacePort.getMoon() != null && spacePort.getPlanet() != null) {
            throw new RuntimeException("И поле спутник и поле планета не могут быть заполнены одновременно");
        }
    }
    public List<AstronomicalBody> getPlanetsAndMoonForLoader(){
        List<AstronomicalBody> astronomicalBodyList = new ArrayList<>();
        astronomicalBodyList.addAll(dataManager.load(Planet.class).all().list().stream().map(i -> modelMapper.map(i, AstronomicalBody.class)).toList());
        astronomicalBodyList.addAll(dataManager.load(Moon.class).all().list().stream().map(i -> modelMapper.map(i, AstronomicalBody.class)).toList());
        return astronomicalBodyList;
    }

    public SpacePort getSpasePortForBody(AstronomicalBody body){
        String string = new String();
        if(body instanceof Planet){
            string = "e.planet = :body";
        }else if (body instanceof Moon){
            string = "e.moon = :body";
        }
        return dataManager.load(SpacePort.class).query("select e from tlq_SpacePort e where " + string + " and e.isDefault = :isDefault")
                .parameter("body", body)
                .parameter("isDefault", true)
                .optional().orElse(null);
    }

}