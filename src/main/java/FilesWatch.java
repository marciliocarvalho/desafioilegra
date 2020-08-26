import model.CustomerModel;
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
        List<CustomerModel> customerModels = new ArrayList<>();
        while (true) {
            try (Stream<Path> paths = Files.walk(Paths.get(inDirectory))) {
                paths
                        .filter(Files::isRegularFile)
                        .forEach(file -> {
                            try (Stream<String> stream = Files.lines(file)) {
                                stream
                                        .forEach(x -> {
                                            try {
                                                if (!x.isEmpty() && !x.trim().equals("")) {
                                                    x = x.trim();
                                                    if (x.substring(0, 3).equals("001")) {
                                                        String[] fields = x.split("\\ç");

                                                        SalesmanModel salesmanModel = new SalesmanModel(
                                                                fields[0], fields[1], fields[2], ((Float.parseFloat(fields[3])))
                                                        );

                                                        salesmanModels.add(salesmanModel);
                                                    } else if (x.substring(0, 3).equals("002")) {
                                                        String[] fields = x.split("\\ç");
                                                        CustomerModel customerModel = new CustomerModel(
                                                                fields[0], fields[1], fields[2], fields[3]);

                                                        customerModels.add(customerModel);
                                                    } else if (x.substring(0, 3).equals("003")) {
                                                        String[] fields = x.split("\\ç");
                                                        String id = fields[0];
                                                        String salesId = fields[1];

                                                        String salesDetails = fields[2];
                                                    }
                                                }


                                                System.out.print(customerModels);
                                                Thread.sleep(2000);

                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        });

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            }
        }

    }
}
