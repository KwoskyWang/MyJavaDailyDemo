/**
 * 几种 Lambda的使用方法
 */
public class UsageOfLambda {
    static Body bod = h -> h + " No Parens!"; // [1]等于是一种匿名内部类的 Body接口的实现

    static Body bod2 = (h) -> h + " More details"; // [2]当参数只有一个时, "()"用不用都可以

    static Description desc = () -> "Short info"; // [3]当没有参数时, "()"是必须的

    static Multi mult = (h, n) -> h + n; // [4]当有多个参数时，"()"参数放在括号里, 用逗号隔开

    static Description moreLines = () -> { // [5]当存在多行代码时, 使用"{}"分隔开每行代码, 并且需要用到关键字 "return"
        System.out.println("moreLines()");
        return "from moreLines()";
    };

    public static void main(String[] args) { //看下实际的表达
        System.out.println(bod.detailed("Oh!"));
        System.out.println(bod2.detailed("Hi!"));
        System.out.println(desc.brief());
        System.out.println(mult.twoArg("Pi! ", 3.14159));
        System.out.println(moreLines.brief());
    }
}

//随便列举几种单方法的接口
interface Description {
    String brief();
}

interface Body {
    String detailed(String head);
}

interface Multi {
    String twoArg(String head, Double d);
}
