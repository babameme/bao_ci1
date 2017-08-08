package touhou.players;

import tklibs.SpriteUtils;
import touhou.animation.Animation;
import touhou.animation.Sprite;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by huynq on 8/2/17.
 */
public class Player {
    private static final int SPEED = 5;
    private static final int FULLPOW = 150;
    private static final int KEEPPOW = 300;
    private Vector2D position;
    private InputManager inputManager;
    private Constraints constraints;
    public ArrayList<PlayerSpell> playerSpells;
    private ImageRenderer renderer;
    private FrameCounter coolDownCounter;
    private ImageRenderer auraRenderer;
    private boolean spellLock;
    private boolean moveLeftRight;
    private int blood = 30;
    private int damage = 5;
    private int power = 0;

    private BufferedImage[] walkingLeft = Sprite.getSprites("assets/images/players/left/", 6);
    private BufferedImage[] walkingRight = Sprite.getSprites("assets/images/players/right/", 6);
    private BufferedImage[] standing = Sprite.getSprites("assets/images/players/straight/", 7);
    private BufferedImage aura = SpriteUtils.loadImage("assets/images/players/aura/0.png");
    private Animation walkLeft = new Animation(walkingLeft, 5);
    private Animation walkRight = new Animation(walkingRight, 5);
    private Animation stand = new Animation(standing, 5);
    private Animation animation = stand;

    public void setConstraints(Constraints constraints){
        this.constraints = constraints;
    }

    public void setRenderer(ImageRenderer renderer){
        this.renderer = renderer;
    }
    public Player() {
        this.spellLock = false;
        position = new Vector2D(384/2, 600);
        renderer = new ImageRenderer(animation.getSprite());
        auraRenderer = new ImageRenderer(aura);
        coolDownCounter= new FrameCounter(5);
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void run() {
        moveLeftRight = false;
        if (inputManager.upPressed)
            position.addUp(0, -SPEED);

        if (inputManager.downPressed)
            position.addUp(0, SPEED);

        if (inputManager.leftPressed){
            position.addUp(-SPEED, 0);
            animation = walkLeft;
            //animation.reset();
            moveLeftRight = true;
        }
        if (inputManager.rightPressed){
            position.addUp(SPEED, 0);
            animation = walkRight;
            //animation.reset();
            moveLeftRight = true;
        }
        if (!moveLeftRight){
            //animation.stop();
            //animation.reset();
            animation = stand;
            //animation.reset();
        }
        if (constraints != null) {
            constraints.make(position);
        }
        //if (coolDownCounter.run()){
        //    coolDownCounter.reset();
        //}
        castSpell();
        animation.update();
        renderer.setImage(animation.getSprite());
        power++;
        if (power > FULLPOW + KEEPPOW){
            power = 0;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void reduceBlood(int t){
        this.blood -= t;
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.position.set(this.position.add(0, -20));
            newSpell.setDirection(new Vector2D(0,-7));
            if (power >= FULLPOW){
                newSpell.setPow(true);
            }
            playerSpells.add(newSpell);
            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock){
            if (coolDownCounter.run()){
                spellLock = false;
            }
        }
    }

    public void render(Graphics2D g2d) {
        if (power >= FULLPOW){
            auraRenderer.render(g2d, position);
        }
        renderer.render(g2d, position);
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
