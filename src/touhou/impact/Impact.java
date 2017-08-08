package touhou.impact;

import touhou.bases.Vector2D;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.util.ArrayList;

public class Impact {
    private ArrayList<PlayerSpell> playerSpells;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<Enemy> enemies;
    private Player player;

    public void setPlayerSpells(ArrayList<PlayerSpell> playerSpells) {
        this.playerSpells = playerSpells;
    }

    public void setEnemyBullets(ArrayList<EnemyBullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private boolean hug(Vector2D a, Vector2D b){
        return a.dis(b) <= 15;
    }

    public void bulletPlayer(){
        //System.out.println(player.getPosition().toString());
        for (EnemyBullet enemyBullet : enemyBullets){
            if (enemyBullet == null || player == null)
                continue;
            if (hug(enemyBullet.getPosition(), player.getPosition())){
                player.reduceBlood(enemyBullet.getDamage());
                enemyBullet.reduceBlood(player.getDamage());
            }
        }
    }

    public void spellEnemy(){
        for (PlayerSpell playerSpell : playerSpells){
            for (Enemy enemy : enemies){
                if (playerSpell == null || enemy == null)
                    continue;
                if (hug(playerSpell.getPosition(), enemy.getPosition())){
                    enemy.reduceBlood(playerSpell.getDamage());
                    playerSpell.reduceBlood(enemy.getDamage());
                }
            }
        }
    }

    public void enemyPlayer(){
        //System.out.println(player.getPosition().toString());
        for (Enemy enemy : enemies){
            if (enemy == null || player == null)
                continue;
            if (hug(enemy.getPosition(), player.getPosition())){
                player.reduceBlood(enemy.getDamage());
                enemy.reduceBlood(player.getDamage());
            }
        }
    }

    public void spellBullet(){
        for (PlayerSpell playerSpell : playerSpells){
            for (EnemyBullet enemyBullet : enemyBullets){
                if (playerSpell == null || enemyBullet == null)
                    continue;
                if (hug(playerSpell.getPosition(), enemyBullet.getPosition())){
                    playerSpell.reduceBlood(enemyBullet.getDamage());
                    enemyBullet.reduceBlood(playerSpell.getDamage());
                }
            }
        }
    }
    public void run(){
        //test();
        bulletPlayer();
        spellEnemy();
        enemyPlayer();
        spellBullet();
    }
}
