package touhou.enemies;

import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBullet {
    public Vector2D position;
    private ImageRenderer renderer;
    public Constraints constraints;
    private FrameCounter coolDownCounter;
    private Animation animation;
    private BufferedImage[] imageSprite;
    private Vector2D direction ;
    private int damage = 1;
    private int blood = 2;

    public EnemyBullet() {
        imageSprite = imageSprite = Sprite.getSprites("assets/images/enemies/bullets/", 7);
        animation = new Animation(imageSprite, 10);
        this.setConstraints(new Constraints(31, 650, 8, 384));
        position = new Vector2D();
        renderer = new ImageRenderer(animation.getSprite());
    }

    public void reduceBlood(int t){
        this.blood -= t;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public Vector2D getPosition() {
        return position;
    }

    public int getBlood() {
        return blood;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
    public void run(){
        animation.update();
        renderer.setImage(animation.getSprite());
        position.addUp(direction);
    }

    public void render(Graphics2D g2d) {
        renderer.render(g2d, position);
    }
}
