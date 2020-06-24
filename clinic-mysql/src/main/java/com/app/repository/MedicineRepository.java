package com.app.repository;

import com.app.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicineRepository extends JpaRepository<Medicine,Integer> {
    Medicine findFirstById(Integer id);

    @Query("SELECT SUM(c.price) from Medicine c")
    Double getSumOfMedicines();

//    @Query("SELECT m.id, sum(c.price * p.amount) from Medicine c, MedicalVisit m, PrescriptionItem p where p.idMedicine.idMedicine = c.id and p.idMedicalVisit.idMedicalVisit = m.id group by m.id")
//    String[] getSumes();

    @Query("SELECT m.name, SUM(m.price * p.amount) from Medicine m, MedicalVisit v, PrescriptionItem p where m.id = p.idMedicine.id AND p.idMedicalVisit.id = v.id group by m.name")
    String[] geSumOfConcreteMedicines();
}
