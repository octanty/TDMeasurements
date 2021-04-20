package TDMeasurement.MaintainabilityIndexService.repository;

import TDMeasurement.MaintainabilityIndexService.entity.DirResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirResultRepository extends JpaRepository<DirResult, Long> {
    DirResult findByDirResultID(long dirResultID);

    DirResult findByDirectoryID(long directoryID);
}
