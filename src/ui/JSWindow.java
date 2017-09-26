package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import static core.Value.WINDOW_HEIGHT;
import static core.Value.WINDOW_WIDTH;

/**
 * JSence Window class.
 * @author Akihiro Takai
 */
public class JSWindow {

    private AnchorPane root;
    private TextArea editor;
    private Footer footer;

    public JSWindow(){

        root = new AnchorPane();

        footer = new Footer(WINDOW_WIDTH, 30.0);
        footer.PutText("", 0);
        set_place(footer.getCanvas(), 0.0, WINDOW_HEIGHT - 55);

        editor = new TextArea();
        editor.setWrapText(true);
        editor.setPrefSize(600, WINDOW_HEIGHT - 60);
        set_place(editor, 150, 0);
        editor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                footer.PutText(Integer.toString(newValue.length()), 40);
            }
        });

        root.getChildren().addAll(footer.getCanvas(), editor);
    }

    private void set_place(Node node, double x, double y){
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
    }

    public Scene CreateScene(){
        return new Scene(root);
    }

}
