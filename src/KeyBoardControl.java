import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyBoardControl extends AbstractAction {
    public int key;
    public Player player;
    public boolean keyPress;

    public KeyBoardControl(Player player, int key, boolean keyPress) {
        this.player = player;
        this.key = key;
        this.keyPress = keyPress;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(keyPress){
            player.keyPressed(key);
        }
        else{
            player.keyReleased(key);
        }
    }

}