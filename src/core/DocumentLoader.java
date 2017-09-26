package core;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DocumentLoader {

    private FileChooser file_chooser;

    public DocumentLoader(){
        file_chooser = new FileChooser();
    }

    public String loadAll(String file_path){
        try {
            return Files.lines(Paths.get(file_path), Charset.forName("Shift-JIS"))
                    .collect(Collectors.joining(System.getProperty("line.separator")));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("FILE NOT FOUND");
        }

        return null;
    }

    public String getOpenPathWithWindow(Stage stage) {

        file_chooser.setTitle("開く");
        file_chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("テキスト文書", "*.txt"));
        File import_file = file_chooser.showOpenDialog(stage);
        if(import_file != null) {
            return import_file.getPath();
        }
        return null;
    }


}
