package zw.co.cytex.command_agriculture.command_agriculture.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Admin {
    @Id
    private Long userId;
    private String name;
    private String surname;
    private String nationalId;
    private String address;
}
