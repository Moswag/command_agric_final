package zw.co.cytex.command_agriculture.command_agriculture.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.cytex.command_agriculture.command_agriculture.model.Crop;
import zw.co.cytex.command_agriculture.command_agriculture.model.GMB;
import zw.co.cytex.command_agriculture.command_agriculture.model.dto.MobilePrice;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.CropRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.GMBRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 12/02/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@RestController
@RequestMapping("/api/mobile")
public class MobileGMBController {
    @Autowired
    GMBRepository gmbRepository;

    @Autowired
    CropRepository cropRepository;

    @RequestMapping("/view_prices")
    public ApiResponse<List<MobilePrice>> getPrices(){
        List<GMB> gmbs=gmbRepository.findAll();
        List<MobilePrice> prices=new ArrayList<>();
        for(GMB gmb: gmbs){
            Optional<Crop> crop=cropRepository.findById(gmb.getCropId());
            if(crop.isPresent()) {
                MobilePrice mobilePrice = new MobilePrice();
                mobilePrice.setCropId(gmb.getCropId());
                mobilePrice.setPrice(gmb.getPrice());
                mobilePrice.setPriceId(gmb.getId());
                mobilePrice.setCrop(crop.get().getName());
                prices.add(mobilePrice);
            }
        }
        return new ApiResponse<>(HttpStatus.OK.value(),"GMB Prices successfully fetched", prices);
    }
}
