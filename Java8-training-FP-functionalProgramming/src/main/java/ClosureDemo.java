/**
 * ①. 闭包的概念: 闭包能够将一个方法作为一个变量去存储,这个方法有能力去访问所在类的自由变量
 *      - Java中能够将方法作为变量存储的就是 对象
 *      - Java如果要让普通对象能够访问类的自由变量,最直接的办法就是使用内部类,因为内部类可以使用外部类的全部变量
 * ②. 闭包的使用: 一般来说, 闭包还要可以传递到外部使用
 *      - Java中由于匿名内部类对外部是隐藏的,所以如果要传递到外部,可以让其实现通用接口,将内部类对象向上转型为接口类型
 *
 *      - 由上可知, Java中常用闭包实现办法: 内部类+接口
 *
 *      - 问题: 实际编码时发现直接使用内部类也是可以达到传递到外部的效果,不确定为什么需要通过接口的方式实现
 */
public class ClosureDemo {
    // 举一个喝牛奶的例子
    public String name = "Pure Milk";
    private int num = 16; //每箱数量
    public ClosureDemo(){
        System.out.println(name+":16/Box");
    }

    /**
     * 闭包
     * @return 喝牛奶的动作
     */
    public Drink drinkMilk(){
        return new Drink() {
            @Override
            public void drink() {
                if (num==0){ System.out.println("Milk empty."); return;}
                num--;
                System.out.println("Drink one bottle of milk.");
            }
        };
    }
    class InnerDrink{
        public void drink() {
            if (num==0){ System.out.println("Milk empty."); return;}
            num--;
            System.out.println("Drink one bottle of milk.");
        }
    }

    /**
     * 获取剩余数量
     */
    public void currentMilk(){
        System.out.println("Still have "+ num +" bottle of milk.");
    }

    public static void main(String[] args) {
        // 内部类+接口
        ClosureDemo demo = new ClosureDemo();
        Drink drink = demo.drinkMilk();
        drink.drink();
        drink.drink();
        demo.currentMilk();

        // 直接内部类
        ClosureDemo.InnerDrink innerDrink = demo.new InnerDrink();
        innerDrink.drink();
        innerDrink.drink();
        demo.currentMilk();
    }

}

interface Drink{
    void drink();
}