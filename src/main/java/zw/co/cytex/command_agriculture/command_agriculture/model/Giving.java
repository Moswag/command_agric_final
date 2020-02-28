package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author : Webster Moswa
 * @since : 15/02/2020, Sat
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
@NoArgsConstructor
@Entity
@Table(name = "givings")
public class Giving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long distributionId;
    private Long farmerId;
    private Long cropId;
    private double quantity;
    private Date date;
}
