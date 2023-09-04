package com.example.barberboost.Controllers;

import com.example.barberboost.dtos.LoyaltyPlanDto;
import com.example.barberboost.models.ClientModel;
import com.example.barberboost.models.LoyaltyPlanModel;
import com.example.barberboost.service.ClientService;
import com.example.barberboost.service.LoyaltyPlanService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/loyalty-plan")
public class LoyaltyPlanServiceController {

    final LoyaltyPlanService loyaltyPlanService;
    final ClientService clientService;

    public LoyaltyPlanServiceController(LoyaltyPlanService loyaltyPlanService, ClientService clientService) {
        this.loyaltyPlanService = loyaltyPlanService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllPlans(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(loyaltyPlanService.findAllByDelete(pageable));
    }

    @PostMapping("/link/{userId}/{planId}")
    public ResponseEntity<Object> linkUserToPlan(@PathVariable(value = "userId") UUID userId,
                                                 @PathVariable(value = "planId") UUID planId) {
        Optional<ClientModel> clientModelOptional = clientService.findByid(userId);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        Optional<LoyaltyPlanModel> loyaltyPlanModelOptional = loyaltyPlanService.findByid(planId);
        if(!loyaltyPlanModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        }

        clientModelOptional.get().setLoyaltyPlan(loyaltyPlanModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(clientModelOptional.get()));
    }

    @PostMapping("/point/{userId}")
    public ResponseEntity<Object> addOnePoint(@PathVariable(value = "userId") UUID userId){
        Optional<ClientModel> clientModelOptional = clientService.findByid(userId);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        Optional<LoyaltyPlanModel> loyaltyPlanModelOptional = loyaltyPlanService.findByid(clientModelOptional.get().getLoyaltyPlan().getId());
        if(!loyaltyPlanModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plan not found");
        }

        ClientModel clientModel = clientModelOptional.get();
        LoyaltyPlanModel loyaltyPlanModel = loyaltyPlanModelOptional.get();
        String rsp = "";

        if(clientModel.getLoyaltyAmount() < loyaltyPlanModel.getNecessaryAmount()){
            clientModel.setLoyaltyAmount(clientModel.getLoyaltyAmount() + 1);
            clientService.save(clientModel);
            rsp = "Ponto adicionado com sucesso!";
        } else if (clientModel.getLoyaltyAmount() == loyaltyPlanModel.getNecessaryAmount()) {
            clientModel.setLoyaltyAmount(0);
            clientService.save(clientModel);
            rsp = "promocção concluída";
        }
        return ResponseEntity.status(HttpStatus.OK).body(rsp);

    }

    @PostMapping("/point/beta/{userId}")
    public ResponseEntity<Object> addOnePointBeta(@PathVariable(value = "userId") UUID userId){
        Optional<ClientModel> clientModelOptional = clientService.findByid(userId);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }

        ClientModel clientModel = clientModelOptional.get();
        String rsp = "";

        if(clientModel.getLoyaltyAmount() < 10){
            clientModel.setLoyaltyAmount(clientModel.getLoyaltyAmount() + 1);
            clientService.save(clientModel);
            rsp = "Ponto adicionado com sucesso!";
        } else if (clientModel.getLoyaltyAmount() == 10) {
            clientModel.setLoyaltyAmount(0);
            clientService.save(clientModel);
            rsp = "promoção concluída";
        }
        return ResponseEntity.status(HttpStatus.OK).body(rsp);

    }


    @PostMapping
    public ResponseEntity<Object> savePlan(@RequestBody @Valid LoyaltyPlanDto loyaltyPlanDto){

        var loyaltyPlanModel = new LoyaltyPlanModel();
        BeanUtils.copyProperties(loyaltyPlanDto, loyaltyPlanModel);
        loyaltyPlanModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        loyaltyPlanModel.setDelete(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(loyaltyPlanService.save(loyaltyPlanModel));
    }
}
