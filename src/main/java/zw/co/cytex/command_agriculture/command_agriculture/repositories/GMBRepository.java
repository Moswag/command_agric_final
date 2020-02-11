package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.GMB;

import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 10/02/2020, Mon
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface GMBRepository extends JpaRepository<GMB, Long> {
    boolean existsByCropId(Long cropId);


    Optional<GMB> findByCropId(Long cropId);
}
