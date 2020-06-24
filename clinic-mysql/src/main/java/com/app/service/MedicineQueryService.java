package com.app.service;

import com.app.helper.PersonInfoHelper;
import com.app.helper.SumOfEveryMedicine;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineQueryService {

    private final JdbcTemplate jdbcTemplate;

    public MedicineQueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SumOfEveryMedicine> getInfo(){
        return jdbcTemplate.query(
                "select medicine.name, sum(medicine.price * prescription_item.amount) as sum_medicine\n" +
                "from medicine, prescription_item\n" +
                "where medicine.id = prescription_item.id_medicine\n" +
                "group by medicine.id", new QueryMedicineMapper());
    }

    public Double getSumOfMedicines(){
        return jdbcTemplate.queryForObject("select sum(medicine.price) from medicine", Double.class
        );
    }

    public List<SumOfEveryMedicine> getNumberOfMedicinesByDoctor(Integer doctorId){
        return jdbcTemplate.query(
                "select medicine.name, sum(prescription_item.amount) as sum_medicine\n" +
                        "from medicine, prescription_item, medical_visit\n" +
                        "where medicine.id = prescription_item.id_medicine\n" +
                        "and prescription_item.id_medical_visit = medical_visit.id\n" +
                        "and medical_visit.id_doctor = " + doctorId +"\n" +
                        "group by medicine.id", new QueryMedicineMapper()
        );
    }

    public List<PersonInfoHelper> countDoctorsNumberOfVisits(){
        return jdbcTemplate.query(
                "select doctor.first_name, doctor.last_name, count(medical_visit.id) as visits\n" +
                        "from medical_visit, doctor\n" +
                        "where medical_visit.id_doctor = doctor.id\n" +
                        "group by doctor.id", new QueryPersonMapper()
        );
    }


}

