import service.FileService;

import java.io.IOException;

public class DesafioApplication {

    public static void main(String args[]) throws IOException, InterruptedException {
        FileService fileService = new FileService();
        fileService.readAllFilesFromDirectory();
    }
}


