package com.app.controller;


import com.app.model.Address;
import com.app.model.Doctor;
import com.app.repository.AddressRepository;
import com.app.repository.DoctorRepository;
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
public class DoctorController {


    private final DoctorRepository doctorRepository;

    private final AddressRepository addressRepository;

    public DoctorController(DoctorRepository doctorRepository, AddressRepository addressRepository) {
        this.doctorRepository = doctorRepository;
        this.addressRepository = addressRepository;
    }


    @RequestMapping(value = "/addDoctor", method = RequestMethod.GET)
    public String addDoctor(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("doctor", new Doctor());

        return "doctor/add-doctor";
    }

    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
    public String addDoctor(@Valid @ModelAttribute("doctor") Doctor doctor, @Valid @ModelAttribute("address") Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "doctor/add-doctor";
        }
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        addressRepository.save(address);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- ADD ADDRESS BEFORE DOCTOR: " + executionTime);

        doctor.setAddress(address);
        millisActualTime = System.currentTimeMillis();
        doctorRepository.save(doctor);
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- ADD DOCTOR: " + executionTime);

        return "welcome";
    }



    @RequestMapping(value = "/showDoctors", method = RequestMethod.GET)
    public String showDoctor(Model model){

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<Doctor> doctors = doctorRepository.findAll();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- QUERY FIND ALL DOCTORS: " + executionTime);

        model.addAttribute("doctors", doctors);
        return "doctor/show-doctors";
    }

    @RequestMapping(value = "/showDoctor/{id}", method = RequestMethod.GET)
    public String showDoctor(@PathVariable("id") String id, Model model){

        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        Doctor doctor = doctorRepository.findFirstById(id);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- QUERY FIND ONE DOCTOR: " + executionTime);
        model.addAttribute("doctor", doctor);

        return "doctor/show-doctor";
    }

    @RequestMapping(value = "/editDoctor/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable("id") String id, Model model){
        Doctor doctor = doctorRepository.findFirstById(id);
        model.addAttribute("doctor", doctor);
        model.addAttribute("address", doctor.getAddress());

        return "doctor/edit-doctor";
    }

    @RequestMapping(value = "/editDoctor/{id}", method = RequestMethod.PATCH)
    public String edit(@Valid @ModelAttribute("doctor") Doctor doctor, @Valid @ModelAttribute("address") Address address, Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        addressRepository.save(address);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- EDIT ADDRESS BEFORE DOCTOR: " + executionTime);
        doctor.setAddress(address);

        millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        doctorRepository.save(doctor);
        executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- EDIT DOCTOR: " + executionTime);

        model.addAttribute("doctor", doctor);

        return "doctor/show-doctor";
    }


    @RequestMapping(value = "/deleteDoctor/{id}", method = RequestMethod.DELETE)
    public String deleteDoctorById(@PathVariable("id") String id, Model model){
        Doctor doctor = doctorRepository.findFirstById(id);
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        doctorRepository.delete(doctor);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- DELETE DOCTOR: " + executionTime);

        model.addAttribute("doctors", doctorRepository.findAll());

        return "doctor/show-doctors";
    }

}
