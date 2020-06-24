package com.app.controller;

import com.app.model.Address;
import com.app.model.MedicalVisit;
import com.app.model.Patient;
import com.app.repository.AddressRepository;
import com.app.repository.MedicalVisitRepository;
import com.app.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;
    private final MedicalVisitRepository medicalVisitRepository;

    public PatientController(PatientRepository patientRepository, AddressRepository addressRepository, MedicalVisitRepository medicalVisitRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
        this.medicalVisitRepository = medicalVisitRepository;
    }


    @RequestMapping(value = "/addPatient", method = RequestMethod.GET)
    public String addPatient(Model model){
        model.addAttribute("address", new Address());
        model.addAttribute("patient", new Patient());

        return "patient/add-patient";
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public String addPatient(@Valid @ModelAttribute("patient") Patient patient, @Valid @ModelAttribute("address") Address address, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "patient/add-patient";
        }

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        addressRepository.save(address);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- ADD ADDRESS: " + executionTime);

        patient.setAddress(address);
        millisActualTime = System.currentTimeMillis();
        patientRepository.save(patient);
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- ADD PATIENT: " + executionTime);

        return "welcome";
    }

    @RequestMapping(value = "/showPatients", method = RequestMethod.GET)
    public String showPatients(Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<Patient> patients = patientRepository.findAll();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- QUERY FIND ALL PATIENTS: " + executionTime);
        model.addAttribute("patients", patients);

        return "patient/show-patients";
    }

    @RequestMapping(value = "/showPatient/{id}", method = RequestMethod.GET)
    public String showPatient(@PathVariable("id") Integer id, Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        Patient patient = patientRepository.findFirstById(id);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- QUERY FIND ONE PATIENT: " + executionTime);
        model.addAttribute("patient", patient);

        return "patient/show-patient";
    }

    @RequestMapping(value = "/editPatient/{id}", method = RequestMethod.GET)
    public String editPatient(@PathVariable("id") Integer id, Model model){
        Patient patient = patientRepository.findFirstById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("address", patient.getAddress());

        return "patient/edit-patient";
    }

    @RequestMapping(value = "/editPatient/{id}", method = RequestMethod.PATCH)
    public String edit(@PathVariable("id") Integer id, @Valid @ModelAttribute("patient") Patient patient, @Valid @ModelAttribute("address") Address address, Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        addressRepository.save(address);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- EDIT ADDRESS: " + executionTime);

        patient.setAddress(address);

        millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        patientRepository.save(patient);
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- EDIT PATIENT: " + executionTime);

        model.addAttribute("patient", patient);

        return "patient/show-patient";
    }


    @RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.DELETE)
    public String deletePatient(@PathVariable("id") Integer id, Model model){
        Patient patient = patientRepository.findFirstById(id);
        List <MedicalVisit> medicalVisits = medicalVisitRepository.findAllByIdPatient_Id(id);

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        for(MedicalVisit medicalVisit: medicalVisits){
            medicalVisit.setIdPatient(null);
            medicalVisitRepository.save(medicalVisit);
        }
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- DELETE PATIENT FROM MEDICAL VISITS: " + executionTime);

        millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        patientRepository.delete(patient);
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MySQL- DELETE PATIENT: " + executionTime);

        model.addAttribute("patients", patientRepository.findAll());

        return "patient/show-patients";
    }
}
