package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.District;
import zw.co.cytex.command_agriculture.command_agriculture.model.Soil;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.DistrictRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.SoilRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 09/02/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/district")
public class DistrictController {

    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    SoilRepository soilRepository;

    @GetMapping
    public ApiResponse<List<District>> getDistricts(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Districts successfully fetched", districtRepository.findAll());
    }

    @PostMapping
    public ApiResponse<District> saveDistrict(@RequestBody District district){
        if(districtRepository.existsByName(district.getName())){
            return new ApiResponse<>(HttpStatus.FOUND.value(),"District already exjts",null);
        }
        else {
            if (soilRepository.existsById(district.getSoilType())) {
                return new ApiResponse<>(HttpStatus.OK.value(), "District successfully added", districtRepository.save(district));
            }
            else {
                return new ApiResponse<>(HttpStatus.FOUND.value(),"Soil type do not exists",null);
            }
        }
    }

    @PutMapping
    public ApiResponse<District> updateDistrict(@RequestBody District district){
        Optional<District> myDistrict=districtRepository.findById(district.getId());
        Optional<Soil> soil=soilRepository.findById(district.getSoilType());

        if(myDistrict.isPresent()) {
            if (soil.isPresent()) {
                return new ApiResponse<>(HttpStatus.OK.value(), "District successfully added", districtRepository.save(district));
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Soil type do not exists", null);
            }
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"District with that Id do not exists", null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<District> deleteDistrict(@PathVariable Long id){
        if(districtRepository.existsById(id)){
            districtRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.OK.value(),"District successfully deleted",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"District with that Id do not exists", null);
        }
    }


}
