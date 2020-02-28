package zw.co.cytex.command_agriculture.command_agriculture.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.cytex.command_agriculture.command_agriculture.model.Crop;

import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 19/01/2020, Sun
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/


public interface CropRepository extends JpaRepository<Crop, Long> {
    Optional<Crop> findByName(String name);
    Optional<Crop> findById(Long id);
    Boolean existsByName(String name);
}
