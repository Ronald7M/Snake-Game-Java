import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SnakeGame extends JFrame {


    public boolean pause=false;
    private int X=30;
    private int Y =40;
    private boolean GAMEOVER=false;
    private int score=0;
    private int tiksForSecond=6;
    private int direction=0;
    private int nextDirection=0;
    Thread thread;
    public TableSnake ts;
    private SnakePanel panel;
    private input in ;
    SnakeGame(){
        init();
    }

    private void init(){
        this.setSize(500,700);
        panel = new SnakePanel(this,X,Y);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(panel);
        this.setLocation(0,0);
        in = new input(this);
        ts=new TableSnake(X, Y);
        thread=new Thread(){
            @Override
            public void run() {
                while(true){
                    if(!pause) {
                        gameLoop();
                    }
                    try {
                        Thread.sleep(1000/tiksForSecond);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        };
        thread.start();
    }
    private void gameLoop()  {
            direction = nextDirection; // Set Direction
            collisionInterpretation(getCollision(direction));
        if (!GAMEOVER) {
                ts.moveSnake(direction); //Move Snake and its tail
            } else {
                int dialogButton = JOptionPane.showConfirmDialog (null, ("You score is: "+score+" \n Do you want to Play again?"),"GAME OVER ",JOptionPane.YES_NO_OPTION);

            if (dialogButton == JOptionPane.YES_OPTION) {
                    playAgain();
                }
            if (dialogButton == JOptionPane.NO_OPTION) {
                EXIT();
            }
            }
        panel.repaint();//Draw game

    }
    public void setNextDirection(int inputDirection){
        if(direction==0 ||direction==2){
            if(inputDirection==1||inputDirection==3){
                nextDirection=inputDirection;
            }
        }
        if(direction==1 ||direction==3){
            if(inputDirection==2||inputDirection==0){
                nextDirection=inputDirection;
            }
        }
    }
    private int getCollision(int direction){
        // return 0 - without collision
        //return 1 - collision with food
        //return 2 -collision with tail
        //return 3 -collision with walls
       if(ts.getValueBox( ts.getNextHeadPosition(direction))==ValueBox.EMPTY){
            return 0;
       }
        if(ts.getValueBox( ts.getNextHeadPosition(direction))==ValueBox.FOOD){
            return 1;
        }
        if(ts.getValueBox( ts.getNextHeadPosition(direction))==ValueBox.TAIL){
            return 2;
        }
        if(ts.getValueBox( ts.getNextHeadPosition(direction))==ValueBox.WALL){
            return 3;
        }

        return -1;
    }
    private void incrementScore(){
        score++;
        this.setTitle("SCORE ->  "+score);
    }
    private void collisionInterpretation(int collision){
        if(collision==0){
            return;
        }
        if(collision==1){
            incrementScore();
            ts.addTail();
            ts.moveFood();
        }
        if(collision==2){
            GAMEOVER=true;
        }
        if(collision==3){
            GAMEOVER=true;
        }

    }

    public void playAgain(){
        score=0;
        this.setTitle("SCORE ->  "+score);
        direction=0;
        nextDirection=0;
        ts=new TableSnake(X, Y);
        GAMEOVER=false;
    }
    private void EXIT(){
        dispose();
        thread.stop();

    }
    public void pause(){
        pause=!pause;
                if(!pause){
                    this.setTitle("SCORE ->  "+score);
                }else{
                    this.setTitle("PAUSE");
                }

    }


}
