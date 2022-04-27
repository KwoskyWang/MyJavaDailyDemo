import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

/**
 * Stream.generate() 搭配 Supplier<T> 是很方便的生成流的工具
 * 下面将会列举
 * 1. 传统的 range循环和基于流式写法的差异
 * 2. generate() 的使用
 * 3. iterate() 基于流式写法的使用
 */
public class GenerateOfStream {

    static void strengthenStream(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        // 1. 传统的 range循环和基于流式写法的差异
        rangeInStreamWay();
        repeat(3, () -> System.out.println("Looping!"));
        repeat(2, GenerateOfStream::hi);

        // 2. generate() 的使用
        String word = Stream.generate(new Generator())
                .limit(30)
                .collect(Collectors.joining("-"));
        System.out.println(word);
        // 如果要生成相同的对象, 只需要传入一个生成该对象的 Lambda; 上面的字母序列其实也是 Generator.get()运行30次的结果.
        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);
        // 使用自己创建的类,并创建和 Supplier<Object>而不是声明 Supplier<T>来创造流
        Stream.generate(Bubble::bubbler)
                .limit(5)
                .forEach(System.out::println);

        // 3. iterate() 基于流式写法的使用
        new Fibonacci().fibonacci().skip(1).limit(10).forEach(System.out::println);

    }

    // intRange不同写法的对比
    static void rangeInStreamWay(){
        // 传统方法:
        int result = 0;
        for (int i = 10; i < 20; i++)
            result += i;
        System.out.println(result);
        // for-in 循环:
        result = 0;
        for (int i : range(10, 20).toArray())
            result += i;
        System.out.println(result);
        // 使用流:
        System.out.println(range(10, 20).sum());
    }

    // 给基于 range()的 Int列表加入增强功能
    static void repeat(int n, Runnable action) {
        range(0, n).forEach(i -> action.run());
    }
    static void hi() {
        System.out.println("Hi!");
    }


}

class Generator implements Supplier<String> {
    Random rand = new Random(47);
    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public String get() {
        return "" + letters[rand.nextInt(letters.length)];
    }
}

// 这个类有一个很重要的特点, 就是它的 Bubble.bubbler是和 Supplier<Bubble>接口兼容的, 因为它们都生产目标对象(Bubble).
class Bubble {
    public final int i;

    public Bubble(int n) {
        i = n;
    }

    @Override
    public String toString() {
        return "Bubble(" + i + ")";
    }

    private static int count = 0;

    public static Bubble bubbler() {
        return new Bubble(count++);
    }
}

// Stream.iterate()产生的流的第一个元素可以认为是一个种子，第二个参数则是一个方法，
//                  由第一个参数带入第二个参数所产生的结果又是下次迭代的第一个参数
class Fibonacci{
    int x = 1;

    Stream<Integer> fibonacci(){
        return Stream.iterate(0,i ->{ // 把第二个参数单独拿出来，然后再把第一个参数[0]带进去
            i = x + i;
            x = i;
            return i;
                });
    }
}