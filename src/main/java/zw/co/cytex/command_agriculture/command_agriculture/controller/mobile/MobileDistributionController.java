package zw.co.cytex.command_agriculture.command_agriculture.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.cytex.command_agriculture.command_agriculture.model.Distribution;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farm;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.DistributionRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmerRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 16/02/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@RestController
@RequestMapping("/api/mobile")
public class MobileDistributionController {
    @Autowired
    DistributionRepository distributionRepository;

    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    FarmRepository farmRepository;

    @RequestMapping("/distribution/{uname}")
    public ApiResponse<List<Distribution>> getDistributions(@PathVariable String uname){
        Optional<Farm> farm=farmRepository.findByFarmId(farmerRepository.findByNationalId(uname).get().getFarmId());
        if(farm.isPresent()){
            Optional<List<Distribution>> distributions=distributionRepository.findByDistrictId(farm.get().getDistrictId());
            return new ApiResponse<>(HttpStatus.OK.value(),"Districts successfully fetched", distributions.get());
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Farm do not exists",null);
        }
    }
}
