package com.digiplan.services;

import java.util.List;

import com.digiplan.entities.TreatmentMethod;

public interface TreatmentMethodService {

    TreatmentMethod getTreatmentMethodData(String id);

    List<TreatmentMethod> getAllTreatmentMethodData();

    TreatmentMethod addTreatmentMethodData(TreatmentMethod treatmentMethodData);

    TreatmentMethod updateTreatmentMethodData(String id, TreatmentMethod treatmentMethodData);

    String deleteTreatmentMethodData(String id);

}
