package core;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Optional;

import static core.Value.WINDOW_HEIGHT;

public class TextEditor {

    private TextArea text_area;
    private boolean has_file;
    private String current_file_path;

    public TextEditor(Stage stage){
        text_area = new TextArea();
        has_file = false;

        text_area.setWrapText(true);
        text_area.setPrefSize(600, WINDOW_HEIGHT - 100);

        text_area.setOnKeyPressed(event ->
        {
            if (event.isControlDown()){
                switch (event.getCode()){
                    case S:
                        SaveFile(stage);
                        break;
                    case O:
                        OpenFile(stage);
                    default:
                        break;
                }
            }
        });
    }

    public void OpenFile(Stage stage){
        DocumentLoader loader = new DocumentLoader();
        Optional<String> path = Optional.ofNullable(loader.getOpenPathWithWindow(stage));
        path.ifPresent(file_path -> {
            text_area.setText(loader.loadAll(file_path));
            current_file_path = file_path;
            has_file = true;
        });
    }

    public void SaveFile(Stage stage){
        DocumentSaver saver = new DocumentSaver();
        if(!has_file) {
            Optional<String> path = Optional.ofNullable(saver.getSavePathWithWindow(stage));
            path.ifPresent(file_path -> {
                saver.saveDoc(text_area.getText(), file_path);
                current_file_path = file_path;
                has_file = true;
            });
        }else{
            saver.saveDoc(text_area.getText(), current_file_path);
        }
    }

    public TextArea node(){
        return text_area;
    }

    public String getText(){
        return text_area.getText();
    }

    public void setFont(Font font){
        text_area.setFont(font);
    }
}
