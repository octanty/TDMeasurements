package TDMeasurement.SqualeService.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Directory {

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

