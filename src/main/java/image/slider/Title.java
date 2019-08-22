package image.slider;

import java.awt.*;

public class Title {
    private String text;
    private Rectangle dimension;

    public Title(String text, Rectangle dimension) {
        this.text = text;
        this.dimension = dimension;
    }

    public String getText() {
        return text;
    }

    public Rectangle getDimension() {
        return dimension;
    }

    public void setDimension(Rectangle dimension) {
        this.dimension = dimension;
    }
}
