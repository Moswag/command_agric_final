package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmerRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 15/02/2020, Sat
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/farmer")
public class FarmerController {

    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping
    public ApiResponse<List<Farmer>> getFarmers(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Farmers successfully fetched", farmerRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Farmer> saveFarmer(@RequestBody Farmer farmer){
        Optional<Farmer> optionalFarmer=farmerRepository.findByNationalId(farmer.getNationalId());

        if(optionalFarmer.isPresent()){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Farmer already exists",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.OK.value(),"Farmer successfully added", farmerRepository.save(farmer));
        }
    }


    @PutMapping
    public ApiResponse<Farmer> updateFarmer(@RequestBody Farmer farmer){
        Optional<Farmer> farmerByNationalId=farmerRepository.findByNationalId(farmer.getNationalId());
        if(farmerRepository.existsById(farmer.getId())){
            if(farmerByNationalId.isPresent() && farmerByNationalId.get().getId().equals(farmer.getId())) {
                return new ApiResponse<>(HttpStatus.OK.value(), "Farmer successfully updated", farmerRepository.save(farmer));
            }
            else if(farmerByNationalId.isEmpty()){
                return new ApiResponse<>(HttpStatus.OK.value(), "Farmer successfully updated", farmerRepository.save(farmer));
            }
            else{
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Farmer with that National Id already exists",null);
        }
    }
        else{
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Farmer with that Id do not exist",null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Farmer> deleteFarmer(Long id){
        if(farmerRepository.existsById(id)){
            farmerRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.OK.value(),"Farmer successfuly deleted", null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Farmer do not exists",null);
        }
    }

}
