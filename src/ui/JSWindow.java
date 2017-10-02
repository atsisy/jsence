package ui;

import core.DocumentLoader;
import core.DocumentSaver;
import core.TextEditor;
import edu.cmu.lti.jawjaw.pobj.POS;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import strpr.StringExtractor;
import strpr.Word;

import java.security.Key;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static core.Value.DEFAULT_FONT_NAME;
import static core.Value.WINDOW_HEIGHT;
import static core.Value.WINDOW_WIDTH;
import static java.util.Arrays.asList;

/**
 * JSence Window class.
 * @author Akihiro Takai
 */
public class JSWindow {

    private Scene scene;
    private VBox root;
    private VBox synonym_search;
    private TextEditor text_editor;
    private Footer footer;
    private MenuBar menu_bar;
    private Stage stage;
    private SearchBox search_box;
    private TableView<Word> word_table_view;
    private ComboBox<String> font_dropdown_list;
    private ComboBox<String> font_size_dropdown_list;

    public JSWindow(Stage stage){

        this.stage = stage;
        root = new VBox();

        HBox footer_box = new HBox();
        footer = new Footer(WINDOW_WIDTH, 20.0);
        footer.PutText("", 0);
        footer_box.getChildren().addAll(footer.getCanvas());
        footer_box.setAlignment(Pos.BASELINE_CENTER);

        menu_bar = new MenuBar();
        menu_bar.prefWidthProperty().bind(stage.widthProperty());
        config_menubar();

        HBox middle_box = new HBox();

        HBox editor_box = new HBox();
        text_editor = new TextEditor(stage);
        config_editor();
        editor_box.getChildren().addAll(text_editor.node());
        editor_box.setAlignment(Pos.CENTER_LEFT);

        synonym_search = new VBox();
        word_table_view = new TableView<>();
        config_tableview();
        search_box = new SearchBox();
        config_searchbox();
        synonym_search.getChildren().addAll(search_box.getWhole(), word_table_view);
        synonym_search.setAlignment(Pos.TOP_RIGHT);
        synonym_search.setSpacing(20.0);


        HBox editor_status_box = new HBox();
        font_dropdown_list = new ComboBox<>();
        font_size_dropdown_list = new ComboBox<>();
        config_font_dropdown_list();
        editor_status_box.getChildren().addAll(font_dropdown_list, font_size_dropdown_list);

        middle_box.getChildren().addAll(editor_status_box, editor_box, synonym_search);
        middle_box.setSpacing(2.0);

        root.getChildren().addAll(middle_box, footer_box);
        root.setAlignment(Pos.TOP_CENTER);

        root.setSpacing(1.0);

        sort_node();
    }

    public Scene CreateScene(){
        scene = new Scene(root);
        return scene;
    }

    private void config_editor(){
        text_editor.node().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                footer.PutText("文字数 : " + Integer.toString(newValue.length()), 60);
            }
        });
        text_editor.node().setOnKeyReleased(event ->
        {
            if(event.getCode() == KeyCode.ENTER) {
                Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        footer.PutText("単語数 : " + Integer.toString(StringExtractor.CountNoun(text_editor.getText())), 240);
                        return null;
                    }
                };
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(task);
            }
        });
    }

    private void config_menubar(){

        Menu file_menu = new Menu("ファイル"),
             edit_menu = new Menu("編集"),
             help_menu = new Menu("ヘルプ");

        /*
        * ファイルメニューの子を生成
         */
        MenuItem file_menu_open_file = new MenuItem("開く");
        file_menu_open_file.setOnAction(event ->
        {
            text_editor.OpenFile(stage);
        });
        MenuItem file_menu_save_file = new MenuItem("保存");
        file_menu_save_file.setOnAction(event ->
        {
            text_editor.SaveFile(stage);
        });
        MenuItem file_menu_quit = new MenuItem("終了");
        file_menu_quit.setOnAction(event -> System.exit(0));


        file_menu.getItems().addAll(file_menu_open_file, file_menu_save_file, file_menu_quit);

        menu_bar.getMenus().addAll(file_menu, edit_menu, help_menu);

        HBox menu_box = new HBox();
        menu_box.getChildren().addAll(menu_bar);
        menu_box.setAlignment(Pos.TOP_LEFT);
        root.getChildren().addAll(menu_box);

    }

    private void config_searchbox(){
        root.getChildren().addAll(search_box.getWhole());

        search_box.setHandler(str -> {
            System.out.println(str);
            ArrayList<Word> data = StringExtractor.collectSynonym((String)str, "名詞", POS.n);
            ObservableList<Word> list = FXCollections.observableList(data);
            data = StringExtractor.collectSynonym((String)str, "動詞", POS.v);
            data.forEach(list::add);
            data = StringExtractor.collectSynonym((String)str, "形容詞", POS.a);
            data.forEach(list::add);
            word_table_view.itemsProperty().setValue(list);
        });
    }

    private void config_tableview(){

        word_table_view.setPrefWidth(220);
        word_table_view.setPlaceholder(new Label("マッチ数 : 0件"));

        TableColumn<Word, String> part_column = new TableColumn<>("品詞");
        TableColumn<Word, String> word_column = new TableColumn<>("単語");

        part_column.setCellValueFactory(new PropertyValueFactory<>("part"));
        word_column.setCellValueFactory(new PropertyValueFactory<>("word"));

        word_table_view.getColumns().addAll(part_column, word_column);
    }

    private void config_font_dropdown_list(){
        font_dropdown_list.getItems().addAll(Font.getFamilies());
        font_dropdown_list.setPrefWidth(170);
        font_size_dropdown_list.getItems().addAll(asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"));
        font_dropdown_list.getSelectionModel().select(DEFAULT_FONT_NAME);
        font_size_dropdown_list.getSelectionModel().select("9");
        text_editor.setFont(new Font(font_dropdown_list.getValue(), Integer.valueOf(font_size_dropdown_list.getValue())));

        font_dropdown_list.setOnAction(event -> {
            text_editor.setFont(new Font(font_dropdown_list.getValue(), Integer.valueOf(font_size_dropdown_list.getValue())));
        });
        font_size_dropdown_list.setOnAction(event -> {
            text_editor.setFont(new Font(font_dropdown_list.getValue(), Integer.valueOf(font_size_dropdown_list.getValue())));
        });

    }

    private void sort_node(){
        menu_bar.toFront();
    }

}
