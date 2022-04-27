/**
 * Lambda在递归场景下的用法
 */
public class RecursionOnLambda {

    static IntCall fact;
    static IntCall fib;

    static void factorial(){ // 定义阶乘规则
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);
        for(int i = 0; i <= 10; i++)
            System.out.println(fact.call(i));
    }

    static void recursiveFibonacci(){ // 定义斐波那契数列规则
        fib = n -> n == 0 ? 0 :
                   n == 1 ? 1 :
                   fib.call(n - 1) + fib.call(n - 2);
        for(int i = 0; i <= 10; i++)
            System.out.println(fib.call(i));
    }

    public static void main(String[] args) {
        factorial(); // 阶乘演示
        recursiveFibonacci();// 斐波那契数列演示
    }
}




//随便定义一个单方法接口,但是参数类型需要正确
interface IntCall {
    int call(int arg);
}