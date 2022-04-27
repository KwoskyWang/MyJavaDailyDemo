package structuralpattern;

/**
 * 代理模式
 *
 * 模式重点：1⃣ Subject 调用的对象，实际想要实现的是 RealSubject
 *         2⃣ Proxy 代理器
 *         3⃣ RealSubject 实际需要实现的对象
 */
public class ProxyPattern {

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.doSomething();
    }
}

class Proxy{
    RealSubject realSubject;
    void doSomething(){
        System.out.println("Before do sth. Serialize the data.");
        realSubject = new RealSubject();
        realSubject.doSomething();
        System.out.println("After do sth. Serialize the data.");
    }
}
class Subject{
    Proxy proxy;
    void doSomething(){
        proxy = new Proxy();
        proxy.doSomething();
    }
}
class RealSubject{
    void doSomething(){
        System.out.println("RealSubject do sth.");
    }
}