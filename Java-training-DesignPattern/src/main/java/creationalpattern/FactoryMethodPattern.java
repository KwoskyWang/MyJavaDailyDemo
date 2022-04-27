package creationalpattern;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 工厂方法模式
 *
 * 在我的理解里是一个树状结构，客户端只需要知道 需要的产品 和这个产品对应的工厂名
 *
 * 核心代码: 产品 = new 工厂.生产方法()
 *
 * 优点: 用户只需要关心所需要的产品对应的工厂，无需知道创造细节甚至产品名(有默认创建方法)
 * 缺点: 每次添加新的产品除了需要编写新的产品类，还需要编写对应的工厂类
 *
 * 具体示例 JDBC工厂方法： Connection conn=DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=DB;user=sa;password=");
 *                      Statement statement=conn.createStatement();
 *                      ResultSet rs=statement.executeQuery("select * from UserInfo");
 */
public class FactoryMethodPattern{

    public static void main(String[] args) {
        Transport myCA737 = new AirplaneMethodFactory().newTransport();
        Transport myCA747 = new AirplaneMethodFactory().newTransport("BigAirplane");
        Transport myCar = new CarMethodFactory().newTransport();
        Transport myVehicle = new CarMethodFactory().newTransport("Vehicle");

        ArrayList<Transport> myTransport = new ArrayList(Arrays.asList(myCA737,myCA747,myCar,myVehicle));
        for (Transport transport: myTransport) {
            transport.Move();
        }
    }

}

/**
 * 工厂接口
 */
interface TransportFactory{
    Transport newTransport(String Name);
}
// 工厂1
class CarMethodFactory implements TransportFactory{
    public Transport newTransport(String type) {
        if (type.equals("Vehicle")){
            return new MyVehicle();
        }else return new MyBicycle();
    }
    public Transport newTransport() {
        return new MyBicycle();
    }
}
// 工厂2
class AirplaneMethodFactory implements TransportFactory{
    public Transport newTransport(String type) {
        if (type.equals("BigAirplane")){
            return new CA747();
        }else return new CA737();
    }
    public Transport newTransport() {
        return new CA737();
    }
}

// 产品接口
interface Transport{
    void Move();
}
// 产品1-1
class MyBicycle implements Transport{
    public void Move() {
        System.out.println("Bicycle's speed is slow.");
    }
}
// 产品1-2
class MyVehicle implements Transport{
    public void Move() {
        System.out.println("Bicycle's speed is fast.");
    }
}
// 产品2-1
class CA737 implements Transport{
    public void Move() {
        System.out.println("CA737's speed is very fast.");
    }
}
// 产品2-2
class CA747 implements Transport{
    public void Move() {
        System.out.println("CA747's speed is super fast.");
    }
}