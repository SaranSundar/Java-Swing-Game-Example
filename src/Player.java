import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private boolean left, right, up, down;
    private int MOVE_SPEED = 5;
    private Image sprite;

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = loadSprite("res/ghost.png");
    }

    public void keyPressed(int key) {
        //int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (key == KeyEvent.VK_UP) {
            up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            down = true;
        }
    }

    public void keyReleased(int key) {
        //int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (key == KeyEvent.VK_UP) {
            up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

    public void move() {
        if(right){
            x += MOVE_SPEED;
        }
        else if(left){
            x -= MOVE_SPEED;
        }
        if(up){
            y -= MOVE_SPEED;
        }
        else if(down){
            y += MOVE_SPEED;
        }
    }

    public Image loadSprite(String path) {
        ImageIcon ii = new ImageIcon(path);
        return ii.getImage();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getSprite() {
        return sprite;
    }
}
