package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.Yield;

import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 18/02/2020, Tue
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface YieldRepository extends JpaRepository<Yield, Long> {
    boolean existsByFarmerIdAndCropType(Long farmerId, Long cropId);


    Optional<List<Yield>> findByFarmerId(Long farmerId);

    Optional<Yield> findByFarmerIdAndCropType(Long farmerId, Long cropId);
}
