package TDMeasurement.SqualeService.repository;

import TDMeasurement.MaintainabilityIndexService.entity.DirResult;
import TDMeasurement.SqualeService.entity.Squale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqualeRepository extends JpaRepository<Squale, Long> {
}
