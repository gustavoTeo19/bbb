package com.example.barberboost.Controllers;


import com.example.barberboost.dtos.BarberDto;
import com.example.barberboost.models.BarberModel;
import com.example.barberboost.service.BarberService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/barber")
public class BarberContoller {
    final BarberService barberService;

    public BarberContoller(BarberService barberService) {
        this.barberService = barberService;
    }

    @PostMapping
    public ResponseEntity<Object> saveBarber(@RequestBody @Valid BarberDto barberDto){
        if(barberService.existsByCpf(barberDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
        }
        var barberModel = new BarberModel();
        BeanUtils.copyProperties(barberDto, barberModel);
        barberModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        barberModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(barberService.save(barberModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllBarbers(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(barberService.findAllByDelete(pageable));
    }

    @GetMapping("/name")
    public ResponseEntity<Object> getAllBarbersByName(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(barberService.findAllByDeleteOnlyName());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getBarber(@PathVariable(value = "id") UUID id){
        Optional<BarberModel> barberModelOptional = barberService.findById(id);
        if(!barberModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Barber not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(barberModelOptional.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateBarber(@PathVariable(value = "id") UUID id,@RequestBody @Valid BarberDto barberDto){
        Optional<BarberModel> barberModelOptional = barberService.findById(id);
        if(!barberModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Barber not found!");
        }
        BarberModel barberModel = new BarberModel();
        BeanUtils.copyProperties(barberDto, barberModel);
        barberModel.setId(barberModelOptional.get().getId());
        barberModel.setRegistrationDate(barberModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(barberService.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBarber(@PathVariable(value = "id") UUID id){
        Optional<BarberModel> barberModelOptional = barberService.findById(id);
        if(!barberModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Barber not found!");
        }
        BarberModel barberModel = new BarberModel();
        BeanUtils.copyProperties(barberModelOptional.get(), barberModel);
        barberModel.setId(barberModelOptional.get().getId());
        barberModel.setRegistrationDate(barberModelOptional.get().getRegistrationDate());
        barberModel.setDelete(true);
        return ResponseEntity.status(HttpStatus.OK).body(barberService.save(barberModel));
    }

    }
