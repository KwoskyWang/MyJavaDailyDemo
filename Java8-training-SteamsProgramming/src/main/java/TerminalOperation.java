import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOperation {

    private static int[] rints = new Random(47).ints(0, 100).limit(10).toArray();
    static final int SZ = 14;

    // rints通过 toArray()产生一个固定的数组, 每次调用 rands()可以使用同一个基础数据创建流
    public static IntStream rands() {
        return Arrays.stream(rints);
    }

    /**
     * @forEach(Consumer): 常见如 System.out::println 作为 Consumer 函数
     * @forEachOrdered(Consumer): 保证 forEach 按照原始流顺序操作
     * @parallel() 用来实现多处理器并行操作,可将流分割成多个(通常与CPU数量相同)并在处理器上分别操作
     *
     * @collect(Collector): 使用 Collector 收集流元素到结果集合中
     * @collect(Supplier, BiConsumer, BiConsumer):
     *  第一个参数 Supplier 创建了一个新的结果集合, 第二个参数 BiConsumer 将下一个元素收集到结果集合中, 第三个参数 BiConsumer 用于将两个结果集合合并起来
     */
    public static void main(String[] args) throws IOException {
        rands().limit(SZ)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        rands().limit(SZ)
                .parallel()
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        rands().limit(SZ)
                .parallel()
                .forEachOrdered(n -> System.out.format("%d ", n));
        System.out.println();

        /**
         * @flatMap(s -> Arrays.stream(s.split("\\W+")))
         * - 如果使用 map(), 那么得到的是一个流, 流无法使用 s.matches()等方法
         * - flatMap(), 对单行来说是使其变为一串字符, 对整个文本来说,是把【各行形成的多个单词流】映射成【一个单词流】
         *
         * @collect(Collectors.toCollection(TreeSet::new))
         * - 为了保持元素有序, 把元素存在 TreeSet里, 但是 Collectors 里面没有特定的 toTreeSet()
         * - 所以可以通过将集合的构造函数引用传递给 Collectors.toCollection(), 从而构建集合【任意类型的集合】
         */
        Set<String> words2 =
                Files.lines(Paths.get("/Users/moooke/IdeaProjects/MyJavaDailyDemo/Java8-training-SteamsProgramming/src/main/resources/example.txt"))
                        .flatMap(s -> Arrays.stream(s.split("\\W+")))
                        .filter(s -> !s.matches("\\d+")) // No numbers
                        .map(String::trim)
                        .filter(s -> s.length() > 2)
                        .limit(30)
                        .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(words2);

        /**
         * 1.提供支持 Map里 key,value元素的数据结构
         * 2.提供返回这个数据结构的流
         * 3.通过 Collectors.toMap(getKey,getValue)来得到 Map
         */
        Map<Integer, Character> map =
                new RandomPair().stream()
                        .limit(8)
                        .collect(
                                Collectors.toMap(Pair::getI, Pair::getC));
        System.out.println(map);

        /**
         * collect() 还可以通过形如 stream().collect(ArrayList::new,
         *                                        ArrayList::add,
         *                                        ArrayList::addAll); 的方式来使用
         */

    }

}

/**
 * 在流中生成 Map的数据结构支持
 * Pair: 用来提供方法引用,一个生成 Character,一个生成 Integer
 */
class Pair {
    public final Character c;
    public final Integer i;
    Pair(Character c, Integer i) {
        this.c = c;
        this.i = i;
    }
    public Character getC() { return c; }
    public Integer getI() { return i; }
    @Override
    public String toString() {
        return "Pair(" + c + ", " + i + ")";
    }
}

/**
 * 在流中生成 Map的数据结构支持
 * RandomPair: 用来提供方法引用,一个生成 Character,一个生成 Integer
 * capChars: 用来生成随机 Character,同时利用 Iterator的 next()方法使得其可以在 流中使用
 * stream: 用来生成一个<Pair>类型的流
 */
class RandomPair {
    Random rand = new Random(47);
    // An infinite iterator of random capital letters:
    Iterator<Character> capChars = rand.ints(65,91)
            .mapToObj(i -> (char)i)
            .iterator();
    public Stream<Pair> stream() {
        return rand.ints(100, 1000).distinct()
                .mapToObj(i -> new Pair(capChars.next(), i));
    }
}