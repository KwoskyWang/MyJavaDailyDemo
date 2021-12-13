//函数式编程
//一. 含义
//    创建易于理解, 充分测试以及可靠的小块, 使用时将它们组合. 相对于面向对象思想对数据的抽象, (functional programming) FP 是对行为的抽象.
//二. 由来
//    计算机早期, 内存稀缺且昂贵, 程序员通过 自修改代码(self modifying coding) 来节省内存资源.
//三. 特点
//    传入参数,输出结果. 这个过程中不会修改任何外部值(包括参数), 这也避免了多线程中的竞争问题(因为不会修改现有值而是生成新值)
public class NewAndOldVersionCompare {

    Strategy strategy;
    String msg;
    NewAndOldVersionCompare(String msg){ // 在构造方法里设置一个默认策略
        strategy = new Strategy1();
        this.msg = msg;
    }

    void showTheStrategy(){ // 执行策略
        System.out.println(strategy.approach(msg));
    }

    void changeTheLocalStrategy(Strategy strategy){ // 这个方法用来把自定义的其他策略注入到此类中🥱(这样就可以使用上面的执行策略入口了)
        this.strategy = strategy;
    }

    public static void main(String[] args) {
        Strategy[] strategies = {
                new Strategy() {                // 这是匿名内部类的写法, 比较笨重
                    public String approach(String msg) {
                        return msg.toUpperCase();
                    }
                },
                msg -> "Hello"+msg,             // 这是 lambda的写法, 简洁
                UnRelatedStrategy::anyMethod    // 这里是方法引用
        };
        NewAndOldVersionCompare newOldCompare = new NewAndOldVersionCompare("Any Message.");
        newOldCompare.showTheStrategy();
        for (Strategy myStrategy : strategies){                 // 具体执行的时候
            newOldCompare.changeTheLocalStrategy(myStrategy);   // 1⃣ 先改变类里面的Strategy类型
            newOldCompare.showTheStrategy();                    // 2⃣ 再去执行对应的Strategy
        }
    }

}

//定义接口
interface Strategy{
    String approach(String msg);
}

//需要动态修改的策略
class Strategy1 implements Strategy{
    public String approach(String msg) {
        return msg.toLowerCase();
    }
}

//随机的一个方法, 可以用作方法引用
class UnRelatedStrategy{
    static String anyMethod(String msg){
        return msg + " " + msg;
    }
}
