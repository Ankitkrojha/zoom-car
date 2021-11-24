package com.example.cricket.controller;


import com.example.cricket.entity.Cricketer;
import com.example.cricket.service.CricketerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CricketerController {

    @Autowired
    CricketerService cricketerService;
    //Cricketer cricketer;


    @GetMapping("allCricketer")
    public List<Cricketer> getAllCricketer()
    {
        System.out.println("Called");
        System.out.println(cricketerService.allCricketerService());

        return cricketerService.allCricketerService();

    }


    @PostMapping("addCricketer")
    public String addCricketer(@RequestBody Cricketer cricketer)
    {

        cricketerService.addCricketerService(cricketer);
        return "Cricketer added "+ cricketer.getName();

    }

    @PutMapping("updateCricketer/{name}")
    public String updateCricketer(@PathVariable String name,@RequestBody Cricketer cricketer)
    {
        cricketerService.updateCricketerService(name,cricketer);
        return "Role update to "+cricketer.getRole();

    }
    @DeleteMapping("deleteCricketer/{name}")
    public String deleteCricketer(@PathVariable String name)
    {
        cricketerService.deleteCricketerServices(name);


        return "Cricketer "+name+" deleted";
    }
}
