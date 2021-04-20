package TDMeasurement.DirectoryService.repository;

import TDMeasurement.DirectoryService.entity.Directory;
import TDMeasurement.MaintainabilityIndexService.entity.DirResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Long> {
    Directory findByDirectoryID(long directoryID);


}


