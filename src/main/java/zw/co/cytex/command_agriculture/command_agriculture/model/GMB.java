package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Webster Moswa
 * @since : 10/02/2020, Mon
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
@Entity
@NoArgsConstructor
@Table(name = "gmb")
public class GMB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long cropId;
    private double quantity;
    private double price;
}
