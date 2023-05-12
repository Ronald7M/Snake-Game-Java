import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableSnake {
    private Random rand= new Random();
    private int X;
    private int Y;
    private ValueBox table[][];
    private  List<Point> snake = new ArrayList<>();
    private Point head;
    private Point food;
    TableSnake(int x,int y){
        X=x;
        Y=y;
        table=new ValueBox[X][Y];
        initTable();
        init();

    }
    private void initTable(){
        //Init table
        for (int i = 0; i < X; i++) {
            for (int i1 = 0; i1 < Y; i1++) {
                //set walls in table
                if(i==0||i==X-1||i1==0||i1==Y-1){
                    table[i][i1]=ValueBox.WALL;
                }
                else{
                    table[i][i1] = ValueBox.EMPTY;
                }
            }
        }


    }

       private  void init(){
            //Create the head
            head=new Point(X/2,Y/2);
            snake.add(new Point((X/2)+1,Y/2));

            //Set the food
           moveFood();
        }
    private void refreshTable(){
        initTable();
        table[food.x][food.y]=ValueBox.FOOD;
        table[head.x][head.y]=ValueBox.HEAD;
        for (int i = 0; i < snake.size(); i++) {
                table[snake.get(i).x][snake.get(i).y]=ValueBox.TAIL;

        }
    }
    public void  moveSnake(int direction){
        //Move the tail
        Point[] copySnake=convListToArr(snake);
        for (int i = 0; i < snake.size(); i++) {
            if(i==0){
                snake.set(i,head);
            }else {
                snake.set(i, new Point(copySnake[i-1].x,copySnake[i-1].y));
            }
        }
        head=getNextHeadPosition(direction);

    }
    public Point  getNextHeadPosition(int direction){
        if(direction==0){
            return new Point(head.x, head.y-1);
        }
        if(direction==2){
            return new Point(head.x, head.y+1);
        }
        if(direction==1){
            return new Point(head.x+1, head.y);
        }
        if(direction==3){
            return new Point(head.x-1, head.y);
        }
        return null;
    }

     public ValueBox[][]  getTable(){
        refreshTable();
        return table;
     }
     public int getX(){
        return X;
     }
    public int getY(){
        return Y;
    }

    Point[] convListToArr(List<Point> p){
        Point ret[]=new Point[p.size()];
        for (int i = 0; i < p.size(); i++) {
            ret[i]=new Point(p.get(i).x,p.get(i).y);
        }
        return ret;
    }
    public void addTail(){
        snake.add(new Point(snake.get(snake.size()-1).x,snake.get(snake.size()-1).y));
    }
    public ValueBox getValueBox(Point checkPoint){
     return table[checkPoint.x][checkPoint.y];
    }
    private void setFood(Point food){
        this.food=food;
    }
    public void moveFood(){
        int x= rand.nextInt(X);
        int y= rand.nextInt(Y);
        while(getValueBox(new Point(x,y))!=ValueBox.EMPTY){
            x= rand.nextInt(X);
            y= rand.nextInt(Y);
        }
        setFood(new Point(x,y));
    }

}