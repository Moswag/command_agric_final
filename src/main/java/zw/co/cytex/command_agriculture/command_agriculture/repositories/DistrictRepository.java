package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.District;

/**
 * @author : Webster Moswa
 * @since : 09/02/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface DistrictRepository extends JpaRepository<District, Long> {
    Boolean existsByName(String name);
}
