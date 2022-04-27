/**
 * 关于方法引用的几个注意点
 * 1.在做方法引用时,通过接口方法引用其他类的方法,这两个方法的签名需要一致:参数和返回值需要一致
 * 2.在通过 类名:方法名 来做方法引用时, 如果该类是外部类, 需要新建对象, 通过 对象::方法 进行引用
 * 3.在通过 类名:方法名 来做方法引用时, 如果该类是内部类, 可以通过
 *      1⃣ 创建内部类的对象,通过 对象::方法 来引用, (内部类是需要关键字 static的);
 *      2⃣ 内部类和其需要被引用的方法标注关键字 static, 通过 类名::方法名 来引用;
 */
public class MethodReferences {
    static void hello(String msg){
        System.out.println("Hello, " + msg);
    }
    static class Description{ // 这是带有自定义构造方法的一个内部类
        String about;
        Description(String msg){
            about = msg;
        }
        void help(String msg){
            System.out.println(about + " " + msg);
        }
    }

    /**
     * 这是一个内部类
     * 如果想要直接引用方法, 就需要给方法加上 static关键字;
     * 如果这个方法在内部类里, 那么内部类也需要加上关键字static
     */
    static class Helper{
        static void assist(String msg){
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Describe d = new Describe();
        Callable c = d::show;
        c.call("Inject the method from Describe.show() to Callable.call()");

        c = MethodReferences::hello;
        c.call("Inject the method from MethodReferences.hello() to Callable.call()");

        Description description = new Description("Inner Class");
        c = description::help;
        c.call("Inject the method from inner class description.help() to Callable.call()");

        c = Helper::assist;
        c.call("Inject the method from inner class Helper.assist() to Callable.call()");
    }

}

// 随便一个单方法接口,但是要注意方法的参数类型
interface Callable{
    void call(String msg);
}

// 用来存放需要被引用的方法
class Describe{
    void show(String msg){
        System.out.println(msg);
    }
}