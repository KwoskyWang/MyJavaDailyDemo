package structuralpattern;

/**
 * 装饰器模式
 *
 * 装饰器模式的重点在于：
 *                  ①. 装饰器和被装饰的对象声明同一个接口
 *                  ②. 装饰器需要有一个抽象类，抽象类里接收需要被装饰的类对象
 *                  ③. 具体的装饰器继承于抽象的装饰器，可以有多个具体的装饰器，分别进行不同的装饰
 *
 * 使用场景：装饰器可以看做一种类的增强，并且不需要给目标对象做很多子类来做拓展，而是通过装饰器来做拓展
 *         Client -> A
 *         Client -> B(A)   做基于 B的增强
 *         Client -> C(A)   做基于 C的增强
 */
public class DecoratorPattern {
    public static void main(String[] args) {

        Shape circle = new Circle();
//        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
//        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        // 如果我们的 ShapeDecorator.class没有声明 Shape，那就只能用上面那种写法
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        Shape greenCircle = new GreenShapeDecorator(new Circle());
        Shape greenRectangle = new GreenShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();

        System.out.println("\nCircle of green border");
        greenCircle.draw();

        System.out.println("\nRectangle of green border");
        greenRectangle.draw();
    }
}
// 创建一个接口：形状
interface Shape {
    void draw();
}
// 接口的实现类：长方形
class Rectangle implements Shape {
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
// 接口的实现类：圆
class Circle implements Shape {
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
// 实现了接口的抽象类：抽象装饰器类 【这一步抽象存在的必要是为了支持更多的具体的装饰器类，如果我把具体的增强写在这个类里，那我只能进行一种类型的装饰】
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
// 具体的装饰器类，改边界为红色
class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
// 具体的装饰器类，改边界为绿色
class GreenShapeDecorator extends ShapeDecorator {

    public GreenShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setGreenBorder(decoratedShape);
    }

    private void setGreenBorder(Shape decoratedShape){
        System.out.println("Border Color: Green");
    }
}