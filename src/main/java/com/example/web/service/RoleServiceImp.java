package com.example.web.service;

import com.example.web.model.Role;
import com.example.web.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Set<Role> allRoles() {
        List<Role> list = roleRepository.findAll();
        return new HashSet<>(list);
    }

}