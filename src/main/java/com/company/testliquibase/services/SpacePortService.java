package com.company.testliquibase.services;

import com.company.testliquibase.entity.SpacePort;
import org.springframework.stereotype.Component;

@Component
public class SpacePortService {
    public void checkMoonAndPlanet(SpacePort spacePort){
        if(spacePort.getMoon() != null && spacePort.getPlanet() != null) {
            throw new RuntimeException("И поле спутник и поле планета не могут быть заполнены одновременно");
        }
    }
}