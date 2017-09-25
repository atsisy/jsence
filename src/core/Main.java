package core;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.SearchBox;
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

        SearchBox search_box = new SearchBox();
        search_box.register_to_root(root);

        stage.setScene(root.create_scene());
        stage.show();

    }

    private void init_window(Stage stage){
        stage.setTitle("Happy Checker");
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);

    }

}
