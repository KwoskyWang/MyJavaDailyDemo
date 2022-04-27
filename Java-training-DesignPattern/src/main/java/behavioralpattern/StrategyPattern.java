package behavioralpattern;

/**
 * 策略模式
 *
 * 适用场景：同样一个问题，有多种解决方式，每一种解决方式是一种策略；
 *              这里的解决方式也可以叫做算法，比如 +，-，*，/；
 *                   在电商场景下，针对订单，比如 [合并订单]，[拆单]，合单拆单可能又对应多种情况。
 *
 * 把策略的 真正实现和 调用分开来，这样下次增加新的策略，只需要编写 策略实现代码和在客户端里新建一个 Context(策略)即可
 *
 *
 * 优缺点：客户端代码很干净，没有声明任何一种策略类作为成员变量；只需要声明待处理的数据，然后选择策略即可；但是多了很多策略类。
 *
 */
public class StrategyPattern {
    public static void main(String[] args) {
        int param_One = 10;
        int param_Two = 20;
        Context context = new Context(new StrategyAdd());
        context.execute(param_One,param_Two);
        context = new Context(new StrategySubtract());
        context.execute(param_One,param_Two);
        context = new Context(new StrategyMultiply());
        context.execute(param_One,param_Two);
    }
}

interface Strategy{
    int doOperation(int param_One,int param_Two);
}

class StrategyAdd implements Strategy{
    public int doOperation(int param_One, int param_Two) {
        return param_One+param_Two;
    }
}
class StrategySubtract implements Strategy{
    public int doOperation(int param_One, int param_Two) {
        return param_One-param_Two;
    }
}
class StrategyMultiply implements Strategy{
    public int doOperation(int param_One, int param_Two) {
        return param_One*param_Two;
    }
}

class Context{
    Strategy strategy;
    Context(Strategy strategy){
        this.strategy = strategy;
    }
    int execute(int param_One,int param_Two){
        System.out.printf("执行结果：%d\n",strategy.doOperation(param_One,param_Two));
        return strategy.doOperation(param_One,param_Two);
    }
}