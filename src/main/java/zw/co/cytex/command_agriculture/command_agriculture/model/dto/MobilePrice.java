package zw.co.cytex.command_agriculture.command_agriculture.model.dto;

import lombok.Data;

/**
 * @author : Webster Moswa
 * @since : 16/02/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
public class MobilePrice {
    Long priceId;
    Long cropId;
    String crop;
    double price;
}
