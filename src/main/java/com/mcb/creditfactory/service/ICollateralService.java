package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.model.CollateralModel;

import java.util.Optional;

public interface ICollateralService<M extends CollateralModel, D extends Collateral> {
    boolean approve(D dto);

    M save(M model);

    Optional<M> load(Long id);

    M fromDto(D dto);

    D toDTO(M car);

    Long getId(M model);

    M appendAssessments(M model);
}
