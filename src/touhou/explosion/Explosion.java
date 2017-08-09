package touhou.explosion;

import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion {
    private Vector2D position;
    private BufferedImage[] images;
    private Animation animation;
    private ImageRenderer renderer;

    public Explosion() {
        images = Sprite.getSprites("assets/images/enemies/explosion/",8);
        animation = new Animation(images, 3);
        renderer = new ImageRenderer(animation.getSprite());
    }
    public void run(){
        renderer.setImage(animation.getSprite());
        animation.update();
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Animation getAnimation() {
        return animation;
    }
    public void render(Graphics2D g2d){
        if (!animation.isResetFrame())
            renderer.render(g2d, position);
    }
}




