package behavioralpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 *
 * 解决问题：将请求封装成对象，使得我们可以用不同的请求对客户进行参数化
 *
 * 实现示例：Client——>Stock(我们真正要操作的对象)
 *            |
 *            |                           |--——BuyStock(implements)
 *         Broker——————>Order(Interface)--|
 *   (存放Order集中操作)  (要操作对象的关联体) |--——SellStock(implements)
 *
 * （虽然实例化了具体的命令对象，比如 buyStockOrder，但实际上它(buyStockOrder)调用的还是我们实际要操作的实体对象 Stock的方法）
 *
 * 适用场景：在某些场合需要对我们的行为进行 "记录，撤销/重做，事务"等处理,本示例演示 撤销/重做
 *
 * 优缺点：降低系统耦合度，新的命令容易添加进来；但是导致系统有很多具体的命令类。
 */
public class CommandPattern {
    List<Order> undoList = new ArrayList<Order>();
    List<Order> redoList = new ArrayList<Order>();

    Order buyOrder = null;
    Order sellOrder = null;

    // 执行买卖方法的同时把对应的对象也加入到 undo列表里，以便回退
    public void buyProduct(){
        this.buyOrder.execute();
        undoList.add(this.buyOrder);
    }
    public void sellProduct(){
        this.sellOrder.execute();
        undoList.add(this.sellOrder);
    }
    // 实际执行 undo时，还要把 undo掉的命令加入 redo，以方便一键重做
    public void undo(){
        if (this.undoList.size()>0){
            Order order = this.undoList.get(undoList.size()-1);
            order.undo();
            this.redoList.add(order);
            this.undoList.remove(order);
        }else System.out.println("没有可以undo的步骤");
    }
    // 实际执行 undo时，还要把 undo掉的命令加入 redo，以方便一键重做
    public void redo(){
        if (this.redoList.size()>0){
            Order order = this.redoList.get(redoList.size()-1);
            order.execute();
            this.undoList.add(order);
            this.redoList.remove(order);
        }else System.out.println("没有可以redo的步骤");
    }

    public static void main(String[] args) {
        CommandPattern commandClient = new CommandPattern();
        Stock abcStock = new Stock("battery",10); //我们需要操作的实体对象，比如说库存

        BuyStock buyStockOrder = new BuyStock(abcStock);    //我们针对需要操作对象的各种操作，比如库存的增加(采购)---命令1
        SellStock sellStockOrder = new SellStock(abcStock); //我们针对需要操作对象的各种操作，比如库存的减少(出库)---命令2
//                                                            //这些操作都声明统一的接口
//        Broker broker = new Broker();       //代理人，用来收集针对实体对象的所有操作
//        broker.takeOrder(buyStockOrder);    //收集采购的操作---添加命令1
//        broker.takeOrder(sellStockOrder);   //收集出库的操作---添加命令2
//
//        broker.placeOrders();               // 执行所搜集的命令
        commandClient.buyOrder = buyStockOrder;
        commandClient.sellOrder = sellStockOrder;
        commandClient.buyProduct();
        commandClient.sellProduct();
        System.out.println("我要撤销");
        commandClient.undo();
        System.out.println("我要重做");
        commandClient.redo();

    }
}

interface Order{
    void execute();
    void undo();
}
class BuyStock implements Order{
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
    //  撤销操作，买的撤销操作就是卖
    public void undo() {
        abcStock.sell();
    }
}
class SellStock implements Order{
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
    //  撤销操作，卖的撤销操作就是买
    public void undo() {
        abcStock.buy();
    }
}

class Stock {
    private String name;
    private int quantity;
    Stock(String name,int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public void buy(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", Quantity: " + quantity +" ] sold");
    }
}

class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}