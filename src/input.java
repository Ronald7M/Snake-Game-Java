import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class input implements KeyListener {
    SnakeGame sg;
    input(SnakeGame sg){
        this.sg=sg;
        sg.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==87||e.getKeyCode()==38){
                sg.setNextDirection(0);
            }
        if(e.getKeyCode()==68||e.getKeyCode()==39){
            sg.setNextDirection(1);
        }
        if(e.getKeyCode()==83||e.getKeyCode()==40){
            sg.setNextDirection(2);
        }
        if(e.getKeyCode()==65||e.getKeyCode()==37){
            sg.setNextDirection(3);
        }
       if(e.getKeyCode()==32){
           sg.pause();
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
