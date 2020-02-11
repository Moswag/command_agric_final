package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farm;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.DistrictRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 10/02/2020, Mon
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/farm")
public class FarmController {

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    DistrictRepository districtRepository;

    @GetMapping
    public ApiResponse<List<Farm>> getAllFarms(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Farms successfully fetched", farmRepository.findAll());
    }


    @PostMapping
    public ApiResponse<Farm> saveFarm(@RequestBody Farm farm){
        if(farmRepository.existsByFarmIdAndAndDistrictId(farm.getFarmId(),farm.getDistrictId())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Farm already exists", null);
        }
        else{
            if(districtRepository.existsById(farm.getDistrictId())){
                return new ApiResponse<>(HttpStatus.OK.value(),"Farm successfully added",farmRepository.save(farm));
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"District do not exists", null);
            }
        }
    }


    @PutMapping
    public ApiResponse<Farm> updateFarm(@RequestBody Farm farm){
        Optional<Farm> mfarm=farmRepository.findByFarmIdAndDistrictId(farm.getFarmId(), farm.getDistrictId());
        if(mfarm.isPresent() && !mfarm.get().getId().equals(farm.getId())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"Farm already exists", null);
        }
        else{
            if(districtRepository.existsById(farm.getDistrictId())){
                return new ApiResponse<>(HttpStatus.OK.value(),"Farm successfully updated",farmRepository.save(farm));
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"District do not exists", null);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Farm> deleteFarm(@PathVariable Long id){
        if(farmRepository.existsById(id)){
            farmRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.OK.value(),"Farm successfully deleted",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Farm do not exists", null);
        }
    }
}
