package touhou.bases;

import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.explosion.Explosion;
import touhou.players.PlayerSpell;

import java.util.ArrayList;

public class RemoveOutside {
    private ArrayList<PlayerSpell> playerSpells;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    public RemoveOutside() {
    }

    public void setPlayerSpells(ArrayList<PlayerSpell> playerSpells) {
        this.playerSpells = playerSpells;
    }

    public void setEnemyBullets(ArrayList<EnemyBullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setExplosions(ArrayList<Explosion> explosions) {
        this.explosions = explosions;
    }

    public void removeOutside(){
        for (int i = 0; i < playerSpells.size(); i++) {
            if (playerSpells.get(i).constraints.outside(playerSpells.get(i).position)){
                playerSpells.remove(i);
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).constraints.outside(enemies.get(i).getPosition()) || enemies.get(i).getBlood() <= 0){
                //System.out.println("Remove enemy");
                enemies.remove(i);
            }
        }
        for (int i = 0; i < enemyBullets.size(); i++) {
            if (enemyBullets.get(i).constraints.outside(enemyBullets.get(i).position) || enemyBullets.get(i).getBlood() <= 0){
                enemyBullets.remove(i);
            }
        }

        for (int i = 0; i < explosions.size(); i++) {
            if (explosions.get(i).getAnimation().isResetFrame()){
                explosions.remove(i);
            }
        }
    }
}
