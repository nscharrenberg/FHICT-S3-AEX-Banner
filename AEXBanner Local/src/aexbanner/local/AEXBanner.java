/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner.local;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Noah Scharrenberg
 */
public class AEXBanner extends Application {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    public static final int NANO_TICKS = 15000000;// FRAME_RATE = 1000000000/NANO_TICKS = 50;
    public final double textSpeed = 12;
    
    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;
    
    @Override
    public void start(Stage primaryStage) {
        Font font = new Font("Arial", HEIGHT);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.YELLOW);
        
        Pane root = new Pane();
        root.getChildren().add(text);
        root.setStyle("-fx-background-color: black");
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        primaryStage.setTitle("AEX Banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
        
        controller = new BannerController(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void setRates(String rates) {
        text.setText(rates);
        text.relocate(textPosition, 0);
        
    }
    
}
