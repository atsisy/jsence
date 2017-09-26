package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * JavaFX Layer class
 * @author Akihiro Takai
 */
public class Layer {

    protected Canvas canvas;
    protected GraphicsContext graphicsContext;

    /**
     * Constructor
     * @param width Width of this Layer
     * @param height Height of this Layer
     */
    public Layer(double width, double height){
        canvas = new Canvas(width, height);
        graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * If you wanna get layer object's JavaFX Canvas, you should use this method.
     * @return self JavaFX Canvas object
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * If you wanna get layer object's JavaFX GraphicsContext, you should use this method.
     * @return self JavaFX Canvas object
     */
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    /**
     * Just clear all area of self.
     */
    public void eraseLayer(){
        this.graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}