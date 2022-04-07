
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Config {

    private String encoding;

    private int threadPoolSize;

    private String sourceDirectory;

    private String targetDirectory;

    private List<String> stringsToFind;
}
