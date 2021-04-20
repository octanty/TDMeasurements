package TDMeasurement.DirectoryService.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long directoryID;
    private String path;

    public Long getDirectoryID() {
        return directoryID;
    }

    public void setDirectoryID(Long directoryID) {
        this.directoryID = directoryID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
