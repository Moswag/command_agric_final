package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Distribution;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farm;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.DistributionRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.DistrictRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmerRepository;
import zw.co.cytex.command_agriculture.command_agriculture.util.Constants;

import java.util.ArrayList;
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
@RequestMapping("api/distribution")
public class DistributionController {

    @Autowired
    DistributionRepository distributionRepository;

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    FarmerRepository farmerRepository;

    @GetMapping
    public ApiResponse<List<Distribution>> getDistributions(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Distributions successfully fetched",distributionRepository.findAll());
    }

    @PostMapping
    public ApiResponse<Distribution> saveDistribution(@RequestBody Distribution distribution){
        if(distributionRepository.existsByDateAndDistrictId(distribution.getDate(),distribution.getDistrictId())){
            sendMessage(distribution.getDistrictId(),"Distribution of inputs has been scheduled for date "+ distribution.getDate()+", see you there, Regards!");
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Distribution for "+distribution.getDate()+" already exists",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.OK.value(),"Distribution successfully set", distributionRepository.save(distribution));
        }
    }

    @PutMapping
    public ApiResponse<Distribution> updateDistribution(@RequestBody Distribution distribution){
        Optional<Distribution> optionalDistribution=distributionRepository.findByDateAndDistrictId(distribution.getDate(),distribution.getDistrictId());
        if(distributionRepository.existsByDateAndDistrictId(distribution.getDate(),distribution.getDistrictId())) {
            if (optionalDistribution.isPresent() && optionalDistribution.get().getId().equals(distribution.getId())) {
                return new ApiResponse<>(HttpStatus.OK.value(), "Distribution successfully set", distributionRepository.save(distribution));
            } else {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Distribution for "+distribution.getDate()+" already exists",null);
            }
        }
        else{
            return new ApiResponse<>(HttpStatus.OK.value(),"Distribution successfully set", distributionRepository.save(distribution));
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Distribution> deleteDistribution(Long id){
        if(distributionRepository.findById(id).isPresent()){
            if(distributionRepository.findById(id).get().getStatus().equals(Constants.STATUS_PENDING)){
                sendMessage(id,"Distribution for date "+ distributionRepository.findById(id).get().getDate()+" has been cancelled, sorry");
            }
            distributionRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.OK.value(),"Distribution successfully deleted",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Distribution with that Id do not exist", null);
        }
    }

    public void sendMessage(Long districtId,String message){
        Optional<List<Farm>> farm=farmRepository.findByDistrictId(districtId);
        List<Farmer> farmers=new ArrayList<>();

        if(farm.isPresent()){
            for(Farm f: farm.get()){
                Optional<Farmer> farmer=farmerRepository.findByFarmId(f.getFarmId());
                farmer.ifPresent(farmers::add);
            }
        }

        for(Farmer farmer: farmers){
            realMessage(farmer.getPhonenumber(),message);
        }

    }

    private void realMessage(String phonenumber, String message){
        System.out.println(message+" to "+ phonenumber+ " successfully send");
    }

}
