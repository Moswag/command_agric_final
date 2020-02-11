package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Crop;
import zw.co.cytex.command_agriculture.command_agriculture.model.Soil;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
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
@RequestMapping("/api/soil")
public class SoilController {

    @Autowired
    SoilRepository soilRepository;


    @PostMapping
    public ApiResponse<Soil> saveSoil(@RequestBody Soil soil){
        if(soilRepository.existsByName(soil.getName())){
            return new ApiResponse<>(HttpStatus.ALREADY_REPORTED.value(),"Soil type already exists",new Soil());
        }
        else{
           return new  ApiResponse<>(HttpStatus.OK.value(),"Soil type successfully added" ,soilRepository.save(soil));
        }
    }

    @GetMapping
    public ApiResponse<List<Soil>> getSoilTypes(){
        return new ApiResponse<>(HttpStatus.OK.value(),"Soil types successfully retrieved", soilRepository.findAll());
    }

    @PutMapping
    public ApiResponse<Soil> updateSoilType(@RequestBody Soil soil){
        Optional<Soil> soilT=soilRepository.findById(soil.getId());
        if(soilT.isPresent()){
            return new ApiResponse<>(HttpStatus.OK.value(),"Soil type successfully updated", soilRepository.save(soil));
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Soil type not found", null);
        }
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Soil> deleteSoilType(@PathVariable Long id){
        Optional<Soil> soil=soilRepository.findById(id);
        if(soil.isPresent()){
            soilRepository.delete(soil.get());
           return new ApiResponse<>(HttpStatus.OK.value(),"Soil type successfully deleted", null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Soil type can not be found", null);

        }
    }




}
