package seel.javafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Created by Seel Patel on 2017-04-17. Java
 */
public class ConfirmBox {
    private Stage window = new Stage(); // Main window
    private String text = "default"; // Text message
    private String titleText = "Alert"; // Title of window

    private VBox layout = new VBox(20); // Main layout
    private Label textLabel = new Label(text); // Label for text message

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");

    boolean answerGiven; // Checks if answer is already given
    boolean answer; // Yes = true, No = false

    // Define style types
    public static class Style {
        public static int LIGHT = 1;
        public static int DARK = 2;
        public static int DARK_SECONDARY = 3;
    }

    // Initialize with message
    public ConfirmBox(String text) {
        setStyle(Style.LIGHT);
        this.text = text;
        setButtonSize(yesButton);
        setButtonSize(noButton);
    }

    // Initialize with message and style
    public ConfirmBox(String text, int style) {
        setStyle(style);
        this.text = text;
        setButtonSize(yesButton);
        setButtonSize(noButton);
    }


    // Change text of message
    public void setText(String text) {
        this.text = text;
    }

    // Change text of window title
    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    // Change text of button

    // Display the window
    // Returns true of yes and false if no
    public boolean display() {
        window.initStyle(StageStyle.UTILITY); // Utility window style, no max and min
        window.setResizable(false);
        window.setTitle(titleText);

        layout.setAlignment(Pos.CENTER);

        HBox buttonHolder = new HBox(20); // Horizontal layout for buttons
        buttonHolder.setAlignment(Pos.CENTER);
        buttonHolder.getChildren().addAll(yesButton,noButton);

        yesButton.setOnAction(event -> {
            answerGiven = true;
            answer = true;
            window.close();
        });
        noButton.setOnAction(event -> {
            answerGiven = true;
            answer = false;
            window.close();
        });

        // Set size of the window
        layout.setMinSize(250, 125);
        layout.setMaxSize(250, 125);

        textLabel.setText(text); // Change label

        layout.getChildren().addAll(textLabel, buttonHolder);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();

        while (true) { // If an answer is given return true
            if (answerGiven){
                return answer;
            }
        }
    }

    // Set width of button
    private void setButtonSize(Button button) {
        button.setMinWidth(50);
        button.setMaxWidth(50);
    }

    // Style the buttons
    public void setButtonStyle(Button button, int style) {
        if (style == Style.LIGHT) {
            button.setStyle("-fx-background-color: #d6d4d0;");
            button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #e2e0dc;"));
            button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #aeaca9;"));
            button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #e2e0dc;"));
            button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #d6d4d0;"));
        }
        else if (style == Style.DARK || style == Style.DARK_SECONDARY) {
            button.setStyle("-fx-background-color: #696865; -fx-text-fill: white;");
            button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #7d7c78; -fx-text-fill: white;"));
            button.setOnMousePressed(event -> button.setStyle("-fx-background-color: #5a5957; -fx-text-fill: white;"));
            button.setOnMouseReleased(event -> button.setStyle("-fx-background-color: #7d7c78; -fx-text-fill: white;"));
            button.setOnMouseExited(event -> button.setStyle("-fx-background-color: #696865; -fx-text-fill: white;"));

        }
    }
    //Set style of elements on window
    public void setStyle(int style) {
        if (style == Style.LIGHT) {
            layout.setStyle("-fx-background-color: white");
            layout.setStyle("-fx-text-fill: black");

            setButtonStyle(yesButton,style);
            setButtonStyle(noButton,style);

        } else if (style == Style.DARK) {
            layout.setStyle("-fx-background-color: #504f4e");
            textLabel.setStyle("-fx-text-fill: white;");

            setButtonStyle(yesButton,style);
            setButtonStyle(noButton,style);

        } else if (style == Style.DARK_SECONDARY) {
            layout.setStyle("-fx-background-color: #504f4e; -fx-border-color: white; -fx-border-width: 2px;");
            textLabel.setStyle("-fx-text-fill: white;");

            setButtonStyle(yesButton,style);
            setButtonStyle(noButton,style);

        } else {
            setStyle(Style.LIGHT);
        }
    }
}
