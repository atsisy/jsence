package ui;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.function.Consumer;

/**
 * JSence Search box class.
 * @author Akihiro Takai
 */
public class SearchBox {

    private HBox root_box;
    private TextField search_box;

    SearchBox() {
        final Label search_label = new Label("Search :");
        Button go_button;

        search_box = new TextField();
        go_button = new Button("Go");
        root_box = new HBox();
        root_box.getChildren().addAll(search_label, search_box, go_button);
        root_box.setSpacing(10);
    }

    HBox getWhole(){
        return root_box;
    }

    void setHandler(Consumer function){
        search_box.setOnAction(event -> function.accept(search_box.getText()));
    }

}