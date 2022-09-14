package com.example.Second_PR.controllers;

import com.example.Second_PR.models.Breed;
import com.example.Second_PR.models.Depart;
import com.example.Second_PR.models.Owner;
import com.example.Second_PR.models.Pet;
import com.example.Second_PR.repo.DepartRepository;
import com.example.Second_PR.repo.OwnerRepository;
import com.example.Second_PR.repo.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OwnerController {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private DepartRepository departRepository;

    @GetMapping("/owner")
    public String ownerMain(Model model)
    {
        Iterable<Owner> owners = ownerRepository.findAll();
        model.addAttribute("owners", owners);
        return "owner/owner-main";
    }
    @GetMapping("/owner/add")
    public String ownerAdd(Owner owner, Model model)
    {
        Iterable<Depart> departs = departRepository.findAll();
        model.addAttribute("depart",departs);
        return "owner/owner-add";
    }
    @PostMapping("/owner/add")
    public String ownerDataAdd(@ModelAttribute("owner") @Valid Owner owner, String departname, BindingResult bindingResult)
    {
        Depart departForAdd = departRepository.findByName(departname);
        Owner ownerForAdd = new Owner(owner.getName(),
                owner.getPost(),
                owner.getSex(),
                owner.getAge(),
                owner.getSalary(),
                departForAdd);
        if (bindingResult.hasErrors())
        {
            return "owner/owner-add";
        }
        ownerRepository.save(ownerForAdd);
        return "redirect:/owner";
    }

//    @PostMapping("/owner/add")
//    public String ownerDataAdd(
//            @RequestParam String name,
//            @RequestParam String post,
//            @RequestParam Integer age,
//            @RequestParam Character sex,
//            @RequestParam double salary, Model model)
//    {
//        Owner owner = new Owner(name, post, age, sex, salary);
//        ownerRepository.save(owner);
//        return "redirect:/owner";
//    }

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

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET)
    public String ownerDetails(@PathVariable("id") long id, Model model)
    {
        Optional<Owner> owner = ownerRepository.findById(id);
        ArrayList<Owner> res = new ArrayList<>();
        owner.ifPresent(res::add);
        model.addAttribute("owner", res);
        if(!ownerRepository.existsById(id))
        {
            return "redirect:/owner";
        }
        return "owner/owner-details";
    }
//    @RequestMapping(value = "/owner/{id}/edit", method = RequestMethod.GET)
//
//    public String ownerEdit(@PathVariable("id")long id,
//                           Model model)
//    {
//        if(!ownerRepository.existsById(id)){
//            return "redirect:/owner";
//        }
//        Optional<Owner> owner = ownerRepository.findById(id);
//        ArrayList<Owner> res = new ArrayList<>();
//        owner.ifPresent(res::add);
//        model.addAttribute("owner",res);
//        return "owner/owner-edit";
//    }

    @RequestMapping(value = "/owner/{id}/edit", method = RequestMethod.GET)

    public String ownerEdit(@PathVariable("id")long id,
                            Model model)
    {
        Owner owner = ownerRepository.findById(id).orElseThrow();
        model.addAttribute("owner", owner);

        return "owner/owner-edit";
    }
    @RequestMapping(value = "/owner/{id}/edit", method = RequestMethod.POST)

    public String ownerDataUpdate(@ModelAttribute("owner") @Valid Owner owner, BindingResult bindingResult,
                                  @PathVariable("id")long id)
    {
        owner.setId(id);
        if (bindingResult.hasErrors())
        {
            return "owner/owner-edit";
        }
        ownerRepository.save(owner);
        return "redirect:/owner";
    }

    //    @RequestMapping(value = "/owner/{id}/edit", method = RequestMethod.POST)
//
//    public String ownerDataUpdate(@PathVariable("id")long id,
//                                  @RequestParam String name,
//                                  @RequestParam String post,
//                                  @RequestParam Integer age,
//                                  @RequestParam Character sex,
//                                  @RequestParam double salary, Model model)
//    {
//        Owner owner = ownerRepository.findById(id).orElseThrow();
//        owner.setName(name);
//        owner.setPost(post);
//        owner.setAge(age);
//        owner.setSex(sex);
//        owner.setSalary(salary);
//        ownerRepository.save(owner);
//        return "redirect:/owner";
//    }
    @RequestMapping(value = "/owner/{id}/remove", method = RequestMethod.POST)

    public String ownerDataDelete(@PathVariable("id") long id, Model model){
        Owner owner = ownerRepository.findById(id).orElseThrow();
        ownerRepository.delete(owner);
        return "redirect:/owner";
    }
}
