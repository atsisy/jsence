package ui;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import static core.Value.SEARCH_BOX_X;
import static core.Value.SEARCH_BOX_Y;

/**
 * Created by Akihiro on 7/12/2017.
 */
public class SearchBox {

    private HBox root_box;

    public SearchBox() {
        final Label search_label = new Label("Search :");
        TextField search_box;
        Button go_button;

        search_box = new TextField();
        go_button = new Button("Go");
        root_box = new HBox();
        root_box.getChildren().addAll(search_label, search_box, go_button);
        root_box.setSpacing(10);
    }

    public void register_to_root(UIRoot ui_root){
        ui_root.register(root_box);
        ui_root.set_place(root_box, SEARCH_BOX_X, SEARCH_BOX_Y);
    }

}