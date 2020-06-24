package com.app.controller;

import com.app.model.*;
import com.app.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MedicalVisitController {

    private final OutpatientClinicRepository outpatientClinicRepository;
    private final PatientRepository patientRepository;
    private final MedicalVisitRepository medicalVisitRepository;
    private final DoctorRepository doctorRepository;
    private final MedicineRepository medicineRepository;


    public MedicalVisitController(PatientRepository patientRepository, MedicalVisitRepository medicalVisitRepository, DoctorRepository doctorRepository, OutpatientClinicRepository outpatientClinicRepository, MedicineRepository medicineRepository) {
        this.patientRepository = patientRepository;
        this.medicalVisitRepository = medicalVisitRepository;
        this.doctorRepository = doctorRepository;
        this.outpatientClinicRepository = outpatientClinicRepository;
        this.medicineRepository = medicineRepository;
    }


    @RequestMapping(value = "/addMedicalVisit", method = RequestMethod.GET)
    public String addMedicalVisit(Model model) {

        prepareModel(model);

        model.addAttribute("medicalVisit", new MedicalVisit());


        return "medicalVisit/add-medicalVisit";
    }


    @RequestMapping(value = "/addMedicalVisit", method = RequestMethod.POST)
    public String addMedicalVisit(@Valid @ModelAttribute("medicalVisit") MedicalVisit medicalVisit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-medicalVisit";
        }
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicalVisitRepository.save(medicalVisit);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- ADD MEDICAL VISIT: " + executionTime);

        return "welcome";
}

    @RequestMapping(value = "/showMedicalVisits", method = RequestMethod.GET)
    public String getMedicalVisits(Model model) {

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<MedicalVisit> medicalVisits = medicalVisitRepository.findAll();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- QUERY FIND ALL MEDICAL VISITS: " + executionTime);

        model.addAttribute("medicalVisits", medicalVisits);

        return "medicalVisit/show-medicalVisits";
    }

    @RequestMapping(value = "/showMedicalVisit/{id}", method = RequestMethod.GET)
    public String showMedicalVisit(@PathVariable("id") String id, Model model){

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- QUERY FIND ONE MEDICAL VISIT: " + executionTime);

        model.addAttribute("medicalVisit", medicalVisit);
        model.addAttribute("medicines", medicineRepository.findAll());
        model.addAttribute("item", new PrescriptionItem());
        model.addAttribute("value", medicineRepository.calculatePrescriptionValue(medicalVisit.getId()));

        return "medicalVisit/show-medicalVisit";
    }

    @RequestMapping(value = "/editMedicalVisit/{id}", method = RequestMethod.GET)
    public String prepareToEditMedicalVisit(@PathVariable("id") String id, Model model){
        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);
        prepareModel(model);

        model.addAttribute("medicalVisit", medicalVisit);

        return "medicalVisit/edit-medicalVisit";
    }

    private void prepareModel(Model model) {
        List<Patient> patients = patientRepository.findAll();
        List<Doctor> doctors = doctorRepository.findAll();
        List <OutpatientClinic> outpatientClinics = outpatientClinicRepository.findAll();

        model.addAttribute("patients", patients);
        model.addAttribute("doctors", doctors);
        model.addAttribute("outpatientClinics", outpatientClinics);
    }

    @RequestMapping(value = "/editMedicalVisit/{id}", method = RequestMethod.PATCH)
    public String edit(@PathVariable("id") String id, @Valid @ModelAttribute("medicalVisit") MedicalVisit medicalVisit, Model model){
        medicalVisit.setPrescriptionItems(null);
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicalVisitRepository.save(medicalVisit);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- EDIT MEDICAL VISIT: " + executionTime);

        model.addAttribute("medicalVisit", medicalVisit);
        model.addAttribute("medicines", medicineRepository.findAll());
        model.addAttribute("item", new PrescriptionItem());

        return "medicalVisit/show-medicalVisit";
    }

    @RequestMapping(value = "/addMedicineToMedicalVisit/{id}", method = RequestMethod.PUT)
    public String addMedicineToMedicalVisit(@PathVariable("id") String id, Model model ,@ModelAttribute("item") PrescriptionItem prescriptionItem){

        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);
        List<PrescriptionItem> prescriptionItems = medicalVisit.getPrescriptionItems();
        if(prescriptionItems == null){
            prescriptionItems = new ArrayList<>();
        }
        prescriptionItems.add(prescriptionItem);
        medicalVisit.setPrescriptionItems(prescriptionItems);

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicalVisitRepository.save(medicalVisit);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- ADD MEDICINE TO PRESCRIPTION: " + executionTime);

        return "redirect:/showMedicalVisit/" + id;
    }

    @RequestMapping(value = "/deleteMedicalVisit/{id}", method = RequestMethod.DELETE)
    public String deleteMedicalVisit(@PathVariable("id") String id, Model model){
        MedicalVisit medicalVisit = medicalVisitRepository.findFirstById(id);

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicalVisitRepository.delete(medicalVisit);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- DELETE MedicalVisit: " + executionTime);

        List<MedicalVisit> medicalVisits = medicalVisitRepository.findAll();
        model.addAttribute("medicalVisits", medicalVisits);

        return "medicalVisit/show-medicalVisits";
    }



}
