package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Farmer {
    @Id
    private Long userId;
    private String name;
    private String surname;
    private String nationalId;
    private String phonenumber;
    private String address;
    private String landId;


}
