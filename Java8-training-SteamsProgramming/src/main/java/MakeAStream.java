import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流操作的类型有三种：创建流，修改流元素（中间操作， Intermediate Operations），消费流元素（终端操作， Terminal Operations）
 * 先来看看: 创建流
 */
public class MakeAStream {
    public static void main(String[] args) throws IOException {
        RandomElementStream();
        CollectionToStream();
        show();
        // Stream.generate() 的用法: 它可以把任意 Supplier<T> 用于生成 T 类型的流
        System.out.println(
                Stream.generate(new RandomWords())
                .limit(5)
                .collect(Collectors.joining("-")));
        // 这里的结果是: four-three-four-two-two; .是因为 RandomWords方法里的 Random.nextInt()方法
    }

    // 组合不同元素成为流
    static void RandomElementStream(){
        Stream.of("It's ", "a ", "wonderful ", "day ", "for ", "pie!")
                .forEach(System.out::print);
        System.out.println();
        Stream.of(3.14159, 2.718, 1.618)
                .forEach(System.out::println);
        Stream.of("It's ", "π ",3.14159)
                .forEach(System.out::print);
    }

    // 集合类转化为流
    static void CollectionToStream(){
        Set<String> w = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        w.stream()
                .map(x -> x + " ")
                .forEach(System.out::print);
        System.out.println();

        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.14159);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream()  // set有.stream()方法, map没有, 这一步是把Map转换成 Set<Map.Entry<K, V>>集合先
                .map(e -> e.getKey() + ": " + e.getValue())
                .forEach(System.out::println);
    }

    // 对生成的流进行增强
    public static <T> void StrengthenStream(Stream<T> stream) {
        stream
                .limit(2)
                .forEach(System.out::println);
        System.out.println("---------");
    }
    // 演示增强流用法
    public static void show(){
        Random rand = new Random(47);
        StrengthenStream(rand.ints().boxed()); // boxed() 流操作会自动地把基本类型包装成为对应的装箱类型
        StrengthenStream(rand.longs().boxed());
        StrengthenStream(rand.doubles().boxed());
        // 控制上限和下限：
        StrengthenStream(rand.ints(10, 20).boxed());
        StrengthenStream(rand.longs(50, 100).boxed());
        StrengthenStream(rand.doubles(20, 30).boxed());
        // 控制流大小：
        StrengthenStream(rand.ints(2).boxed());
        StrengthenStream(rand.longs(2).boxed());
        StrengthenStream(rand.doubles(2).boxed());
        // 控制流的大小和界限
        StrengthenStream(rand.ints(3, 3, 9).boxed());
        StrengthenStream(rand.longs(3, 12, 22).boxed());
        StrengthenStream(rand.doubles(3, 11.5, 12.3).boxed());
    }

    /**
     * 把类转化成流
     * collect() 根据参数来结合所有的流元素
     * Collectors.joining()作为 collect() 的参数时, 将得到一个String 类型的结果, 并被joining()的参数隔开
     * Collectors.还有其他许多用法, 用以产生不同结果
      */
    static class RandomWords implements Supplier<String> {
        List<String> words = new ArrayList<>();
        Random rand = new Random(47);

        RandomWords() throws IOException {
            List<String> lines = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five"));
            // 略过第一行
            for (String line : lines.subList(1, lines.size())) {
                for (String word : line.split("[ .?,]+"))
                    words.add(word.toLowerCase());
            }
            System.out.println(words);
        }

        @Override
        public String get() {
            return words.get(rand.nextInt(words.size()));
        }
    }
}
