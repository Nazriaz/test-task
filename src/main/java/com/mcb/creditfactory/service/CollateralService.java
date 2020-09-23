package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.CollateralModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private ServiceFactory serviceFactory;

    @SuppressWarnings("ConstantConditions")
    public Long saveCollateral(Collateral object) {
        ICollateralService<CollateralModel, Collateral> collateralService = serviceFactory.getCollateralService(object);
        if (collateralService == null) throw new IllegalArgumentException();
        boolean approve = collateralService.approve(object);
        if (!approve) {
            return null;
        }
        boolean exists = getInfo(object) != null;
        if (exists) return appendCollateralAssessments(object);
        return Optional.of(object)
                .map(collateralService::fromDto)
                .map(collateralService::save)
                .map(collateralService::getId)
                .orElse(null);
    }

    private Long appendCollateralAssessments(Collateral object) {
        ICollateralService<CollateralModel, Collateral> collateralService = serviceFactory.getCollateralService(object);
        if (collateralService == null) throw new IllegalArgumentException();
        return Optional.of(object)
                .map(collateralService::fromDto)
                .map(collateralService::appendAssessments)
                .map(collateralService::getId)
                .orElse(null);
    }

    public Collateral getInfo(Collateral object) {
        ICollateralService<CollateralModel, Collateral> collateralService = serviceFactory.getCollateralService(object);
        if (collateralService == null) throw new IllegalArgumentException();
        return Optional.of(object)
                .map(collateralService::fromDto)
                .map(collateralService::getId)
                .flatMap(collateralService::load)
                .map(collateralService::toDTO)
                .orElse(null);
    }
}
