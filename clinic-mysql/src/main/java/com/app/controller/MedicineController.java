package com.app.controller;


import com.app.model.Medicine;
import com.app.model.PharmaceuticalCompany;
import com.app.repository.MedicineRepository;
import com.app.repository.PharmaceuticalCompanyRepository;
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
public class MedicineController {

    private final MedicineRepository medicineRepository;
    private final PharmaceuticalCompanyRepository pharmaceuticalCompanyRepository;



    public MedicineController(MedicineRepository medicineRepository, PharmaceuticalCompanyRepository pharmaceuticalCompanyRepository) {
        this.medicineRepository = medicineRepository;
        this.pharmaceuticalCompanyRepository = pharmaceuticalCompanyRepository;
    }


    @RequestMapping(value = "/addMedicine", method = RequestMethod.GET)
    public String addMedicine(Model model) {

        List <PharmaceuticalCompany> pharmaceuticalCompanies = pharmaceuticalCompanyRepository.findAll();
        model.addAttribute("pharmaceuticalCompanies", pharmaceuticalCompanies);
        model.addAttribute("medicine", new Medicine());

        return "medicine/add-medicine";
    }


    @RequestMapping(value = "/addMedicine", method = RequestMethod.POST)
    public String addMedicine(@Valid @ModelAttribute("medicine") Medicine medicine, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "medicine/add-medicine";
        }
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicineRepository.save(medicine);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- ADD MEDICINE: " + executionTime);

        return "redirect:/";
    }



    @RequestMapping(value = "/showMedicines", method = RequestMethod.GET)
    public String showMedicines(Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        List<Medicine> medicine = medicineRepository.findAll();
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- QUERY FIND ALL MEDICINES: " + executionTime);
        model.addAttribute("medicine", medicine);

        return "medicine/show-medicines";
    }

    @RequestMapping(value = "/showMedicine/{id}", method = RequestMethod.GET)
    public String showMedicine(@PathVariable("id") Integer id, Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        Medicine medicine = medicineRepository.findFirstById(id);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MYSQL- QUERY FIND ONE MEDICINE: " + executionTime);
        model.addAttribute("medicine", medicine);

        return "medicine/show-medicine";
    }

    @RequestMapping(value = "/editMedicine/{id}", method = RequestMethod.GET)
    public String prepareToEditMedicine(@PathVariable("id") Integer id, Model model){
        Medicine medicine = medicineRepository.findFirstById(id);
        List <PharmaceuticalCompany> pharmaceuticalCompanies = pharmaceuticalCompanyRepository.findAll();
        model.addAttribute("medicine", medicine);
        model.addAttribute("pharmaceuticalCompanies", pharmaceuticalCompanies);

        return "medicine/edit-medicine";
    }

    @RequestMapping(value = "/editMedicine/{id}", method = RequestMethod.PATCH)
    public String edit(@PathVariable("id") Integer id, @Valid @ModelAttribute("medicine") Medicine medicine, Model model){
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicineRepository.save(medicine);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- EDIT MEDICINE: " + executionTime);

        model.addAttribute("medicine", medicine);

        return "medicine/show-medicine";
    }

    @RequestMapping(value = "/deleteMedicine/{id}", method = RequestMethod.DELETE)
    public String deleteMedicine(@PathVariable("id") Integer id, Model model){
        Medicine medicine = medicineRepository.findFirstById(id);
        long millisActualTime = System.currentTimeMillis(); // początkowy czas w milisekundach.
        medicineRepository.delete(medicine);
        long executionTime = System.currentTimeMillis() - millisActualTime; //
        System.out.println("MONGO- DELETE MEDICINE: " + executionTime);

        model.addAttribute("medicine", medicineRepository.findAll());

        return "medicine/show-medicines";
    }


}
