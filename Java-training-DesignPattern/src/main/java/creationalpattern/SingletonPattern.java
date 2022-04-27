package creationalpattern;

/**
 * 序言：
 * ①.多个系统中都可以进行打印任务，但是同一时间只能有一个打印机工作
 * ②.多个程序可以唤起任务管理器，但是一个系统只能有一个任务管理器
 * <p>
 * 如何保证这个类只有一个实例，并且易于访问，定义一个全局变量？但是包含全局变量的类可以实例化多个对象。
 * 更好的办法是让这个类自己保存它唯一的实例，通过类的机制可以保证没有其他实例会被创建，再提供一个访问它的方法，这就是单例模式的设计动机
 *
 * 匿名内部类，线程安全，也不会有性能问题，推荐方式 ↓↓↓ [由于构造方法私有,所以外部类无法进行初始化;通过内部类持有该实例,也不需要加锁,类未被调用时也不会初始化]
 */
public class SingletonPattern {

    private SingletonPattern(){}

    private static class SingletonHolder{
        private static final SingletonPattern singleton = new SingletonPattern();
    }

    public static SingletonPattern getSingletonPattern() {
        return SingletonHolder.singleton;
    }

    @Override
    public String toString() {
        return "我是Singleton的类";
    }

    public static void main(String[] args) {
        SingletonPattern pattern = getSingletonPattern();
        System.out.println(pattern);
    }
}

// 枚举方式，也是超简单，推荐使用 : 用的时候直接 Singleton instance = Singleton.INSTANCE;
enum Singleton{
    INSTANCE;
}

// 饿汗模式: 线程安全，缺点是即使没有调用 getSingleton()方法，实例也被初始化了
class HungrySingleton {
    private static final HungrySingleton singleton = new HungrySingleton();
    private HungrySingleton() {}

    public HungrySingleton getSingleton() {
        return singleton;
    }

}

// 懒汉模式，线程不安全: 虽然有判断，但是可能多个线程在同一时间进行判断，从而产生多个实例
class LazyNotSafeSingleton {
    private static LazyNotSafeSingleton singleton;
    private LazyNotSafeSingleton() {}

    public LazyNotSafeSingleton getSingleton() {
        if (singleton == null) {
            singleton = new LazyNotSafeSingleton();
        }
        return singleton;
    }
}

// 懒汉模式，线程安全: 通过 synchronized关键字保证线程安全，但是这样即使实例化过之后，获取实例的代码也只能有一个线程访问，性能低
class LazyAndSafeSingleton {
    private static LazyAndSafeSingleton singleton;

    private LazyAndSafeSingleton() {}

    public synchronized LazyAndSafeSingleton getSingleton() {
        if (singleton == null) {
            singleton = new LazyAndSafeSingleton();
        }
        return singleton;
    }
}

/** 双重锁检查，非线程安全: 这里的线程不安全不是来自于会同时实例化多个对象，而是由于 [new Singleton()]时虚拟机实际上有三个步骤
 *                                                                             ①栈上分配内存空间 ②堆上初始化对象 ③栈上引用指向堆内存空间地址
 *                      当 ② 和 ③被虚拟机重排的时候 [为了提升效率，多线程时 jvm会对三个步骤进行重排],会导致空指针异常
**/
class DoubleLockNotSafeSingleton {
    private static DoubleLockNotSafeSingleton singleton;
    private DoubleLockNotSafeSingleton() {}

    public static DoubleLockNotSafeSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleLockNotSafeSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleLockNotSafeSingleton();
                }
            }
        }
        return singleton;
    }
}

/**
 * 双重检查锁模式，线程安全：和不安全的上例相比，就是多了一个关键字 volatile，来避免在实例化 DoubleLockAndSafeSingleton.class的时候进行指令重排
 */
class DoubleLockAndSafeSingleton{
    private static volatile DoubleLockAndSafeSingleton singleton;
    private DoubleLockAndSafeSingleton(){}

    public static DoubleLockAndSafeSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DoubleLockAndSafeSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleLockAndSafeSingleton();
                }
            }
        }
        return singleton;
    }
}