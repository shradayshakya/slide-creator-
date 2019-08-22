package image.slider;

import java.awt.Rectangle;
import java.io.File;

public class Image {
    private File path;
    private Rectangle dimension;

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public Rectangle getDimension() {
        return dimension;
    }

    public void setDimension(Rectangle dimension) {
        this.dimension = dimension;
    }

    public Image(File path, Rectangle dimension) {
        this.path = path;
        this.dimension = dimension;
    }
}
