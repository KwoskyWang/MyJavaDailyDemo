import jdk.internal.org.objectweb.asm.tree.FrameNode;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ①. 传统写法里的while属于外部迭代(external iteration), 流式写法里的迭代无法看到,属于内部迭代(internal iteration)
 * ②. 内部迭代的可读性更强, 且把控制权交给并行化机制, 可以更简单地使用多核处理器
 */
public class RandomsCase {
    // 流式写法,简单便捷
    public static void main(String[] args) {
        new Random(47)
                .ints(5,20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);

        traditionWay();

    }
    // 传统写法,变量多,阅读不流畅
    public static void traditionWay(){
        Random random = new Random(47);
        SortedSet<Integer> set = new TreeSet<>();
        while (set.size()<7){
            int i = random.nextInt(20);
            if (i<5) continue;
            set.add(i);
        }
        System.out.println(set);
    }

}
