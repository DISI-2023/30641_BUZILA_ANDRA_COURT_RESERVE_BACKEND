package com.example._Buzila_Andra_Court_Reserve_Backend.services;


import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
