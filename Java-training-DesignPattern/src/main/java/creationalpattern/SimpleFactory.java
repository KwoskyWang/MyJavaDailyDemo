package creationalpattern;

/**
 * @createCar(): 定义具体的创建方法，通过传入枚举值来返回不同的实现类
 *
 * 在具体调用时，可以通过 Car xx = createCar()的方法来创建对象 (只需要改变入参就可以得到不同对象)
 *
 * 优点：使用方便，降低代码结构的耦合程度，实际开发中类型参数甚至可以保存在xml等文件中
 * 缺点：工厂职责过重，每次新增产品都需要修改工厂类的代码，与开闭原则相违背，
 *      且所有的产品创建逻辑都集中在一个类，一旦不能正常工作，整个系统都受到影响。
 *
 * 具体示例： public final static DateFormat getDateInstance();
 *          public final static DateFormat getDateInstance(int style);
 *          public final static DateFormat getDateInstance(int style,Locale locale);
 */
public class SimpleFactory {

    static Car createCar(carType carType){
        switch (carType){
            case bicycle:
                return new Bicycle();
            case motorcycle:
                return new Motorcycle();
            case vehicle:
                return new Vehicle();
        }
        return null;
    }

    public static void main(String[] args) {
        Car myCar = createCar(carType.bicycle);
        Car myCar2 = createCar(carType.motorcycle);
        Car myCar3 = createCar(carType.vehicle);
        myCar.run();
        myCar2.run();
        myCar3.run();
    }

}

/**
 * 定义我们要实现的内容的枚举值，这些枚举值都有一一对应的实现【也可以没有】
 */
enum carType{
    bicycle,motorcycle,vehicle
}

/**
 * Car的接口，用来定义实现规范，是具体需要实现的内容的抽象
 */
interface Car{
    void run();
}

/**
 * 下面的三个类都是Car的具体实现类
 */
class Bicycle implements Car{

    public void run() {
        System.out.println("Bicycle's speed is slow.");
    }
}

class Motorcycle implements Car{

    public void run() {
        System.out.println("Motorcycle's speed is normal.");
    }
}

class Vehicle implements Car{

    public void run() {
        System.out.println("Vehicle's speed is fast.");
    }
}