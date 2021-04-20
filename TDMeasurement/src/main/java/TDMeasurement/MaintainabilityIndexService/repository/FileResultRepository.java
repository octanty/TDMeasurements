package TDMeasurement.MaintainabilityIndexService.repository;

import TDMeasurement.MaintainabilityIndexService.entity.FileResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileResultRepository extends JpaRepository<FileResult, Long> {
    FileResult findByFileResultID(long fileResultID);

    List<FileResult> findByDirectoryID(long directoryID);
}


