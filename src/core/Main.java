package core;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ui.Footer;
import ui.UIRoot;

import static core.Value.WINDOW_HEIGHT;
import static core.Value.WINDOW_WIDTH;

public class Main extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        init_window(stage);

        UIRoot root = new UIRoot();

        Footer footer = new Footer(WINDOW_WIDTH, 30.0);
        footer.PutText("", 0);
        root.set_place(footer.getCanvas(), 0.0, WINDOW_HEIGHT - 55);
        root.register(footer.getCanvas());

        TextArea editor = new TextArea();
        editor.setWrapText(true);
        editor.setPrefSize(600, WINDOW_HEIGHT - 60);
        editor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                footer.PutText(Integer.toString(newValue.length()), 40);
            }
        });



        root.register(editor);
        root.set_place(editor, 150.0, 0.0);

        stage.setScene(root.create_scene());
        stage.show();

    }

    private void init_window(Stage stage){
        stage.setTitle("JSence");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

    }

}
