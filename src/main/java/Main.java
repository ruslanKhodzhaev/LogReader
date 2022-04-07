import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {
        YAMLMapper mapper = new YAMLMapper();
        Config config = mapper.readValue(new File("src/config.yml"), Config.class);

        File directorySource = new File(config.getSourceDirectory());
        File[] files = directorySource.listFiles(File::isFile);

        if (files == null || files.length == 0) {
            System.out.println("directory is empty");
            return;
        }

        List<SearchTask> taskList = new ArrayList<>();
        List<String> fileNames = Arrays.stream(files).map(File::getName).collect(Collectors.toList());

        for (String fileName : fileNames) {
            taskList.add(new SearchTask(fileName
                    , config.getSourceDirectory()
                    , config.getTargetDirectory()
                    , config.getEncoding()
                    , config.getStringsToFind()));
        }

        ExecutorService executor = Executors
                .newFixedThreadPool(Math.min(taskList.size(), config.getThreadPoolSize()));
        taskList.forEach(executor::submit);

        executor.shutdown();
    }
}
