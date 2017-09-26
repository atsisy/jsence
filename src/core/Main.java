package core;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.JSWindow;

import static core.Value.WINDOW_HEIGHT;
import static core.Value.WINDOW_WIDTH;

public class Main extends Application {

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        init_window(stage);

        JSWindow js_window = new JSWindow();

        stage.setScene(js_window.CreateScene());

        stage.show();

    }

    private void init_window(Stage stage){
        stage.setTitle("JSence");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
    }

}
