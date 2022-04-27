package creationalpattern;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 抽象工厂模式
 *
 * 两个前置概念： ①. 产品等级[ 电视机子类有海尔电视机，海信电视机，TCL电视机 ]
 *             ②. 产品族[ 一个工厂可以生产位于不同产品等级结构中的一组产品，例如海尔工厂既可以生产电冰箱，也可以生产电视机 ]
 *
 * 抽象工厂包含的四个角色:
 *          AbstractFactory: 抽象工厂
 *          ConcreteFactory: 具体工厂
 *          AbstractProduct: 抽象产品
 *          Product: 具体产品
 *
 * 具体场景：在很多软件系统中需要更换界面主题，要求界面中的按钮、文本框、背景色等一起发生改变时，可以使用抽象工厂模式进行设计
 */
public class AbstractFactory {
    public static void main(String[] args) {

        AbstractFactory_Interface HaierFactory = new HaierFactory();
        AbstractFactory_Interface TCLFactory = new TCLFactory();

        HaierTV_One haierTV = (HaierTV_One) HaierFactory.createTV();
        HaierRefrigerator haierRefrigerator = (HaierRefrigerator) HaierFactory.createRefrigerator();
        TCLTV tcltv = (TCLTV) TCLFactory.createTV();
        TCLRefrigerator tclRefrigerator = (TCLRefrigerator) TCLFactory.createRefrigerator();

        haierTV.open();
        haierRefrigerator.open();
        tcltv.open();
        tclRefrigerator.open();
    }

}

// 抽象工厂
interface AbstractFactory_Interface{
    Object createTV();
    Object createRefrigerator();
}
// 具体工厂: Haier
class HaierFactory implements AbstractFactory_Interface{

    public HaierTV_One createTV() {
        return new HaierTV_One();
    }

    public HaierRefrigerator createRefrigerator() {
        return new HaierRefrigerator();
    }
}
// 具体工厂: TCL
class TCLFactory implements AbstractFactory_Interface{

    public TCLTV createTV() {
        return new TCLTV();
    }

    public TCLRefrigerator createRefrigerator() {
        return new TCLRefrigerator();
    }
}
// 抽象产品
interface AbstractTV{
    void open();
}
// 抽象产品
interface AbstractRefrigerator{
    void open();
}
// 具体产品清单
class HaierTV_One implements AbstractTV{
    public void open() {
        System.out.println("I've turned on the TV of Haier.");
    }
}

class HaierRefrigerator implements AbstractRefrigerator{
    public void open() {
        System.out.println("I open the door of the Haier refrigerator.");
    }
}

class TCLTV implements AbstractTV{
    public void open() {
        System.out.println("I just turned on the TV of TCL.");
    }
}

class TCLRefrigerator implements AbstractRefrigerator{
    public void open() {
        System.out.println("I open the door of the TCL refrigerator.");
    }
}