package com.example.cricket.service;


import com.example.cricket.entity.Cricketer;
import com.example.cricket.repository.CricketerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CricketerService {
    @Autowired
    CricketerRepo cricketerRepo;

    @Autowired
    Cricketer cricketer;

    public List<Cricketer> allCricketerService()
    {
        System.out.println(cricketerRepo.getAllCricketerRepo());

        return cricketerRepo.getAllCricketerRepo();
    }
    public void addCricketerService(Cricketer cricketer)
    {
        cricketerRepo.addCricketerRepo(cricketer);
        System.out.println("All ok from add cricketer service");
    }
    public void updateCricketerService(String name,Cricketer cricketer)
    {
        cricketerRepo.updateCricketerRepo(name,cricketer);
        System.out.println("All good from update cricketer services");
    }
    public void deleteCricketerServices(String name)
    {
        cricketerRepo.deleteCricketerRepo(name);
        System.out.println("All good delete cricketer service");

    }


}
