import java.util.function.Function;

/**
 * 1⃣ .这里看起来可能会有些吃力,这里的 produce()就是高阶函数, 高阶函数是产生或者消费函数的函数
 * 2⃣ .这里的 produce() 就是用来产生函数的函数
 * 3⃣ .
 * 4⃣ .
 */
public class HigherOrderFunction {
    static FuncSS produce(){
        return s -> s.toLowerCase();
    }

    static FuncSS2 produce2(){
        return s -> s.toString()+", from Integer.";
    }

    public static void main(String[] args) {
        FuncSS ss = produce();
        System.out.println(ss.apply("THIS IS A UPPER SENTENCE."));
        FuncSS2 ss2 = produce2();
        System.out.println(ss2.apply(100));
    }
}

// 可以把第一个参数看为参数,而第二个参数为返回值类型,当然,实际引用还要借用 Lambda和 Function的 apply()方法.
interface FuncSS extends Function<String,String>{}
interface FuncSS2 extends Function<Integer,String>{}