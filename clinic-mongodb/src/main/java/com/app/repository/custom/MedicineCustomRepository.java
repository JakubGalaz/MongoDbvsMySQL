package com.app.repository.custom;

import com.app.helper.SumHelper;
import com.app.helper.SumOfEveryMedicine;

import java.util.List;

public interface MedicineCustomRepository {

    double getSumOfMedicines();
    List<SumOfEveryMedicine> getNumberOfMedicinesByDoctor(String doctorId);
    List<SumOfEveryMedicine> geSumOfConcreteMedicines();
    SumHelper calculatePrescriptionValue(String medicalVisitId);
}
