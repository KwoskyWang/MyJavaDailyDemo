import java.util.ArrayList;
import java.util.List;

/**
 * --- 发布-订阅模式 ---
 */
public class PubSubPattern {
    public static void main(String[] args) {

        //新建事件发布器
        Publisher publisher = new MyPublisher();
        //新建通道
        EventChannel channel = new MyEventChannel("channel One.");
        EventChannel channel2 = new MyEventChannel("channel Two.");
        //新建订阅者
        Subscriber subscriber = new MySubscriber("subscriber One.");
        Subscriber subscriber2 = new MySubscriber("subscriber Two.");
        Subscriber subscriber3 = new MySubscriber("subscriber Three.");
        Subscriber subscriber4 = new MySubscriber("subscriber Four.");

        //通道绑定发布器
        publisher.addEventChannel(channel);
        publisher.addEventChannel(channel2);
        //订阅者绑定通道
        channel.addSubscriber(subscriber);
        channel.addSubscriber(subscriber2);
        channel2.addSubscriber(subscriber3);
        channel2.addSubscriber(subscriber4);

        //发布器发布事件
        publisher.notifyEventChannel(channel, "往通道1发送了一个事件.");
        publisher.notifyEventChannel(channel2, "往通道2发送了一个事件.");
    }
}

/*
 * 事件发布器
 * 类型是接口或者抽象类
 */
interface Publisher {
    public void addEventChannel(EventChannel... eventChannels);//添加通道

    public void deleteEventChannel(EventChannel... eventChannels);//删除通道

    public void notifyEventChannel(EventChannel eventChannels, String msg);//通知订阅该通道的观察者
}

/**
 * 事件通道
 */
interface EventChannel {
    public void addSubscriber(Subscriber... subscribers);//添加观察者

    public void deleteSubscriber(Subscriber... subscribers);//删除观察者

    public void notifyAllSubscriber(String msg);//通知所有的观察者
}

/**
 * 订阅者
 */
interface Subscriber {
    public void UpdateMsg(String msg);
}

/**
 * 具体主题
 */
class MyPublisher implements Publisher {

    // 发布通道集合
    private List<EventChannel> mList = new ArrayList<EventChannel>();// 注意这里集合的泛型用的是接口类型

    public void addEventChannel(EventChannel... eventChannels) {
        for (EventChannel eventChannel : eventChannels) {
            mList.add(eventChannel);
        }
    }

    public void deleteEventChannel(EventChannel... eventChannels) {
        for (EventChannel eventChannel : eventChannels) {
            mList.remove(eventChannel);
        }
    }

    // 通知特定通道去更新订阅这个通道的订阅者
    public void notifyEventChannel(EventChannel eventChannel, String msg) {
        eventChannel.notifyAllSubscriber(msg);
    }
}

/**
 * 通道实现
 */
class MyEventChannel implements EventChannel {
    String channelName;

    MyEventChannel(String name) {
        this.channelName = name;
    }

    // 发布通道集合
    private List<Subscriber> mList = new ArrayList<Subscriber>();// 注意这里集合的泛型用的是接口类型

    public void addSubscriber(Subscriber... subscribers) {
        for (Subscriber subscriber : subscribers) {
            mList.add(subscriber);
        }
    }

    public void deleteSubscriber(Subscriber... subscribers) {
        for (Subscriber subscriber : subscribers) {
            mList.remove(subscriber);
        }
    }

    public void notifyAllSubscriber(String msg) {
        System.out.println("From: " + channelName);
        for (Subscriber subscriber : mList) {
            subscriber.UpdateMsg(msg);
        }
    }
}

class MySubscriber implements Subscriber {
    private String name;

    public MySubscriber(String name) {
        this.name = name;
    }

    public void UpdateMsg(String msg) {
        System.out.println(name + "接收到了：" + msg);
    }
}