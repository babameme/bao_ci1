package touhou.bases.renderers;

import touhou.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 8/5/17.
 */
public class ImageRenderer {
    private BufferedImage image;

    public ImageRenderer(BufferedImage image) {
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void render(Graphics2D g2d, Vector2D position) {
        g2d.drawImage(image,
                (int)(position.x - image.getWidth() / 2),
                (int)(position.y - image.getHeight() / 2),
                null);
    }
}
