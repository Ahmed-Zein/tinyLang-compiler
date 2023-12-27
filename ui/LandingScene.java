package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.Parser;
import parser.tree.NonTerminalNode;
import ui.graph.GraphDrawer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LandingScene extends Application {
    private TextArea textArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");

        textArea = new TextArea();
        textArea.setWrapText(true);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        HBox.setHgrow(textArea, Priority.ALWAYS);

        VBox leftVBox = new VBox(10);
        leftVBox.setPadding(new Insets(10));
        leftVBox.getChildren().addAll(textArea);

        VBox rightVBox = new VBox(10);
        rightVBox.setPadding(new Insets(10));

        // Load button
        Button loadButton = new Button("Load Text File");
        loadButton.setOnAction(e -> loadTextFromFile());

        Button printButton = new Button("parse");
        printButton.setOnAction(e -> parse(textArea));

        rightVBox.getChildren().addAll(loadButton, printButton);

        HBox root = new HBox(10);
        root.getChildren().addAll(leftVBox, rightVBox);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private void loadTextFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parse(TextArea t) {
        String input = t.getText();
        if (input.isEmpty()) {
            Label  l = new Label("add text or open a file");
            Stage s = new Stage();
            s.setScene(new Scene(l, 200 , 50));
            s.show();
//            t.setText("ERROR: the file is empty");
            return;
        }
        Parser parser = new Parser(input);
        NonTerminalNode graph_root = null;
        try {
            graph_root = parser.parse();
//            t.setText(graph_root.drawTree(0));
            Stage parseStage = new Stage();
            Pane graph_pane = new Pane();
            GraphDrawer gd = new GraphDrawer();
            gd.drawGraph(graph_pane, graph_root, GraphDrawer.DEFAULT_NODE_WIDTH, GraphDrawer.DEFAULT_NODE_HEIGHT, 0);
            ScrollPane sp = new ScrollPane(graph_pane);

            VBox sliders = new VBox();
            Slider scaleXSlider = new Slider(0.1, 2, 1);
            Slider scaleYSlider = new Slider(0.1, 2, 1);
            sliders.getChildren().addAll(scaleXSlider, scaleYSlider);
            scaleXSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                graph_pane.setScaleX(newValue.doubleValue());
            });

            scaleYSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                graph_pane.setScaleY(newValue.doubleValue());
            });
            sliders.setMinWidth(190);
            sliders.setMaxWidth(200);
//        sliders.setPadding(new Insets(5));
            HBox container = new HBox(sp, sliders);

            parseStage.setScene(new Scene(container, 800, 800));
            parseStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}