package com.app.controller;

import com.app.helper.PersonInfoHelper;
import com.app.helper.SumOfEveryMedicine;
import com.app.model.Doctor;
import com.app.repository.DoctorRepository;
import com.app.repository.MedicineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StatisticsController {

    private final MedicineRepository medicineRepository;
    private final DoctorRepository doctorRepository;

    public StatisticsController(MedicineRepository medicineRepository, DoctorRepository doctorRepository ) {
        this.medicineRepository = medicineRepository;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(value = "/medicineStatistics", method = RequestMethod.GET)
    public String getMedicineStatistics(Model model) {


        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        double sum = medicineRepository.getSumOfMedicines();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- SUM VALUE MEDICINES: " + executionTime);


        millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<SumOfEveryMedicine> sumOfEveryMedicineList = medicineRepository.geSumOfConcreteMedicines();
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- TOTAL PRICE OF MEDICINES IN PRESCRIPTIONS: " + executionTime);

        model.addAttribute("medicineSum",  sum);
        model.addAttribute("sumOfEveryMedicineList", sumOfEveryMedicineList);

        return "statistics/medicine-statistics";
    }

    @RequestMapping(value = "/medicineStatisticsByDoctor", method = RequestMethod.POST)
    public String getStatisticsByDoctor(Model model, @Valid @ModelAttribute("doctor") Doctor doctor) {
        List<SumOfEveryMedicine>  sumOfEveryMedicineList;
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        sumOfEveryMedicineList = medicineRepository.getNumberOfMedicinesByDoctor(doctor.getId());
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- SUM OF CURES BY DOCTOR: " + executionTime);

        model.addAttribute("doctor", doctorRepository.findFirstById(doctor.getId()));
        model.addAttribute("sumOfEveryMedicineList", sumOfEveryMedicineList);
        return "statistics/medicine-bydoctor-statistics";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatistics(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("doctor", new Doctor());

        return "statistics/show-statistics";
    }

    @RequestMapping(value = "/doctorsMedicalVisits", method = RequestMethod.GET)
    public String getDoctorsMedicalVisitsStatistics(Model model) {
        List <PersonInfoHelper> visits;
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        visits = doctorRepository.countNumberOfVisitsByDoctors();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- COUNT DOCTOR'S MEDICAL VISITS: " + executionTime);

        model.addAttribute("visits",visits);

        return "statistics/doctor-visit-statistics";
    }




}
