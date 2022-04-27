package behavioralpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * --- 监听器模式 ---
 * 1. 包含三个角色：事件监听器，事件对象，事件源
 * 2. 新建事件源
 * 3. 给事件源添加监听器【同时在监听器里定义监听器的匿名内部类,完成监听器的 handleEvent(事件)的方法,同时判断事件类型,给出对应处理逻辑】
 * 4. 定义事件对象,构造函数可以传入 Obj类型
 * 5. 通过 事件源.notifyListener(Event),来调用监听器的 handleEvent(事件)方法
 */
public class EventListenerPattern {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource(); // 事件源

        eventSource.addListener(new EventListener() { // 事件源 调用监听器的一个方法，并传递事件对象

            public void handleEvent(MyEventObject eventObject) {
                eventObject.doEvent();

                if (eventObject.getSource().equals("新增好友申请")) {
                    System.out.println("新增好友成功"); // 回调
                } else if (eventObject.getSource().equals("删除好友申请")) {
                    System.out.println("删除好友成功"); // 回调
                }else System.out.println("暂时不支持该操作.");
            }
        });

        eventSource.addListener(new EventListener() { // 事件源 调用监听器的一个方法，并传递事件对象
            public void handleEvent(MyEventObject eventObject) {
                eventObject.doEvent();
                if (eventObject.getSource().equals("新增好友申请")) {
                    System.out.println("新增好友成功日志插入"); // 回调
                } else if (eventObject.getSource().equals("删除好友申请")) {
                    System.out.println("删除好友成功日志插入"); // 回调
                }else System.out.println("empty.");
            }
        });

        MyEventObject eventObject = new MyEventObject("新增好友申请"); // 事件对象
        MyEventObject eventObject2 = new MyEventObject("删除好友申请"); // 事件对象
        MyEventObject eventObject3 = new MyEventObject("隐藏好友申请"); // 事件对象

        eventSource.notifyListenerEvent(eventObject); // 触发事件
        eventSource.notifyListenerEvent(eventObject2); // 触发事件
        eventSource.notifyListenerEvent(eventObject3); // 触发事件
    }
}

/**
 * 事件监听器接口,在实际调用时可以通过匿名内部类实现,也可以提前手动实现
 */
interface EventListener extends java.util.EventListener {
    //事件处理
    void handleEvent(MyEventObject eventObject);

}

/**
 * 事件对象,作为一个传递值,构造函数接收 Obj类型参数【所以支持各种类型的事件】
 */
class MyEventObject extends java.util.EventObject {

    public MyEventObject(Object source) {
        super(source);
    }

    public Object getSource() {
        return source;
    }

    public void doEvent() {
        System.out.println("准备操作：\n" + this.getSource());
    }
}

/**
 * 事件源, 带有一个监听器的列表, 同时可以增删监听器, 并且通过 nofityListenerEvent()方法通知监听器去调用它的 handleEvent()方法.
 */
class EventSource {

    // 监听器列表，监听器的注册 加入此列表
    private List<EventListener> listeners = new ArrayList<EventListener>();

    public void addListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    public void notifyListenerEvent(MyEventObject eventObject) {
        for (EventListener eventListener : listeners) {
            eventListener.handleEvent(eventObject);
        }
    }
}