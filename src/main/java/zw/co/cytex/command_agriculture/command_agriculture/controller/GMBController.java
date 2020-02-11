package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Crop;
import zw.co.cytex.command_agriculture.command_agriculture.model.GMB;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.CropRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.GMBRepository;

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
@RequestMapping("api/gmb")
public class GMBController {

    @Autowired
    GMBRepository gmbRepository;

    @Autowired
    CropRepository cropRepository;

    @GetMapping
    public ApiResponse<List<GMB>> getPrices(){
        return new ApiResponse<>(HttpStatus.OK.value(),"GMB Prices successfully fetched", gmbRepository.findAll());
    }

    @PostMapping
    public ApiResponse<GMB> savePrices(@RequestBody GMB gmb){
        if(gmbRepository.existsByCropId(gmb.getCropId())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"GMB price already exists",null);
        }
        else{
            return new ApiResponse<>(HttpStatus.OK.value(),"GMB Price successfully added", gmbRepository.save(gmb));
        }
    }

    @PutMapping
    public ApiResponse<GMB> updatePrices(@RequestBody GMB gmb){
        Optional<GMB> optionalGMB=gmbRepository.findByCropId(gmb.getCropId());
        Optional<Crop> crop=cropRepository.findById(gmb.getCropId());
        if(optionalGMB.isPresent()&& !optionalGMB.get().getId().equals(gmb.getId())){
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"GMB price already exists",null);
        }
        else {
            if (crop.isPresent()) {
                return new ApiResponse<>(HttpStatus.OK.value(), "GMB Price successfully updated", gmbRepository.save(gmb));
            }
            else{
                return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Crop do not exist",null);
            }
        }
    }


    @DeleteMapping("/{id}")
    public ApiResponse<GMB> deleteGMBPrice(@RequestBody Long id){
        if(gmbRepository.existsByCropId(id)){
            gmbRepository.deleteById(id);
            return new ApiResponse<>(HttpStatus.OK.value(),"GMB Prices successfully deleted", null);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"GMB Price not found",null);
        }
    }




}
