package touhou.enemies;

import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    private Vector2D position;
    public Constraints constraints;
    public ArrayList<EnemyBullet> enemyBullets;
    private ImageRenderer renderer;
    private FrameCounter coolDownCounter;
    private Animation animation;
    private BufferedImage[] imageSprite;
    private boolean bulletLock;
    private Vector2D direction = new Vector2D(0,2);
    private int blood = 15;
    private int damage = 2;
    private boolean boss;

    public Enemy(int type) {
        //System.out.println("New enemy type : " + Integer.toString(type));
        this.bulletLock = false;
        Random generator = new Random();
        this.position = new Vector2D( generator.nextInt(384), 50);
        //System.out.println(Float.toString(position.x));
        //System.out.println(Float.toString(position.y));
        this.boss = false;
        if (type == 0){
            imageSprite = Sprite.getSprites("assets/images/enemies/level0/pink/", 4);
        }
        else if (type == 1){
            imageSprite = Sprite.getSprites("assets/images/enemies/level0/blue/", 4);
        }
        else {
            imageSprite = Sprite.getSprites("assets/images/enemies/level0/black/", 9);
            boss = true;
        }
        animation = new Animation(imageSprite, 5);
        coolDownCounter = new FrameCounter(100);
        renderer = new ImageRenderer(animation.getSprite());
        this.setConstraints(new Constraints(31, 650, 8, 384));
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public int getBlood() {
        return blood;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    public void setCoolDownCounter(FrameCounter coolDownCounter) {
        this.coolDownCounter = coolDownCounter;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public void setEnemyBullets(ArrayList<EnemyBullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public Vector2D getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
    }

    public void reduceBlood(int t){
        this.blood -= t;
    }

    public void run(){
        castBullet();
        animation.update();
        //System.out.println(animation.getCurrentFrame().getCount());
        //System.out.println(animation.getFrames().size());
        renderer.setImage(animation.getSprite());
        Random generator = new Random();
        //direction.x = generator.nextInt(6) - generator.nextInt(6);
        direction.x = 0;
        position.addUp(direction);
    }

    public void cast(int x, int y, int dx, int dy){
        EnemyBullet newBullet = new EnemyBullet();
        newBullet.setPosition(position.add(x, y));
        newBullet.setDirection(new Vector2D(dx, dy));
        enemyBullets.add(newBullet);
    }

    public void castType1(){
        for (int i = -3; i <= 3; i++) {
            cast(- i * 10, 30, -i, 3);
            cast(i * 10, 30, i, 3);
        }
    }

    public void castType2(){
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1 ; j++) {
                if (i != j) {
                    cast(i * 10, j * 10, i, j);
                }
            }
        }
    }

    public void castType3(){
        for (int i = -3; i <= 3 ; i++) {
            cast(i * 40, 20, 0, 3);
        }
    }

    public void castType4(){
        for (int i = - 7; i <= 7; i++){
            cast(i * 30, 30, i, 3);
        }
    }

    public void castBullet(){
        if (coolDownCounter.run() && !bulletLock) {
            if (this.boss){
                castType4();
            }
            else {
                Random generator = new Random();
                int t = generator.nextInt(3);
                if (t == 0)
                    castType1();
                if (t == 1)
                    castType2();
                if (t == 2)
                    castType3();
            }
            bulletLock = true;
            coolDownCounter.reset();
        }
        unlockBullet();
    }
    private void unlockBullet() {
        if (bulletLock){
            if (coolDownCounter.run()){
                bulletLock = false;
            }
        }
    }

    public void render(Graphics2D g2d) {
        //System.out.println("Render ???");
        renderer.render(g2d, position);
    }
}
