package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
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
    private MenuBar menu_bar;

    public JSWindow(){

        root = new AnchorPane();

        footer = new Footer(WINDOW_WIDTH, 30.0);
        footer.PutText("", 0);
        set_place(footer.getCanvas(), 0.0, WINDOW_HEIGHT - 55);

        menu_bar = new MenuBar();
        config_menubar();

        editor = new TextArea();
        config_editor();

        root.getChildren().addAll(menu_bar, footer.getCanvas(), editor);

        sort_node();
    }

    private void set_place(Node node, double x, double y){
        AnchorPane.setLeftAnchor(node, x);
        AnchorPane.setTopAnchor(node, y);
    }

    public Scene CreateScene(){
        return new Scene(root);
    }

    private void config_editor(){
        editor.setWrapText(true);
        editor.setPrefSize(600, WINDOW_HEIGHT - 100);
        set_place(editor, 150, menu_bar.getHeight() + 30);
        editor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                footer.PutText(Integer.toString(newValue.length()), 40);
            }
        });

    }

    private void config_menubar(){
        menu_bar.setPrefWidth(WINDOW_WIDTH);

        Menu file_menu = new Menu("ファイル"),
             edit_menu = new Menu("編集"),
             help_menu = new Menu("ヘルプ");

        menu_bar.getMenus().addAll(file_menu, edit_menu, help_menu);

        set_place(menu_bar, 0, 0);

    }

    private void sort_node(){
        menu_bar.toFront();
    }

}
