package com.springboot.api.controller;

import com.springboot.api.dao.HospitalDao;
import com.springboot.api.domain.Hospital;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalController {

    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Hospital> get(@PathVariable Integer id) {
        Hospital hospital = hospitalDao.findById(id);
        Optional<Hospital> opt = Optional.of(hospital);

        if(!opt.isEmpty()) {
            return ResponseEntity.ok().body(hospital);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Hospital());
        }
    }


}
