package frame;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Image;
import service.ImageService;

import static service.ImageService.getExtensionList;

public class ChoseFile extends Application {
    private Desktop desktop = Desktop.getDesktop();
    private TableView table = new TableView();

    List<File> receivedFiles = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception {
        final FileChooser fileChooser = new FileChooser();
        TextArea textArea = new TextArea();
        textArea.setMinHeight(70);
        Button buttonM = new Button("Select Multi Files");
        buttonM.setOnAction(event -> {
            textArea.clear();
            receivedFiles = fileChooser.showOpenMultipleDialog(primaryStage);

//            List<String> nameList = ImageService.getNameList(receivedFiles);
//            List<String> extensionList = ImageService.getExtensionList(receivedFiles);
//            List<Long> sizeList = ImageService.getSizeList(receivedFiles);
//            List<String> resolutionList = ImageService.getResolutionList(receivedFiles);
//            List<Double> ratioList = ImageService.getWeightHeightRatio(receivedFiles);
//            List<Integer> colorList = ImageService.getColorList(receivedFiles);


            List<Integer> squareList = ImageService.getSquareList(receivedFiles);


            squareList.forEach(it -> System.out.println(it));



            printLog(textArea, receivedFiles);
          //  showResults(primaryStage);
        });

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);
        root.getChildren().addAll(textArea, buttonM);
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Chepky Roman");
        primaryStage.setScene(scene);
        primaryStage.show();




    }

    private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }



    private void showResults(Stage stage){
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {

        Application.launch(args);
    }
}
