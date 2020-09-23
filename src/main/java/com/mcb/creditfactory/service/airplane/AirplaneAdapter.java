package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.Assessment;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {
    private Collateral airplaneDto;

    @Override
    public BigDecimal getValue() {
        return airplaneDto.getAssessments().stream()
                .max(Comparator.comparing(Assessment::getDate))
                .orElseThrow(NoSuchElementException::new)
                .getValue();
    }

    @Override
    public Short getYear() {
        return airplaneDto.getYear();
    }

    @Override
    public LocalDate getDate() {
        return airplaneDto.getAssessments().stream()
                .max(Comparator.comparing(Assessment::getDate))
                .orElseThrow(NoSuchElementException::new)
                .getDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
