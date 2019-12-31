package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Land {
    @Id
    private Long id;
    private String location;
    private double hectares;
    private String district;
}
