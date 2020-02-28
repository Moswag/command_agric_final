package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farm;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 09/02/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface FarmRepository extends JpaRepository<Farm, Long> {
    boolean existsByFarmIdAndAndDistrictId(Long farmId, Long districtId);
    Optional<Farm> findByFarmIdAndDistrictId(Long farmId, Long districtId);

    Optional<List<Farm>> findByDistrictId(Long districtId);

    Optional<Farm> findByFarmId(Long farmId);
}
