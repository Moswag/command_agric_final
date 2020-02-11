package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Crop;
import zw.co.cytex.command_agriculture.command_agriculture.model.Soil;
import zw.co.cytex.command_agriculture.command_agriculture.model.User;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.CropRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.SoilRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 19/01/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/crops")
public class CropController {

    @Autowired
    CropRepository cropRepository;

    @Autowired
    SoilRepository soilRepository;

    @PostMapping
    public ApiResponse<Crop> saveCrop(@RequestBody Crop crop){
        Optional<Soil> soil=soilRepository.findById(crop.getSoilType());
        if(soil.isPresent()) {
            if (cropRepository.existsByName(crop.getName())) {
                return new ApiResponse<>(
                        HttpStatus.FOUND.value(),
                        "Crop already exists",
                        null);
            } else {
                return new ApiResponse<>(
                        HttpStatus.OK.value(),
                        "Crop successfully saved",
                        cropRepository.save(crop));
            }
        }
        else{
            return new ApiResponse<>(
                    HttpStatus.NOT_FOUND.value(),
                    "Soil type can not be found",
                    null);
        }


    }

    @GetMapping
    public ApiResponse<List<Crop>> getAllCrops(){
        return new ApiResponse<>(HttpStatus.OK.value(), "All crops successfully fetched", cropRepository.findAll());
    }

    @PutMapping
    public ApiResponse<Crop> updateCrop(@RequestBody Crop crop){
        Optional<Crop> oCrop=cropRepository.findById(crop.getId());
        if(oCrop.isPresent()){
            return new ApiResponse<>(HttpStatus.OK.value(),"Crop successfully updated",cropRepository.save(crop));
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Crop can not be found", null);

        }
    }
    @DeleteMapping("/{id}")
    public ApiResponse<Crop> deleteCrop(@PathVariable Long id){
        Optional<Crop> crop=cropRepository.findById(id);
        if(crop.isPresent()){
            cropRepository.delete(crop.get());
            return new ApiResponse<>(HttpStatus.OK.value(),"Crop successfully deleted",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Crop with that Id can not be found", null);
        }
    }





}
