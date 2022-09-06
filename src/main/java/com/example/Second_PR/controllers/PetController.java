package com.example.Second_PR.controllers;

import com.example.Second_PR.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Second_PR.repo.PetRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/pet")
    public String petMain(Model model)
    {
        Iterable<Pet> pets = petRepository.findAll();
       model.addAttribute("pets", pets);
        return "pet/pet-main";
    }
    @GetMapping("/pet/add")
    public String petAdd(Model model)
    {
        return "pet/pet-add";
    }
    @PostMapping("/pet/add")
    public String petDataAdd(
            @RequestParam String name,
            @RequestParam String breed,
            @RequestParam Integer age,
            @RequestParam Character sex,
            @RequestParam Boolean sick, Model model)
    {
        Pet pet = new Pet(name, breed, age, sex, sick.booleanValue());
        petRepository.save(pet);
        return "redirect:/pet";
    }

    @GetMapping("/pet/filter")
    public String petFilter(Model model)
    {
        return "pet/pet-filter";
    }

    @PostMapping("/pet/filter/result")
    public String petResult(@RequestParam String name, Model model)
    {
        List<Pet> result = petRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "pet/pet-filter";
    }

}
