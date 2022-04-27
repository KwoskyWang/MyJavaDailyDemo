package structuralpattern;

/**
 * 外观模式
 *
 * 目的：为子系统的一组接口提供了一个统一的界面，定义了一个高层的接口，使得子系统更加容易使用
 *
 * 示例：去医院很麻烦，要挂号，门诊，取药，付钱；如果这时候有一个接待员一次性帮我们把这些都处理了，那么对病人来说就很方便
 *
 * 如何使用：在客户端和复杂系统里加入一层，这一层来处理调用顺序，依赖关系等各种事情
 *
 * 对比：建造者模式，建造者模式也是在建造者中处理各个示例的初始化和先后顺序的关系，但是建造者是把各个部分组合到一起。而外观模式是顺序调用，没有组合功能。w
 */
public class FacadePattern {
    public static void main(String[] args) {
        Host host = new Host();

        host.register();
        host.outpatientService();
        host.getTheMedicine();
        host.payTheBill();

        host.oneTimeForAllTheServices();
    }
}


class Host{
    HospitalOperation register;
    HospitalOperation outpatientService;
    HospitalOperation getTheMedicine;
    HospitalOperation payTheBill;

    public Host(){
        register = new Register();
        outpatientService = new OutpatientService();
        getTheMedicine = new GetTheMedicine();
        payTheBill = new PayTheBill();
    }

    void register(){register.operate();}
    void outpatientService(){outpatientService.operate();}
    void getTheMedicine(){getTheMedicine.operate();}
    void payTheBill(){payTheBill.operate();}

    void oneTimeForAllTheServices(){
        System.out.println("这里一次性把所有的流程全走完");
        register();
        outpatientService();
        getTheMedicine();
        payTheBill();
    }

}

// 医院看病的操作接口
interface HospitalOperation{
    void operate();
}

// 挂号
class Register implements HospitalOperation{
    public void operate(){
        System.out.println("挂号");
    }
}
// 门诊
class OutpatientService implements HospitalOperation{
    public void operate(){
        System.out.println("门诊");
    }
}
// 拿药
class GetTheMedicine implements HospitalOperation{
    public void operate(){
        System.out.println("拿药");
    }
}
// 付钱
class PayTheBill implements HospitalOperation{
    public void operate(){
        System.out.println("付钱");
    }
}