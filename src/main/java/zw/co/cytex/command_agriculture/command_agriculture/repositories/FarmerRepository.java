package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;

/**
 * @author : Webster Moswa
 * @since : 11/02/2020, Tue
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    boolean existsByNationalId(String nationalId);
}
