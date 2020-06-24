package com.app.controller;

import com.app.helper.PersonInfoHelper;
import com.app.helper.SumOfEveryMedicine;
import com.app.model.Doctor;
import com.app.repository.DoctorRepository;
import com.app.service.MedicineQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StatisticsBySQLController {

    private final DoctorRepository doctorRepository;
    private final MedicineQueryService medicineQueryService;

    public StatisticsBySQLController(MedicineQueryService medicineQueryService, DoctorRepository doctorRepository) {
        this.medicineQueryService = medicineQueryService;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(value = "/medicineStatisticsSQL", method = RequestMethod.GET)
    public String getMedicineStatistics(Model model) {


        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        double sum = medicineQueryService.getSumOfMedicines();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & jdbcTemplate- SUM VALUE MEDICINES: " + executionTime);


        millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<SumOfEveryMedicine> sumOfEveryMedicines = medicineQueryService.getInfo();
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & jdbcTemplate- TOTAL PRICE OF MEDICINES IN PRESCRIPTIONS: " + executionTime);

        model.addAttribute("medicineSum",  sum);
        model.addAttribute("medicineByVisit", sumOfEveryMedicines);

        return "statistics/medicine-statistics";
    }

    @RequestMapping(value = "/medicineStatisticsByDoctorSQL", method = RequestMethod.POST)
    public String getStatisticsByDoctor(Model model, @Valid @ModelAttribute("doctor") Doctor doctor) {
        long millisActualTime = System.currentTimeMillis();
        List<SumOfEveryMedicine> sumOfEveryMedicineList = medicineQueryService.getNumberOfMedicinesByDoctor(doctor.getId());
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & jdbcTemplate- SUM OF CURES BY DOCTOR " + doctor.getId() +": " + executionTime);

        model.addAttribute("doctor", doctorRepository.findFirstById(doctor.getId()));
        model.addAttribute("sumOfEveryMedicineList", sumOfEveryMedicineList);

        return "statistics/medicine-bydoctor-statistics";
    }

    @RequestMapping(value = "/doctorsMedicalVisitsSQL", method = RequestMethod.GET)
    public String getDoctorsMedicalVisitsStatistics(Model model) {
        long millisActualTime = System.currentTimeMillis();
        List <PersonInfoHelper> visits = medicineQueryService.countDoctorsNumberOfVisits();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & jdbcTemplate- COUNT DOCTOR MEDICAL VISITS: " + executionTime);

        model.addAttribute("visits", visits);

        return "statistics/doctor-visit-statistics";
    }
}
