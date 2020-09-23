package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.Assessment;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class CarAdapter implements CollateralObject {
    private CarDto car;

    @Override
    public BigDecimal getValue() {
        return car.getAssessments().stream()
                .max(Comparator.comparing(Assessment::getDate))
                .orElseThrow(NoSuchElementException::new)
                .getValue();
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }

    @Override
    public LocalDate getDate() {
        // Для автомобилей дата оценки не используется, поэтому всегда берем текущую
        return car.getAssessments().stream()
                .max(Comparator.comparing(Assessment::getDate))
                .orElseThrow(NoSuchElementException::new)
                .getDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
