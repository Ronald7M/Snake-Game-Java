import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {
    private Color head;
    private Color tail;
    private Color food;
    private Color wall;
    private Color backGround;
    private int x=30;
    private int y=40;

    private int X=500/30;
    private int Y=(700-25)/40;


    void init(){
        head=new Color(14, 252, 0);
        tail=new Color(121, 140, 11);
        food=new Color(250,0,0);
        wall=new Color(0, 74, 255);
        backGround=new Color(0,0,0);
    }

   private SnakeGame sg;
    SnakePanel(SnakeGame sg,int x, int y){
        this.sg=sg;
        this.x=x;
        this.y=y;
        init();
    }

    public void paintComponent(Graphics g){
        uppNewSizeScreen();
        g.setColor(wall);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        for (int i = 0; i < sg.ts.getX(); i++) {
            for (int j = 0; j < sg.ts.getY(); j++) {
                int x=i*X;
                int y=j*Y;
                g.setColor(getColor(sg.ts.getTable()[i][j]));
                g.fillRect(x,y,X,Y);

            }


        }
    }

    private Color getColor(ValueBox vb){
        if(vb==ValueBox.TAIL){
            return tail;
        }
        if(vb==ValueBox.EMPTY){
            return backGround;
        }
        if(vb==ValueBox.HEAD){
            return head;
        }
        if(vb==ValueBox.FOOD){
            return food;
        }
        if(vb==ValueBox.WALL){
            return wall;
        }
        return Color.black;
    }
    private void uppNewSizeScreen(){
        System.out.println(this.getSize());
        X=this.getSize().width/x;
        Y=this.getSize().height/y;

    }



}
