import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Observer;

public class DesafioApplication {

    public static void main(String args[]) throws IOException, InterruptedException {
        FilesWatch filesWatch = new FilesWatch();
        filesWatch.watchDirectoryFiles();

    }
}


