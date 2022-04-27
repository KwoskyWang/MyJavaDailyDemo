import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 中间操作用于从一个流中获取对象, 并将对象作为另一个流从后端输出, 以连接到其他操作
 * <p>
 * map()方法在做 流加强 的过程中起到关键作用
 * mapToInt(Function): 操作同下,返回 IntStream
 * mapToLong(ToLongFunction): 操作同下,返回 LongStream
 * mapToDouble(ToDoubleFunction): 操作同下,但结果是 DoubleStream
 * <p>
 * map(Function) 将函数操作应用到流的输入元素中, 并将返回值传递到输出流中
 * <p>
 * 另外 -> 借用 [map+方法引用], 我们还可以接收和产生不同的类型
 */
public class IntermediateOperationsOfStream {
    public static void main(String[] args) throws Exception {
        // 1.流中间操作演示
        Set<String> set = new TreeSet<>(Arrays.asList("one", "one", "two", "three", "four", "five"));
        set.stream()
                .skip(1)
                .map(x -> "-" + x)
                .peek(System.out::println)  // 中间操作
                .map(x -> x + "-")
                .peek(System.out::println)  // 中间操作
                .map(x -> x.toUpperCase())
                .sorted(Comparator.reverseOrder())  // 反向排序
                .peek(System.out::println)
                .sorted()  // 排序, sorted()也可以传入 Lambda函数
                .distinct() // 去重, 当然, 这里使用的是 set,所以没有重复,是个伪需求
                .filter(IntermediateOperationsOfStream::isNotOne) // 过滤掉我们设置的规则的数据
                .forEach(System.out::println);

        // 2.增强流使用演示
        strengthenStream("增加字符", n -> "[" + n + "]");
        strengthenStream("字符替换", n -> n.replace('2', '9'));
        strengthenStream("转int再加1", n -> {
            try {
                return Integer.parseInt(n) + 1 + "";
            } catch (NumberFormatException e) {
                return n;       // 这里的写法可以注意,是通过 Integer.parseInt(n)尝试转换成 int类型,果失败就返回原字符串
            }
        });
        strengthenStream("截取字符", n -> n.length() > 0 ? n.charAt(n.length() - 1) + "" : n + "0");

        // 3.流输入输出不一致类型演示
        Stream.of(1, 2, 3, 4, 5, 6)
                .map(IntermediateOperationsOfStream::new)
                .forEach(System.out::println);

        // 4. 不同数据类型装换对应不同的map
        Stream.of("1", "2", "3")
                .mapToInt(Integer::parseInt)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        Stream.of("17", "19", "23")
                .mapToLong(Long::parseLong)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        Stream.of("17", "1.9", ".23")
                .mapToDouble(Double::parseDouble)
                .forEach(n -> System.out.format("%f ", n));

        // 5.演示 flatMap()的用法
        System.out.println("\n直接生成流的流:");
        doubleStream();
        System.out.println("使用flatMap来用生成流的方法处理流:");
        flatStream();
        // 通过 flat()方法的特定场景演示
        System.out.println("\n使用Stream.concat()方法组合流:");
        flatRandNumberGenerator();

        // 6.演示 flatMap()处理字符的用法
        System.out.println("\n这里演示: flatMap()处理字符的用法");
        String filePath = "/Users/moooke/IdeaProjects/MyJavaDailyDemo/Java8-training-SteamsProgramming/src/main/resources/example.txt";
        fileStream(filePath)
                .limit(7)
                .forEach(s -> System.out.format("%s ", s));
        System.out.println();
        fileStream(filePath)
                .skip(2)
                .limit(2)
                .forEach(s -> System.out.format("%s ", s));
        System.out.println();
    }

    // 过滤函数
    static boolean isNotOne(String msg) {
        return !msg.equals("-ONE-");
    }

    // 针对 流增强的演示
    static void strengthen(String str, Function<String, String> func) {
        // 如果我把 return Arrays.stream() 写在这里,那么我需要返回 Stream<String>,
        //                        这样我在其他地方调用的时候就需要把返回作为一个流来处理,就没办法用到 Function<>的增强
    }

    static Stream<String> testStream() {
        return Arrays.stream(new String[]{"12", "", "23", "45"});
    }

    // 实际增强写法, 上面的 testStream用来提供数据, 也可以把数组拿出来, 需要的时候做动态修改
    static void strengthenStream(String str, Function<String, String> func) {
        System.out.println("---<" + str + ">---");
        testStream()
                .map(func)
                .forEach(System.out::println);
    }

    // 这里用来演示通过 map+方法引用 使得流传入和传出不同数据类型
    final int i;

    IntermediateOperationsOfStream(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "已经把int转为String: " + i;
    }

    // 当把流转换成流
    static void doubleStream() {
        Stream.of(1, 2, 3)
                .map(i -> Stream.of("Gonzo", "Kermit", "Beaker"))
//                .map(e-> e.getClass().getName())
                .forEach(System.out::println);
    }

    // 使用 flatMap()来用生成流的方法处理流
    static void flatStream() {
        Stream.of(1, 2, 3)
                .flatMap(i -> Stream.of("Gonzo ", "Fozzie ", "Beaker "))
                .forEach(System.out::print);
    }

    static void flatRandNumberGenerator() {
        Random rand = new Random();
        Stream.of(1, 2, 3, 4, 5)
                .flatMapToInt(
                        i -> IntStream.concat(    // Stream.concat()方法可以把两个流组合起来
                                rand.ints(0, 100)
                                        .limit(i), IntStream.of(-1)))
                .forEach(n -> System.out.format("%d ", n));
    }

    static Stream<String> fileStream(String filePath) throws Exception {
        return Files.lines(Paths.get(filePath))
//                .skip(1) // First (comment) line
                .flatMap(line ->
                        Pattern.compile(" ").splitAsStream(line));
    }

}
