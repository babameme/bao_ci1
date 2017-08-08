package touhou.inputs;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * Created by huynq on 8/2/17.
 */
public class InputManager {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean xPressed;
    //public boolean releasedLR;

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = true;
                break;
            case VK_LEFT:
                leftPressed = true;
                break;
            case VK_UP:
                upPressed = true;
                break;
            case VK_DOWN:
                downPressed = true;
                break;
            case VK_X:
                xPressed = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        //releasedLR = false;
        switch (e.getKeyCode()) {
            case VK_RIGHT:
                rightPressed = false;
                //releasedLR = true;
                break;
            case VK_LEFT:
                leftPressed = false;
                //releasedLR = true;
                break;
            case VK_UP:
                upPressed = false;
                break;
            case VK_DOWN:
                downPressed = false;
                break;
            case VK_X:
                xPressed = false;
                break;
        }
    }
}
