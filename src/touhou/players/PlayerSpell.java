package touhou.players;

import tklibs.SpriteUtils;
import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 8/2/17.
 */
public class PlayerSpell {
    public Vector2D position;
    private ImageRenderer renderer;
    private ImageRenderer auraRenderer;
    public Constraints constraints;
    private FrameCounter coolDownCounter;
    private Animation animation;
    private BufferedImage[] imageSprite;
    private Vector2D direction;
    private int blood = 2;
    private int damage = 1;
    private boolean pow;

    private BufferedImage aura = SpriteUtils.loadImage("assets/images/sphere/thunder.png");

    public PlayerSpell() {
        imageSprite = imageSprite = Sprite.getSprites("assets/images/sphere/", 4);
        animation = new Animation(imageSprite, 10);
        renderer = new ImageRenderer(animation.getSprite());
        auraRenderer = new ImageRenderer(aura);
        position = new Vector2D();
        this.setConstraints(new Constraints(31, 650, 8, 384));
        pow = false;
    }

    public void reduceBlood(int t){
        this.blood -= t;
    }
    public Vector2D getPosition() {
        return position;
    }

    public void setPow(boolean pow) {
        this.pow = pow;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public void render(Graphics2D g2d) {
        if (pow){
            auraRenderer.render(g2d, position);
        }
        renderer.render(g2d, position);
    }

    public void run() {
        if (pow){
            damage = 2;
        }
        animation.update();
        renderer.setImage(animation.getSprite());
        position.addUp(direction);
    }
}
