import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class OptionalOperation {

    public static void main(String[] args) {
        // 1.操作 Optional对象
        // map(Function): 如果 Optional 不为空，应用 Function 于 Optional 中的内容, 并返回结果. 否则直接返回 Optional.empty
        // flatMap(Function): 同 map(), 但是提供的映射函数将结果包装在 Optional 对象中，因此 flatMap() 不会在最后进行任何包装
        // * filter(Predicate): 对 Optional 中的内容应用Predicate 并将结果返回.
        //      - 如果 Optional 不满足 Predicate, 将 Optional 转化为空 Optional. 如果 Optional 已经为空, 则直接返回空Optional
        optionalOperation("true", str -> true);
        optionalOperation("false", str -> false);
        optionalOperation("str != \"\"", str -> str != "");
        optionalOperation("str.length() == 3", str -> str.length() == 3);
        optionalOperation("startsWith(\"B\")",
                str -> str.startsWith("B"));
        // 2.通过 map()操作 optional对象
        // 同 map() 一样, Optional.map() 执行一个函数. 它仅在 Optional 不为空时才执行这个映射函数.
        // 并将 Optional 的内容提取出来, 传递给映射函数
        optionalOnMap("Add brackets", s -> "[" + s + "]");
        optionalOnMap("Increment", s -> {
            try {
                return Integer.parseInt(s) + 1 + "";
            } catch (NumberFormatException e) {
                return s;
            }
        });
        optionalOnMap("Replace", s -> s.replace("2", "9"));
        optionalOnMap("Take last digit", s -> s.length() > 0 ?
                s.charAt(s.length() - 1) + "" : s);
        // 3.通过 flatMap() 操作 Optional对象
        optionalOnFlatMap("Add brackets",
                s -> Optional.of("[" + s + "]"));
        optionalOnFlatMap("Increment", s -> {
            try {
                return Optional.of(
                        Integer.parseInt(s) + 1 + "");
            } catch (NumberFormatException e) {
                return Optional.of(s);
            }
        });
        optionalOnFlatMap("Replace",
                s -> Optional.of(s.replace("2", "9")));
        optionalOnFlatMap("Take last digit",
                s -> Optional.of(s.length() > 0 ?
                        s.charAt(s.length() - 1) + ""
                        : s));
        // 4.Optional流与它的解包
        Signal.stream()
                .limit(10)
                .forEach(System.out::println);
        System.out.println(" ---");
        Signal.stream()
                .limit(10)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);
    }


    /**
     * Optional 对象操作的函数支持
     *
     * @param descr 模块标题
     * @param pred  断言规则
     */
    static void optionalOperation(String descr, Predicate<String> pred) {
        String[] elements = {
                "Foo", "", "Bar", "Baz", "Bingo"
        };
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    Arrays.stream(elements)
                            .skip(i) // 关键步骤,因为每次 for循环都会重新启动流,如果不加 skip(), 那么次 findFirst()都会拿到第一个元素 "Foo"
                            .findFirst()
                            .filter(pred));
        }
    }

    /**
     * 通过 map()作用于 Optional
     *
     * @param descr 模块名称
     * @param func  map()所使用的函数
     */
    static void optionalOnMap(String descr, Function<String, String> func) {
        String[] elements = {
                "12", "", "23", "45", ""
        };
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    Arrays.stream(elements)
                            .skip(i)
                            .findFirst() // Produces an Optional
                            .map(func));
        }
    }

    /**
     * 通过 flatMap()作用于 Optional
     *
     * @param descr 模块名称
     * @param func  flatMap()所使用的函数
     *              同 map(), flatMap() 将提取非空 Optional 的内容并将其应用在映射函数.
     *              唯一的区别就是 flatMap() 不会把结果包装在 Optional 中, 因为映射函数已经被包装过了
     */
    static void optionalOnFlatMap(String descr, Function<String, Optional<String>> func) {
        String[] elements = {
                "12", "", "23", "45", ""
        };
        System.out.println(" ---( " + descr + " )---");
        for (int i = 0; i <= elements.length; i++) {
            System.out.println(
                    Arrays.stream(elements)
                            .skip(i)
                            .findFirst() // Produces an Optional
                            .flatMap(func));
        }
    }
}


/**
 * Signal用来生成随机数,如果随机数 =1 or 2,则分别返回不同 Signal对象
 * stream()用来返回被 Optional包装的<Signal>的流
 */
class Signal {
    private final String msg;

    public Signal(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Signal(" + msg + ")";
    }

    static Random rand = new Random(47);

    public static Signal morse() {
        switch (rand.nextInt(4)) {
            case 1:
                return new Signal("dot");
            case 2:
                return new Signal("dash");
            default:
                return null;
        }
    }

    public static Stream<Optional<Signal>> stream() {
        return Stream.generate(Signal::morse)
                .map(signal -> Optional.ofNullable(signal));
    }
}
