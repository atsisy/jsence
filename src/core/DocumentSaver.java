package core;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * JSence Save documents class
 * @author Akihiro Takai
 */
public class DocumentSaver {

    private FileChooser file_chooser;

    public DocumentSaver(){
        file_chooser = new FileChooser();
    }

    public void saveDoc(String source, String file_path){
        try{
            File file = new File(file_path);
            FileWriter filewriter = new FileWriter(file);
            filewriter.write(source);
            filewriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.err.println("File Save Error.");
        }
    }

    public String getSavePathWithWindow(Stage stage) {

        file_chooser.setTitle("保存");
        file_chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("テキスト文書", "*.txt"));
        File import_file = file_chooser.showSaveDialog(stage);
        if(import_file != null) {
            return import_file.getPath();
        }
        return null;
    }

}
