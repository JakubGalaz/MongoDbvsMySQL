package com.app.repository;

import com.app.helper.PersonInfoHelper;
import com.app.model.MedicalVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalVisitRepository extends JpaRepository<MedicalVisit,Integer> {
    MedicalVisit findFirstById(Integer id);

    @Query("SELECT new com.app.helper.PersonInfoHelper(m.idDoctor.firstName, m.idDoctor.lastName, count(m)) from MedicalVisit m group by m.idDoctor")
    List<PersonInfoHelper> countDoctorsNumberOfVisits();

    List<MedicalVisit> findAllByIdPatient_Id(Integer id);
    List <MedicalVisit> findAllByIdDoctor_Id(Integer id);
}
