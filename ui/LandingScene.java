package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parser.Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LandingScene extends Application {
    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Editor");

        textArea = new TextArea();
        textArea.setWrapText(true);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        textArea.autosize();

        VBox leftVBox = new VBox(10);
        leftVBox.setPadding(new Insets(10));
        leftVBox.getChildren().addAll(textArea);

        VBox rightVBox = new VBox(10);
        rightVBox.setPadding(new Insets(10));

        HBox.setHgrow(leftVBox, Priority.ALWAYS);
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

    private  void parse(TextArea t){
        String input = t.getText();
        if(input.length() <=0){
            t.setText("ERROR: the file is empty");
            return;
        }
        Parser parser = new Parser(input);
        try {
            t.setText(parser.parse().drawTree(0));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}