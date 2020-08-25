import model.SalesmanModel;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class FilesWatch {

    String inDirectory = System.getProperty("user.home") + "/data/in";

    public void watchDirectoryFiles() throws IOException, InterruptedException {

        File directory = new File(inDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }

        WatchService watchService
                = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(inDirectory);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {

                System.out.println(
                        "Event kind:" + event.kind()
                                + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }

    public void readAllFilesFromDirectory() throws IOException {
        List<SalesmanModel> salesmanModels = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(inDirectory))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(x -> {
                        try {
                            String fileString = new String(Files.readAllBytes(x.toAbsolutePath()));

                            if (fileString.substring(0, 3).equals("001")) {
                                String[] fields = fileString.split("\\ç");
                                SalesmanModel salesmanModel = new SalesmanModel(
                                        fields[0], fields[1], fields[2], Double.parseDouble(fields[3])
                                );

                                System.out.print(salesmanModel);
                            }
                            else if (fileString.substring(0, 3).equals("002")) {
//                                System.out.print("Achou!!!");
                            }
                            else if (fileString.substring(0, 3).equals("003")) {
//                                System.out.print("Achou!!!");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        SalesmanModel salesmanModel = new SalesmanModel();
                    });
        }
    }

    public void writeOutputFile() {

    }
}
