import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 引申内容:  java.util.regex.Pattern 中增加了一个新的方法 splitAsStream()
 *          这个方法可以根据传入的公式将字符序列转化为流,但是有一个限制,输入只能是 CharSequence
 *          我们可以把数据存储在一个 CharSequence中, 然后多次使用 stream生成流
 *          但是失去了流的两个重要优势: ①.不需要存储流 ②.懒加载计算
 *
 * 流的建造者模式(Builder design pattern)
 * 1. 创建一个 builder对象
 * 2. 将创建流的信息传递给它
 * 3. builder对象执行'创建'流的操作
 */
public class BuilderDesignOfStream {
    public static void main(String[] args) throws Exception {
        new FileToWordBuilder("/Users/moooke/IdeaProjects/MyJavaDailyDemo/Java8-training-SteamsProgramming/src/main/resources/example.txt")
                .putToBuilder("这是我加的")// 动态增加元素,这里可以是任何返回 String类型的方法, 甚至可以编辑成一个动态地数据插入器
                .stream()
                .limit(10)
                .map(x -> x+" ")
                .forEach(System.out::print);

        //
    }
}

// 演示: 流的建造者模式
class FileToWordBuilder{
    Stream.Builder<String> builder = Stream.builder(); // 1. 创建一个 builder对象

    FileToWordBuilder putToBuilder(String msg){ // 如果我想在流创建完成创建之前再动态地增加点内容，那么可以用这种方式
        builder.add(msg);
        return this;
    }

    FileToWordBuilder(String file) throws Exception{
        Files.lines(Paths.get(file))
                .forEach(line -> {
                    for (String t:line.split("[,?.。' ']+"))
                        builder.add(t); // 2. 将创建流的信息传递给它
                });
    }

    Stream<String> stream(){
        return builder.build(); // 3. builder对象执行'创建'流的操作
    }
}

// Arrays类的 .stream()展示
class Machine2 {
    public static void main(String[] args) {
        Arrays.stream(new double[]{3.14159, 2.718, 1.618})
                .forEach(n -> System.out.format("This is the show of stream:%f %f %n", n,n));

        Arrays.stream(new int[] { 1, 3, 5 })
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        Arrays.stream(new long[] { 11, 22, 44, 66 })
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();

        // 选择一个子域:
        Arrays.stream(new int[] { 1, 3, 5, 7, 15, 28, 37 }, 2, 6)
                .forEach(n -> System.out.format("%d ", n));
    }
}
