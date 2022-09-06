package com.example.Second_PR.controllers;

import com.example.Second_PR.models.Owner;
import com.example.Second_PR.models.Pet;
import com.example.Second_PR.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.Second_PR.repo.PetRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/pet")
    public String petMain(Model model)
    {
        Iterable<Pet> pets = petRepository.findAll();
       model.addAttribute("pets", pets);
        return "pet-main";
    }
    @GetMapping("/pet/add")
    public String petAdd(Model model)
    {
        return "pet-add";
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
        return "pet-filter";
    }

    @PostMapping("/pet/filter/result")
    public String petResult(@RequestParam String name, Model model)
    {
        List<Pet> result = petRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "pet-filter";
    }

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owner")
    public String ownerMain(Model model)
    {
        Iterable<Owner> owners = ownerRepository.findAll();
        model.addAttribute("owners", owners);
        return "owner-main";
    }
    @GetMapping("/owner/add")
    public String ownerAdd(Model model)
    {
        return "owner-add";
    }
    @PostMapping("/owner/add")
    public String ownerDataAdd(
            @RequestParam String name,
            @RequestParam String post,
            @RequestParam Integer age,
            @RequestParam Character sex,
            @RequestParam double salary, Model model)
    {
        Owner owner = new Owner(name, post, age, sex, salary);
        ownerRepository.save(owner);
        return "redirect:/owner";
    }

    @GetMapping("/owner/filter")
    public String ownerFilter(Model model)
    {
        return "owner-filter";
    }

    @PostMapping("/owner/filter/result")
    public String ownerResult(@RequestParam String name, Model model)
    {
        List<Owner> result = ownerRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "owner-filter";
    }

}
