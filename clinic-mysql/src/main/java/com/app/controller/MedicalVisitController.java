package com.app.controller;

import com.app.model.*;
import com.app.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MedicalVisitController {

    private final PrescriptionItemRepository prescriptionItemRepository;
    private final MedicineRepository medicineRepository;
    private final OutpatientClinicRepository outpatientClinicRepository;
    private final PatientRepository patientRepository;
    private final MedicalVisitRepository medicalVisitRepository;
    private final DoctorRepository doctorRepository;


    public MedicalVisitController(PatientRepository patientRepository, MedicalVisitRepository medicalVisitRepository, DoctorRepository doctorRepository, OutpatientClinicRepository outpatientClinicRepository, MedicineRepository medicineRepository, PrescriptionItemRepository prescriptionItemRepository) {
        this.patientRepository = patientRepository;
        this.medicalVisitRepository = medicalVisitRepository;
        this.doctorRepository = doctorRepository;
        this.outpatientClinicRepository = outpatientClinicRepository;
        this.medicineRepository = medicineRepository;
        this.prescriptionItemRepository = prescriptionItemRepository;
    }


    @RequestMapping(value = "/addMedicalVisit", method = RequestMethod.GET)
    public String addMedicalVisit(Model model) {

        prepareModel(model);

        model.addAttribute("medicalVisit", new MedicalVisit());


//        System.out.println(patients.get(1));

        return "medicalVisit/add-medicalVisit";
    }

    private void prepareModel(Model model) {
        List<Patient> patients = patientRepository.findAll();
        List<Doctor> doctors = doctorRepository.findAll();
        List <OutpatientClinic> outpatientClinics = outpatientClinicRepository.findAll();

        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        model.addAttribute("outpatientClinics", outpatientClinics);
    }


    @RequestMapping(value = "/showMedicalVisits", method = RequestMethod.GET)
    public String getMedicalVisits(Model model) {
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List <MedicalVisit> medicalVisits = medicalVisitRepository.findAll();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- QUERY FIND ALL MEDICAL VISITS: " + executionTime);

        model.addAttribute("medicalVisits", medicalVisits);

        return "medicalVisit/show-medicalVisits";
    }

    @RequestMapping(value = "/addMedicalVisit", method = RequestMethod.POST)
    public String addMedicalVisit(@Valid @ModelAttribute("medicalVisit") MedicalVisit medicalVisit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "medicalVisit/add-medicalVisit";
        }

        medicalVisitRepository.save(medicalVisit);

        return "redirect:/";
    }
    @RequestMapping(value = "/showMedicalVisit/{id}", method = RequestMethod.GET)
    public String showMedicalVisit(@PathVariable("id") Integer id, Model model){

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- QUERY FIND ONE MEDICAL VISIT: " + executionTime);

        model.addAttribute("medicalVisit", medicalVisit);
        model.addAttribute("medicines", medicineRepository.findAll());
        model.addAttribute("item", new PrescriptionItem());
        model.addAttribute("value", prescriptionItemRepository.getTotalPriceOfPrescriptionByMedicalVisitId(medicalVisit.getId()));


        return "medicalVisit/show-medicalVisit";
    }


    @RequestMapping(value = "/editMedicalVisit/{id}", method = RequestMethod.GET)
    public String prepareToEditMedicalVisit(@PathVariable("id") Integer id, Model model){
        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);
        prepareModel(model);

        model.addAttribute("medicalVisit", medicalVisit);

        return "medicalVisit/edit-medicalVisit";
    }

    @Transactional
    @RequestMapping(value = "/editMedicalVisit/{id}", method = RequestMethod.PATCH)
    public String edit(@PathVariable("id") Integer id, @Valid @ModelAttribute("medicalVisit") MedicalVisit medicalVisit, Model model){
        prescriptionItemRepository.deleteAllByIdMedicalVisit_Id(id);
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicalVisitRepository.save(medicalVisit);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- EDIT MedicalVisit: " + executionTime);

        model.addAttribute("medicalVisit", medicalVisit);
        model.addAttribute("medicines", medicineRepository.findAll());
        model.addAttribute("item", new PrescriptionItem());
        return "medicalVisit/show-medicalVisit";
    }

    @RequestMapping(value = "/addMedicineToMedicalVisit/{id}", method = RequestMethod.PUT)
    public String addMedicineToMedicalVisit(@PathVariable("id") Integer id, Model model ,@ModelAttribute("item") PrescriptionItem prescriptionItem){

        prescriptionItem.setIdMedicalVisit(medicalVisitRepository.findFirstById(id));
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        prescriptionItemRepository.save(prescriptionItem);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- ADD MEDICINE TO PRESCRIPTION: " + executionTime);

        return "redirect:/showMedicalVisit/" + id;
    }

    @Transactional
    @RequestMapping(value = "/deleteMedicalVisit/{id}", method = RequestMethod.DELETE)
    public String deleteMedicalVisit(@PathVariable("id") Integer id, Model model){
        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        prescriptionItemRepository.deleteAllByIdMedicalVisit_Id(medicalVisit.getId());
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- DELETE PRESCRIPTION ITEMS BEFORE DELETE MEDICAL VISIT: " + executionTime);

        millisActualTime = System.currentTimeMillis();
        medicalVisitRepository.delete(medicalVisit);
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- DELETE MEDICAL VISIT: " + executionTime);

        List<MedicalVisit> medicalVisits = medicalVisitRepository.findAll();
        model.addAttribute("medicalVisits", medicalVisits);

        return "medicalVisit/show-medicalVisits";
    }


}
