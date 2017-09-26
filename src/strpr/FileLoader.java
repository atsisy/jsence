package strpr;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileLoader {

    public FileLoader(){}

    public String load_all(String file_path){
        try {
            return Files.lines(Paths.get(file_path), Charset.forName("Shift-JIS"))
                    .collect(Collectors.joining(System.getProperty("line.separator")));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("FILE NOT FOUND");
        }

        return null;
    }

}
