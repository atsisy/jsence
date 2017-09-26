package core;

import javafx.application.Application;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
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

        HTMLEditor editor = new HTMLEditor();
        editor.setPrefSize(600, WINDOW_HEIGHT - 20);

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
