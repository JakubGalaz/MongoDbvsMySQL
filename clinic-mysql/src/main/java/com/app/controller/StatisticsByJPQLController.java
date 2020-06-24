package com.app.controller;

import com.app.helper.PersonInfoHelper;
import com.app.helper.SumOfEveryMedicine;
import com.app.model.Doctor;
import com.app.repository.DoctorRepository;
import com.app.repository.MedicalVisitRepository;
import com.app.repository.MedicineRepository;
import com.app.repository.PrescriptionItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StatisticsByJPQLController {

    private final MedicalVisitRepository medicalVisitRepository;
    private final DoctorRepository doctorRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;

    public StatisticsByJPQLController(MedicineRepository medicineRepository, PrescriptionItemRepository prescriptionItemRepository, DoctorRepository doctorRepository, MedicalVisitRepository medicalVisitRepository) {
        this.medicineRepository = medicineRepository;
        this.prescriptionItemRepository = prescriptionItemRepository;
        this.doctorRepository = doctorRepository;
        this.medicalVisitRepository = medicalVisitRepository;
    }


    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String getStatistics(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("doctor", new Doctor());

        return "statistics/show-statistics";
    }

    @RequestMapping(value = "/medicineStatisticsJPQL", method = RequestMethod.GET)
    public String getMedicineStatistiscs(Model model) {

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        double sum = medicineRepository.getSumOfMedicines();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & ORM- SUM VALUE MEDICINES: " + executionTime);

        millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<SumOfEveryMedicine> prescriptionItemList = prescriptionItemRepository.geSumOfConcreteMedicines();
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & ORM- TOTAL PRICE OF MEDICINES IN PRESCRIPTIONS: " + executionTime);

        model.addAttribute("medicineSum",  sum);
        model.addAttribute("medicineByVisit", prescriptionItemList);

        return "statistics/medicine-statistics";
    }

    @RequestMapping(value = "/medicineStatisticsByDoctorJPQL", method = RequestMethod.POST)
    public String getStatisticsByDoctor(Model model, @Valid @ModelAttribute("doctor") Doctor doctor) {
        long millisActualTime = System.currentTimeMillis();
        List<SumOfEveryMedicine> sumOfEveryMedicineList = prescriptionItemRepository.getNumberOfMedicinesByDoctor(doctor.getId());
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & ORM- SUM OF CURES BY DOCTOR" + doctor.getId() +": " + executionTime);

        model.addAttribute("doctor", doctorRepository.findFirstById(doctor.getId()));
        model.addAttribute("sumOfEveryMedicineList", sumOfEveryMedicineList);

        return "statistics/medicine-bydoctor-statistics";
    }

    @RequestMapping(value = "/doctorsMedicalVisitsJPQL", method = RequestMethod.GET)
    public String getDoctorsMedicalVisitsStatistics(Model model) {
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List <PersonInfoHelper> visits = medicalVisitRepository.countDoctorsNumberOfVisits();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL & ORM- COUNT DOCTOR MEDICAL VISITS: " + executionTime);

        model.addAttribute("visits", visits);

        return "statistics/doctor-visit-statistics";
    }



}
