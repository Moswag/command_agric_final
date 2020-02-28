package zw.co.cytex.command_agriculture.command_agriculture.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;
import zw.co.cytex.command_agriculture.command_agriculture.model.Yield;
import zw.co.cytex.command_agriculture.command_agriculture.model.dto.MobileYield;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.CropRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmerRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.YieldRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 18/02/2020, Tue
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@RestController
@RequestMapping("/api/mobile")
public class MobileYieldController {
    @Autowired
    YieldRepository yieldRepository;

    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    CropRepository cropRepository;

    @RequestMapping("/view_yields/{uname}")
    public ApiResponse<MobileYield> getYields(@PathVariable String uname) {
        Optional<Farmer> optionalFarmer = farmerRepository.findByNationalId(uname);
        if(optionalFarmer.isPresent()){
            Optional<List<Yield>> optionalYieldList=yieldRepository.findByFarmerId(optionalFarmer.get().getId());
            List<MobileYield> mobileYields=new ArrayList<>();
            for(Yield yield: optionalYieldList.get()){
                MobileYield mobileYield=new MobileYield();
                mobileYield.setNationalId(yield.getFarmerId().toString());
                mobileYield.setCrop(cropRepository.findById(yield.getCropType()).get().getName());
                mobileYield.setQuantity(String.valueOf(yield.getYield()));
                mobileYield.setDate(yield.getDate().toString());
                mobileYields.add(mobileYield);

            }

            return new ApiResponse<>(HttpStatus.OK.value(), "Yields successfully fetched", mobileYields);
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Farmer do not exist", null);
        }
   }

    @RequestMapping("/saveYield")
    public ApiResponse<Yield> saveYield(@Valid MobileYield mobileYield){
        if(farmerRepository.existsByNationalId(mobileYield.getNationalId())){
            Optional<Yield> myYield=yieldRepository.findByFarmerIdAndCropType(farmerRepository.findByNationalId(mobileYield.getNationalId()).get().getId(),cropRepository.findByName(mobileYield.getCrop()).get().getId());
            if(myYield.isPresent()){
               double quantity= myYield.get().getYield()+Double.parseDouble(mobileYield.getQuantity());
                myYield.get().setYield(quantity);
                return new ApiResponse<>(HttpStatus.OK.value(),"Yield successfully updated", yieldRepository.save(myYield.get()));
            }
            else{
                Yield yield=new Yield();
                yield.setCropType(cropRepository.findByName(mobileYield.getCrop()).get().getId());
                yield.setFarmerId(farmerRepository.findByNationalId(mobileYield.getNationalId()).get().getId());
                yield.setYield(Double.parseDouble(mobileYield.getQuantity()));
                yield.setDate(new Date());
                return new ApiResponse<>(HttpStatus.OK.value(),"Yield successfully added", yieldRepository.save(yield));
            }
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Farmer not found", null);
        }
    }
}
