package com.app.controller;

import com.app.service.GenerationService;
import com.app.service.MedicineQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Test {

    private final GenerationService generationService;

    private final MedicineQueryService medicineQueryService;

    public Test(GenerationService generationService, MedicineQueryService medicineQueryService) {
        this.generationService = generationService;
        this.medicineQueryService = medicineQueryService;
    }


    @GetMapping("/addToDB")
    public String addToDB(Model model) {

        try{
            long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
            generationService.generateAll();
            long executionTime = System.currentTimeMillis() - millisActualTime; //
            System.out.println("Generate Time: " + executionTime);


        }catch(Exception ex){
            ex.printStackTrace();
        }

        return "welcome"; //view
    }

    @GetMapping("/query")
    public String queryTest(){
        medicineQueryService.getInfo();

        return "redirect:/";
    }

    @GetMapping("/")
    public String main(){
        return "welcome";
    }
}