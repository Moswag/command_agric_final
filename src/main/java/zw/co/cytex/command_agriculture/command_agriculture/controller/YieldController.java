package zw.co.cytex.command_agriculture.command_agriculture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;
import zw.co.cytex.command_agriculture.command_agriculture.model.Yield;
import zw.co.cytex.command_agriculture.command_agriculture.model.dto.MobileYield;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.CropRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmerRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.YieldRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 18/02/2020, Tue
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/yields")
public class YieldController {
    @Autowired
    YieldRepository yieldRepository;

    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    CropRepository cropRepository;

    @GetMapping
    public ApiResponse<MobileYield> getYields() {
        List<Yield> yields=yieldRepository.findAll();
            List<MobileYield> mobileYields=new ArrayList<>();
            for(Yield yield: yields){
                    MobileYield mobileYield = new MobileYield();
                    mobileYield.setNationalId("Mos");
                    mobileYield.setCrop(cropRepository.findById(yield.getCropType()).get().getName());
                    mobileYield.setQuantity(String.valueOf(yield.getYield()));
                    mobileYield.setDate(yield.getDate().toString());
                    mobileYields.add(mobileYield);


            }
            System.out.println(yields+" after alt: "+ mobileYields);

            return new ApiResponse<>(HttpStatus.OK.value(), "Yields successfully fetched", mobileYields);
        }


}
