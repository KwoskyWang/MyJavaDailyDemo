package behavioralpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * --- 观察者模式 ---
 * 观察者模式组成结构:
 *      1.被观察者接口【①添加观察者 ②删除观察者 ③通知观察者】
 *      2.被观察者实现【实现接口方法，添加删除方法实现通过可变参数可以快速管理】
 *      3.观察者接口【①定义响应(处理被观察者通知的响应)】
 *      4.观察者实现【初始化+实现具体的响应(来自被观察者通知的响应)】
 */
public class ObserverPattern {
    public static void main(String[] args) {
        //创建观察者
        IObserver observers1 = new Observers("王大锤");
        IObserver observers2 = new Observers2("李大锤");
        IObserver observers3 = new Observers3("乔大锤");
        IObserver observers4 = new Observers4("余大锤");
        //创建主题
        IPrettyGirl angelababy = new Angelababy();
        //主题添加观察者
        angelababy.addObservers(observers1,observers2,observers3,observers4);
        //发送消息通知
        angelababy.notifyAllObservers("我不开心");
        //删除观察者
        System.out.println("----------");
        angelababy.deleteObservers(observers1,observers2);
        angelababy.notifyAllObservers("我不开心");
    }
}

/*
 * 抽象主题角色
 * 类型是接口或者抽象类
 */
interface IPrettyGirl {
    public void addObservers(IObserver... observers);//添加观察者

    public void deleteObservers(IObserver... observers);//删除观察者

    public void notifyAllObservers(String msg);//通知所有的观察者
}


/**
 * 具体主题
 */
class Angelababy implements IPrettyGirl {

    // 观察者集合
    private List<IObserver> mList = new ArrayList<IObserver>();// 注意这里集合的泛型用的是接口类型

    public void addObservers(IObserver... observers) {
        for (IObserver iObserver : observers) {
            mList.add(iObserver);
        }
    }

    public void deleteObservers(IObserver... observers) {
        for (IObserver iObserver : observers) {
            mList.remove(iObserver);
        }
    }

    public void notifyAllObservers(String msg) {
        System.out.println("angelababy: " + msg);
        for (IObserver list : mList) {
            list.UpdateMsg(msg);
        }
    }
}

/**
 * 抽象观察者
 */
interface IObserver {
    void UpdateMsg(String msg);
}


/**
 * 具体观察者1
 */
class Observers implements IObserver {
    private String name;

    public Observers(String name) {
        this.name = name;
    }

    public void UpdateMsg(String msg) {
        if ("我不开心".equals(msg)) {
            System.out.println(name + "说：我们去打游戏");
        }
    }
}
/**
 * 具体观察者
 */
class Observers2 implements IObserver {
    private String name;

    public Observers2(String name) {
        this.name = name;
    }

    public void UpdateMsg(String msg) {
        // TODO 自动生成的方法存根
        if ("我不开心".equals(msg)) {
            System.out.println(name + "说：我们去看电影");
        }
    }
}
/**
 * 具体观察者3
 */
class Observers3 implements IObserver {
    private String name;

    public Observers3(String name) {
        this.name = name;
    }

    public void UpdateMsg(String msg) {
        // TODO 自动生成的方法存根
        if ("我不开心".equals(msg)) {
            System.out.println(name + "说：我们去嘿嘿嘿");
        }
    }
}
/**
 * 具体观察者4
 */
class Observers4 implements IObserver {
    private String name;

    public Observers4(String name) {
        this.name = name;
    }

    public void UpdateMsg(String msg) {
        // TODO 自动生成的方法存根
        if ("我不开心".equals(msg)) {
            System.out.println(name + "说：关我屁事");
        }
    }
}