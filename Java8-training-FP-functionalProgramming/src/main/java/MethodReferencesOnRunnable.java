/**
 * 方法引用用作 lambda的写法不需要加上 '()->', 但是方法签名需要一致(参数与返回值)
 */
public class MethodReferencesOnRunnable {

    public static void main(String[] args) {
        // 传统写法,匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Normal way to start Runnable.run()");
            }
        }).start();
        // Lambda 写法
        new Thread(()-> System.out.println("this is Runnable.run() on lambda")).start();
        // Lambda + 方法引用
        new Thread(Go::go).start();
    }
}

// 示例: 需要注入 Runnable()接口的方法
class Go{
    static void go(){
        System.out.println("Go:go()");
    }
}