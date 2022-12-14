package com.example.Second_PR.controllers;

import com.example.Second_PR.models.Breed;
import com.example.Second_PR.models.Owner;
import com.example.Second_PR.models.Pet;
import com.example.Second_PR.models.Therapy;
import com.example.Second_PR.repo.BreedRepository;
import com.example.Second_PR.repo.PerRepo;
import com.example.Second_PR.repo.TherapyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.Second_PR.repo.PetRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private PerRepo perRepo;

    @Autowired
    private TherapyRepository therapyRepository;

    @GetMapping("/pet")
    public String petMain(Model model)
    {
        Iterable<Pet> pets = petRepository.findAll();
       model.addAttribute("pets", pets);
        return "pet/pet-main";
    }
    @GetMapping("/pet/add")
    public String petAdd(Pet pet, Model model)
    {
        Iterable<Breed> breeds = breedRepository.findAll();
        model.addAttribute("breed",breeds);
        return "pet/pet-add";
    }
    @PostMapping("/pet/add")
    public String petDataAdd( @ModelAttribute("pet") @Valid Pet pet, @RequestParam String breedname, BindingResult bindingResult)
    {
        Breed breedForAdd = breedRepository.findByName(breedname);
        Pet petForAdd = new Pet(pet.getName(), breedForAdd, pet.getAge(), pet.getSex(), pet.getSick());
        if (bindingResult.hasErrors())
        {
            return "pet/pet-add";
        }
        petRepository.save(petForAdd);
        return "redirect:/pet";
    }

    @GetMapping("/pet/filter")
    public String petFilter(Model model)
    {
        return "pet/pet-filter";
    }

    @PostMapping("/pet/filter/resultcontains")
    public String petResultContains(@RequestParam String name, Model model)
    {
        List<Pet> result = petRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "pet/pet-filter";
    }

    @PostMapping("/pet/filter/result")
    public String petResult(@RequestParam String name, Model model)
    {
        List<Pet> result = petRepository.findByName(name);
        model.addAttribute("result", result);
        return "pet/pet-filter";
    }
    @RequestMapping(value = "/pet/{id}", method = RequestMethod.GET)
    public String petDetails(@PathVariable("id") long id, Model model)
    {
        Optional<Pet> pet = petRepository.findById(id);
        ArrayList<Pet> res = new ArrayList<>();
        pet.ifPresent(res::add);
        model.addAttribute("pet", res);
        if(!petRepository.existsById(id))
        {
            return "redirect:/pet";
        }
        return "pet/pet-details";
    }
    @RequestMapping(value = "/pet/{id}/edit", method = RequestMethod.GET)

    public String petEdit(@PathVariable("id")long id,
                          Model model)
    {
        Pet pet = petRepository.findById(id).orElseThrow();
        model.addAttribute("pet", pet);
        return "pet/pet-edit";
    }
    @RequestMapping(value = "/pet/{id}/edit", method = RequestMethod.POST)

    public String petDataUpdate(@ModelAttribute("pet") @Valid Pet pet, BindingResult bindingResult,
                                @PathVariable("id")long id)
    {
        pet.setId(id);
        if (bindingResult.hasErrors())
        {
            return "pet/pet-edit";
        }
        petRepository.save(pet);
        return "redirect:/pet";
    }
    @RequestMapping(value = "/pet/{id}/remove", method = RequestMethod.POST)

    public String petDataDelete(@PathVariable("id") long id, Model model){
        Pet pet = petRepository.findById(id).orElseThrow();
        petRepository.delete(pet);
        return "redirect:/pet";
    }
    @GetMapping("/therapy/pet")
    private String therapyPet(Model model){
        Iterable<Pet> pets = petRepository.findAll();
        model.addAttribute("pets", pets);
        Iterable<Therapy> therapies = therapyRepository.findAll();
        model.addAttribute("therapies", therapies);
        return "pet/pet-therapy";
    }

    @PostMapping("/therapy/pet")
    public String therapyPetAdd(@RequestParam String pet, @RequestParam String therapy, Model model)
    {
        Pet pet1 = perRepo.findByName(pet);
        Therapy therapy1 = therapyRepository.findByName(therapy);
        pet1.getTherapy().add(therapy1);
        therapy1.getPets().add(pet1);
        petRepository.save(pet1);
        therapyRepository.save(therapy1);
        return "redirect:/pet";
    }



}
