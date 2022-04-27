public class MethodReferenceWithMoreCase {

    public static void main(String[] args) {
//    Callable3 callable3 = Describe2::show; // 这里会报错,因为 Describe2并不是 static
        Callable2 callable = Describe2::show; // 如果引用方法的参数里带有被引用类型,则可以引用非 static方法
        Describe2 describe2 = new Describe2();
        callable.call(describe2); // 成功引用非 static方法(非绑定方法)
        describe2.show();

        // 对于多个参数情况下的方法引用的演示,
        TwoArgs twoargs = MyMethod::two;
        ThreeArgs threeargs = MyMethod::three;
        FourArgs fourargs = MyMethod::four;
        MyMethod athis = new MyMethod();
        twoargs.call2(athis, 11, 3.14);
        threeargs.call3(athis, 11, 3.14, "Three");
        fourargs.call4(athis, 11, 3.14, "Four", 'Z');
    }

}

// 还是熟悉的例子, 因为是在默认包下, 为了避免重名, 加上数字
interface Callable2{
    void call(Describe2 describe);
}

interface Callable3{
    void call();
}

// 用来存放需要被引用的方法,如何不创建实例而使用此类的方法引用?
class Describe2{
    void show(){
        System.out.println("Say something.");
    }
}

// 在没有对应实例的情况下,如果要引用这里的方法,就需要在接口参数里带上该类
class MyMethod {
    void two(int i, double d) { System.out.println(i+d);}
    void three(int i, double d, String s) { System.out.println(i+d+s);}
    void four(int i, double d, String s, char c) { System.out.println(i+d+s+c);}
}

interface TwoArgs {
    void call2(MyMethod athis, int i, double d);
}

interface ThreeArgs {
    void call3(MyMethod athis, int i, double d, String s);
}

interface FourArgs {
    void call4(MyMethod athis, int i, double d, String s, char c);
}