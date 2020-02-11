package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Webster Moswa
 * @since : 09/02/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
@NoArgsConstructor
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long soilType;
}
