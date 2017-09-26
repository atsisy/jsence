package ui;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import static core.Value.WINDOW_WIDTH;

/**
 * JavaFX Footer class. Extends from Layer class.
 * @author Akihiro Takai
 */
public class Footer extends Layer {

    /**
    * Background color
     */
    private Color background_color;

    /**
     * Constructor
     * @param width Width of this Footer
     * @param height Height of this Footer
     */
    public Footer(double width, double height){
        super(width, height);
        background_color = new Color(0.7f, 0.7f, 0.7f, 1.0f);
    }

    /**
     * Constructor
     * @param color Alternative color of background
     */
    public void setColor(Color color){
        background_color = color;
    }

    /**
     * Draw text in own drawable area
     * @param str String
     * @param from_left Parameter of from left
     */
    public void PutText(String str, int from_left){
        graphicsContext.setFill(background_color);
        graphicsContext.fillRect(from_left - 100, 0, WINDOW_WIDTH, 20);

        graphicsContext.setFill(new Color(0.2f, 0.2f, 0.2f, 1.0f));
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.fillText(str, from_left, 10);
    }
}