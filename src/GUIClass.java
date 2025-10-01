import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class GUIClass extends Application {

    @Override
    public void start(Stage stage) {
        // UI Elements
        Label introLabel = new Label("Flash Cards");
        Button createDeckButton = new Button("Create Deck");
        Button viewDeckButton = new Button("View Existing Deck");
        Button studyDeckButton = new Button("Study Existing Deck");
        Button editDeckButton = new Button("Edit Existing Deck");
        Button saveButton = new Button("Save Current Decks");
        Button loadButton = new Button("Load Pre-existing Decks");
        Button exitButton = new Button("Quit Program");

        HBox firstButtonsRow = new HBox(20, createDeckButton, viewDeckButton, studyDeckButton);
        firstButtonsRow.setAlignment(Pos.CENTER);

        HBox secondButtonsRow = new HBox(20, editDeckButton, saveButton, loadButton);
        secondButtonsRow.setAlignment(Pos.CENTER);

        VBox screenLayout = new VBox(20, introLabel, firstButtonsRow, secondButtonsRow, exitButton);
        screenLayout.setAlignment(Pos.CENTER);


        Scene mainScene = new Scene(screenLayout, 700, 600);
        stage.setTitle("Flash Cards");
        stage.setScene(mainScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
