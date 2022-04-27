package structuralpattern;

/**
 * 桥接模式
 *
 * 目的：把抽象部分和实现部分分离开，使得他们都可以独立地变化
 *
 * 使用场景：实现类可能会有多个角度，比如一个八面佛，有16个手臂，每个手臂拿的东西都不一样。
 *                             比如启动一个机器，它的不同部分需要实现不同的功能。
 *
 * 优缺点：1.抽象和实现分离，容易拓展，实现细节客户不可见；2.增加了系统的理解和设计难度
 *
 * 示例：在一个大的背景下，不同的地方分别画不同颜色的圆
 *      1.有一个[各个不同的具体动作]抽象出来的接口[动作]，然后有不同的实现，用来处理具体的不同的动作的内容
 *      2.有一个桥接类的抽象类，这里面初始化的时候接收[动作]，并且有一个实际工作的 draw()方法
 *      3.有一个桥接类的具体实现类(也可以是多个实现类，都继承于桥接抽象类)，这里构造方法实际去接收 各种参数和一个[动作]，
 *          然后有一个实际工作的 draw()方法去调用[抽象动作]的实际工作的方法( 这里是drawCircle())
 *      4.在客户端里直接实现 抽象的桥接类 实例 = new 具体的桥接类(参数 ...,[具体动作])
 *          然后调用 实例.工作方法 即可
 */
public class BridgePattern {
    public static void main(String[] args) {
        AbstractShape background = new MyCircle(100,100,500,new Background());
        AbstractShape redShape = new MyCircle(10,10,50,new RedCircle());
        AbstractShape greenShape = new MyCircle(50,50,50,new GreenCircle());
        background.draw();
        redShape.draw();
        greenShape.draw();
    }
}


abstract class AbstractShape{
    DrawAPI drawAPI;
    AbstractShape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    abstract void draw();
}

class MyCircle extends AbstractShape{
    private int x, y, radius;
    MyCircle(int x,int y,int radius,DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    void draw() {
        drawAPI.drawCircle(x,y,radius);
    }
}

interface DrawAPI{
    void drawCircle(int x,int y,int radius);
}
class Background implements DrawAPI{
    public void drawCircle(int x,int y,int radius) {
        System.out.printf("x is %d,y is %d,radius is %d,this is the background.\n", x, y, radius);
    }
}
class RedCircle implements DrawAPI{
    public void drawCircle(int x,int y,int radius) {
        System.out.printf("x is %d,y is %d,radius is %d,and the color is red.\n", x, y, radius);
    }
}
class GreenCircle implements DrawAPI{
    public void drawCircle(int x,int y,int radius) {
        System.out.printf("x is %d,y is %d,radius is %d,and the color is green.", x, y, radius);
    }
}