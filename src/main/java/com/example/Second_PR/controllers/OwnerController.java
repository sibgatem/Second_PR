package com.example.Second_PR.controllers;

import com.example.Second_PR.models.Owner;
import com.example.Second_PR.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owner")
    public String ownerMain(Model model)
    {
        Iterable<Owner> owners = ownerRepository.findAll();
        model.addAttribute("owners", owners);
        return "owner/owner-main";
    }
    @GetMapping("/owner/add")
    public String ownerAdd(Model model)
    {
        return "owner/owner-add";
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
        return "owner/owner-filter";
    }

    @PostMapping("/owner/filter/result")
    public String ownerResult(@RequestParam String name, Model model)
    {
        List<Owner> result = ownerRepository.findByName(name);
        model.addAttribute("result", result);
        return "owner/owner-filter";
    }

    @PostMapping("/owner/filter/resultcontains")
    public String ownerResultContains(@RequestParam String name, Model model)
    {
        List<Owner> result = ownerRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "owner/owner-filter";
    }
}
