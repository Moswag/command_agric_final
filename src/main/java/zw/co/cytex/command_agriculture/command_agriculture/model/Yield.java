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
public class Yield {
    @Id
    private Long id;
    private Long farmerId;
    private double yield;
    private String cropType;


}
