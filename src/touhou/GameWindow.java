package touhou;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.RemoveOutside;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.impact.Impact;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.event.KeyEvent.*;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private static final int BKSPEED = 1;
    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;
    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;
    private BufferedImage background;
    private RemoveOutside removeOutside = new RemoveOutside();
    private FrameCounter timeEnemy = new FrameCounter(50);
    private int backgroundY = - 2341;
    private int backgroundX = 0;
    private Impact impact = new Impact();
    Player player = new Player();
    ArrayList<PlayerSpell> playerSpells = new ArrayList<PlayerSpell>();
    ArrayList<EnemyBullet> enemyBullets = new ArrayList<EnemyBullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    InputManager inputManager = new InputManager();

    public GameWindow() {
        pack();
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        player.setInputManager(this.inputManager);
        //System.out.println(Integer.toString(getInsets().top));
        //System.out.println(Integer.toString(getInsets().left));
        player.setConstraints(new Constraints(getInsets().top, 650, getInsets().left, 384));
        player.playerSpells = this.playerSpells;
        removeOutside.setEnemies(enemies);
        removeOutside.setEnemyBullets(enemyBullets);
        removeOutside.setPlayerSpells(playerSpells);

        impact.setEnemies(enemies);
        impact.setEnemyBullets(enemyBullets);
        impact.setPlayerSpells(playerSpells);
        impact.setEnemyBullets(enemyBullets);
        impact.setPlayer(player);

        //System.out.println(player.getPosition().toString());

        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(854, 650);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void setMoveBackground(){
        backgroundY += BKSPEED;
        if (backgroundY > 0){
            backgroundY = 0;
        }
    }
    private void run() {
        setMoveBackground();
        player.run();

        for (PlayerSpell playerSpell : playerSpells) {
            playerSpell.run();
        }

        if (timeEnemy.run()){
            Random generator = new Random();
            Enemy newEnemy = new Enemy(generator.nextInt(3));
            newEnemy.setEnemyBullets(this.enemyBullets);
            enemies.add(newEnemy);
            timeEnemy.reset();
        }
        //System.out.println(enemies.size());
        impact.run();
        removeOutside.removeOutside();
        for (Enemy enemy : enemies){
            enemy.run();
        }
        for (EnemyBullet enemyBullet : enemyBullets){
            enemyBullet.run();
        }
        System.out.println(Integer.toString(player.getBlood()));
    }

    private void render() {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 854, 650);
        backbufferGraphics.drawImage(background, backgroundX, backgroundY, null);
        player.render(backbufferGraphics);

        for (PlayerSpell playerSpell: playerSpells) {
            playerSpell.render(backbufferGraphics);
        }
        for (Enemy enemy : enemies){
            enemy.render(backbufferGraphics);
        }
        for (EnemyBullet enemyBullet : enemyBullets){
            enemyBullet.render(backbufferGraphics);
        }

        windowGraphics.drawImage(backbufferImage, 0, 0, null);
    }
}
