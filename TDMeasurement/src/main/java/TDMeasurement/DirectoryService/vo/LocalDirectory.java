package TDMeasurement.DirectoryService.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDirectory {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

