package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Webster Moswa
 * @since : 16/01/2020, Thu
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
@Entity
@NoArgsConstructor
@Table(name = "soil_type")
public class Soil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
