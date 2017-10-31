/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RemoteException;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
    public final double textSpeed = 10;
    
    public static final String ipAddress = "127.0.0.1";
    public static final int portNumber = 1099;
    
    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;
    
    @Override
    public void start(Stage primaryStage) throws RemoteException {
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
        
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS) {
                    textPosition -= textSpeed;
                }
                
                if (textPosition + textLength < 0) {
                    textPosition = WIDTH;
                }
                
                text.relocate(textPosition, 0);
                prevUpdate = now;
            }
            
            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = WIDTH;
                text.relocate(textPosition, 0);
                super.start();
            }
        };
        
        animationTimer.start();      
        controller = new BannerController(this, ipAddress, portNumber);
    }
    
    @Override
    public void stop() throws Exception {
        super.stop();
        animationTimer.stop();
        controller.stop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void setRates(String rates) {
        text.setText(rates);
        textLength = text.getLayoutBounds().getWidth();
    }
}
