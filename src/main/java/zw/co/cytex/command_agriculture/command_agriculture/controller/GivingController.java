package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Giving;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.DistributionRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.GivingRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.InputsRepository;

/**
 * @author : Webster Moswa
 * @since : 15/02/2020, Sat
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/giving")
public class GivingController {
    @Autowired
    GivingRepository givingRepository;

    @Autowired
    DistributionRepository distributionRepository;

    @Autowired
    InputsRepository inputsRepository;

    @GetMapping
    public ApiResponse<Giving> getGivings(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Givings successfully fetched", givingRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Giving> addGivings(@RequestBody Giving giving){
        if(distributionRepository.findById(giving.getDistributionId()).isPresent()){
            if(inputsRepository.existsByCropId(giving.getCropId())) {
                if(giving.getQuantity()< inputsRepository.findByCropId(giving.getCropId()).get().getQuantity()) {
                    return new ApiResponse<>(HttpStatus.OK.value(), "Giving successfully added", givingRepository.save(giving));
                }
                else{
                    return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Crop can not be distributed as it is out of stock",null);
                }
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Crop can not be distributed as it is out of stock",null);
            }
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Distribution does not exist, please contact admin",null);
        }
    }

}
