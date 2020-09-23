package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.CollateralModel;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {
    @Autowired
    AirplaneService airplaneService;
    @Autowired
    CarService carService;

    ICollateralService<CollateralModel, Collateral> getCollateralService(Collateral object) {
        ICollateralService iCollateralService;
        switch (object.getClass().getSimpleName()) {
            case "AirplaneDto":
                iCollateralService = airplaneService;
                break;
            case "CarDto":
                iCollateralService = carService;
                break;
            default:
                iCollateralService = null;
                break;
        }
        return iCollateralService;
    }
}
