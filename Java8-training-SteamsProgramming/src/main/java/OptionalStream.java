import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Optional类提供对流的
 * 1. 空值检查
 * 2. 包装流
 * <p>
 * 同时也存在便利函数可以解包 Optional
 */
public class OptionalStream {

    public static void main(String[] args) {
        // 1. 如果我们的流中存在null, 或者我们的流为null, 如何让流不中断？
        // 下面演示对 Optional类的几个方法的演示: findFirst(), findAny(), max(), min()
        optionalShow();
        isEmptyOptional(Stream.<String>empty().findAny());
        // 2. 便利函数演示
        // ifPresent(Consumer)：当值存在时调用 Consumer，否则什么也不做。
        // orElse(otherObject)：如果值存在则直接返回，否则生成 otherObject。
        // orElseGet(Supplier)：如果值存在则直接返回，否则使用 Supplier 函数生成一个可替代对象。
        // orElseThrow(Supplier)：如果值存在直接返回，否则使用 Supplier 函数生成一个异常。
        test("ifPresent", OptionalStream::ifPresent);
        test("orElse", OptionalStream::orElse);
        test("orElseGet", OptionalStream::orElseGet);
        test("orElseThrow", OptionalStream::orElseThrow);
        // 3.创建 Optional
        // empty(): 生成一个空 Optional
        // of(value): 将一个非空值包装到 Optional 里
        // ofNullable(value): 针对一个可能为空的值, 为空时自动生成 Optional.empty, 否则将值包装在 Optional 中
        optionalCreationTest("empty", Optional.empty());
        optionalCreationTest("of", Optional.of("Howdy"));
        try {
            optionalCreationTest("of", Optional.of(null));
        } catch (Exception e) {
            System.out.println(e);
        }
        optionalCreationTest("ofNullable", Optional.ofNullable("Hi"));
        optionalCreationTest("ofNullable", Optional.ofNullable(null));

    }


    static void optionalShow() {
        System.out.println(Stream.of("1", "2", null, "2")
                .findFirst());
        System.out.println(Stream.of("3", "2", null, null)
                .findAny().get());  // 通过 get()可以获取 optional里的数据
        System.out.println(Stream.of("1", "2", "3")
                .max(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty()
                .min(String.CASE_INSENSITIVE_ORDER));
        System.out.println(Stream.<String>empty()
                .reduce((s1, s2) -> s1 + s2));
        System.out.println(IntStream.empty()
                .average());
    }

    static void isEmptyOptional(Optional<String> optString) {
        if (optString.isPresent()) {
            System.out.println(optString.get());
        } else
            System.out.println("Nothing there.");
    }

    /**
     * 便利函数演示 函数支持
     *
     * @param testName
     * @param cos      Consumer接口函数,可以先定义 consumer函数,然后调用 accept(T)时会自动执行这个函数,或者直接 accept(Lambda).
     */
    static void test(String testName, Consumer<Optional<String>> cos) {
        System.out.println(" === " + testName + " === ");
        cos.accept(Stream.of("Print the content of Optional").findFirst());
        cos.accept(Stream.<String>empty().findFirst());
    }

    static void ifPresent(Optional<String> optString) {
        optString.ifPresent(System.out::println);
    }

    static void orElse(Optional<String> optString) {
        System.out.println(optString.orElse("Nada"));
    }

    static void orElseGet(Optional<String> optString) {
        System.out.println(
                optString.orElseGet(() -> "Generated"));
    }

    static void orElseThrow(Optional<String> optString) {
        try {
            System.out.println(optString.orElseThrow(
//                    () -> new Exception("Supplied"))); // 这里的报错类型是自定义的
                    () -> new NullPointerException("Supplied")));
        } catch (Exception e) {
            System.out.println("Caught " + e);
        }
    }

    /**
     * 测试创建 Optional
     *
     * @param testName
     * @param opt
     */
    static void optionalCreationTest(String testName, Optional<String> opt) {
        System.out.println(" === " + testName + " === ");
        System.out.println(opt.orElse("Null"));
    }

}

