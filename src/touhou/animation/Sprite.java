package touhou.animation;

import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Sprite {
    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 32;

    public static BufferedImage loadSprite(String file){
        //System.out.println(file);
        BufferedImage sprite = SpriteUtils.loadImage(file);
        //System.out.println(file);
        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {
        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    // Get sprite from a folder and number sprite
    public static BufferedImage[] getSprites(String file, int number){
        BufferedImage[] Sprites = new BufferedImage[number];
        for (int i = 0; i < number ; i++) {
            Sprites[i] = loadSprite(file + Integer.toString(i) + ".png");
        }
        return Sprites;
    }

}
