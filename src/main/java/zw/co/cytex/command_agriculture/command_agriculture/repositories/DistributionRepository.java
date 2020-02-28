package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.Distribution;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 15/02/2020, Sat
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface DistributionRepository extends JpaRepository<Distribution,Long> {

    boolean existsByDateAndDistrictId(Date date, Long id);
    Optional<Distribution> findByDateAndDistrictId(Date date, Long id);
    Optional<List<Distribution>> findByDistrictId(Long id);
}
