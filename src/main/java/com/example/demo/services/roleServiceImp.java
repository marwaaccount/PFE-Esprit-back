package com.example.demo.services;

import com.example.demo.Models.role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.demo.Repositories.RoleRepository;
@Service
public class roleServiceImp implements roleService{

    @Autowired
    RoleRepository RoleRepository;
    @Override
    public List<role> getRoles() {
       return RoleRepository.findAll();
    }
}
