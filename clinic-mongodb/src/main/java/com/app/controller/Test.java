package com.app.controller;

import com.app.repository.DoctorRepository;
import com.app.service.GenerationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    private final GenerationService generationService;


    private final DoctorRepository doctorRepository;

    public Test(GenerationService generationService, DoctorRepository doctorRepository) {
        this.generationService = generationService;
        this.doctorRepository = doctorRepository;
    }


    @GetMapping("/")
    public String main(Model model) {
        return "welcome"; //view
    }

    @GetMapping("/queryTest")
    public String test(Model model) {
        model.addAttribute("message", "Witamy serdecznie na stronie przychodni!");
        System.out.println(doctorRepository.countNumberOfVisitsByDoctors());
        return "welcome"; //view
    }


    @GetMapping("/addToDB")
    public String addToDB(Model model) {

        try {
            long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
            generationService.generateAll();
            long executionTime = System.currentTimeMillis() - millisActualTime; //
            System.out.println("Generate Time: " + executionTime);


        } catch (Exception ex) {
            model.addAttribute("message", ex.toString());
        }

        return "welcome"; //view
    }


}