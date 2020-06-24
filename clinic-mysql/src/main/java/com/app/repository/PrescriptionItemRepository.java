package com.app.repository;

import com.app.helper.SumOfEveryMedicine;
import com.app.model.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionItemRepository extends JpaRepository<PrescriptionItem,Integer> {

    @Query("SELECT p.idMedicalVisit.id, ROUND(sum(p.idMedicine.price * p.amount), 2) from PrescriptionItem p group by p.idMedicalVisit.id")
    String[] getSumes();

//    @Query("SELECT new PrescriptionItem (p.idMedicine, SUM(p.idMedicine.price * p.amount)) from PrescriptionItem p group by p.idMedicine")
//    List <PrescriptionItem> geSumOfConcreteMedicines();
//
//    @Query("SELECT new PrescriptionItem (p.idMedicine, sum(p.amount)) from PrescriptionItem p where p.idMedicalVisit.idDoctor.id = :doctorId group by p.idMedicine")
//    List<PrescriptionItem> getNumberOfMedicinesByDoctor(Integer doctorId);

    @Query("SELECT new com.app.helper.SumOfEveryMedicine (p.idMedicine.name, SUM(p.idMedicine.price * p.amount)) from PrescriptionItem p group by p.idMedicine")
    List <SumOfEveryMedicine> geSumOfConcreteMedicines();

    @Query("SELECT new com.app.helper.SumOfEveryMedicine (p.idMedicine.name, SUM(p.amount)) from PrescriptionItem p where p.idMedicalVisit.idDoctor.id = :doctorId group by p.idMedicine")
    List <SumOfEveryMedicine> getNumberOfMedicinesByDoctor(Integer doctorId);

    @Query("SELECT SUM(p.idMedicine.price * p.amount) from PrescriptionItem p where p.idMedicalVisit.id = :id")
    Double getTotalPriceOfPrescriptionByMedicalVisitId(Integer id);

    void deleteAllByIdMedicalVisit_Id(Integer id);

}

