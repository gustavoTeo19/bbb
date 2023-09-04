package com.example.barberboost.Controllers;


import com.example.barberboost.dtos.AppointmentDto;
import com.example.barberboost.models.AppointmentModel;
import com.example.barberboost.models.BarberModel;
import com.example.barberboost.models.ClientModel;
import com.example.barberboost.models.ServiceModel;
import com.example.barberboost.service.AppointmentService;
import com.example.barberboost.service.BarberService;
import com.example.barberboost.service.ClientService;
import com.example.barberboost.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/appointment")
public class AppointmentController {
    final AppointmentService appointmentService;

    final BarberService barberService;

    final ClientService clientService;
    final ServiceService serviceService;

    public AppointmentController(AppointmentService appointmentService, BarberService barberService,
                                 ClientService clientService, ServiceService serviceService) {
        this.appointmentService = appointmentService;
        this.barberService = barberService;
        this.clientService = clientService;
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<Object> saveAppointment(@RequestBody @Valid AppointmentDto appointmentDto){
        var appointmentModel = new AppointmentModel();

        System.out.println(appointmentDto);
        UUID barberId = appointmentDto.getBarberId();
        String clientEmail = appointmentDto.getClientEmail();

        Optional<BarberModel> barberModel = barberService.findById(barberId);
        Optional<ClientModel> clientModel = clientService.findByEmail(clientEmail);

        List<ServiceModel> services = new ArrayList<>();
        for(UUID ids: appointmentDto.getServices()){
            Optional<ServiceModel> serviceOpt = serviceService.findById(ids);
            serviceOpt.ifPresent(services::add);
        }

        System.out.println(clientModel);
        System.out.println(barberModel);
        BarberModel barberModel1 = new BarberModel();
        barberModel1.setName(barberModel.get().getName());
        barberModel1.setId(barberModel.get().getId());
        barberModel1.setDelete(barberModel.get().getDelete());
        barberModel1.setRegistrationDate(barberModel.get().getRegistrationDate());

        ClientModel clientModel1 = new ClientModel();
        clientModel1.setDelete(clientModel.get().getDelete());
        clientModel1.setName(clientModel.get().getName());
        clientModel1.setId(clientModel.get().getId());
        clientModel1.setCellPhone(clientModel.get().getCellPhone());
        clientModel1.setRegistrationDate(clientModel.get().getRegistrationDate());

        appointmentModel.setAppoitmentDate(appointmentDto.getAppoitmentDate());
        appointmentModel.setServices(services);
        appointmentModel.setCliente(clientModel1);
        appointmentModel.setBarber(barberModel1);
        appointmentModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        appointmentModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.save(appointmentModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAppointment(@PathVariable(value = "id") UUID id){
        Optional<AppointmentModel> appointmentModelOptional = appointmentService.findById(id);
        if(!appointmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict: Appointment not found!");
        }
        appointmentService.delete(appointmentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado");
    }

    @GetMapping
    public ResponseEntity<Object> getAllAppointments(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.findAllByDelete(pageable));
    }

}
