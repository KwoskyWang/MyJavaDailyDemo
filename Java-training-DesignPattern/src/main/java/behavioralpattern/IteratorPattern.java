package behavioralpattern;

import java.util.ArrayList;

/**
 * 目的: 提供一种方法去顺序地访问一个聚合对象中的各个元素，同时不需要暴露该对象的内部表示
 *
 * 用法：①一个容器接口，提供一个返回迭代器的方法
 *      ②一个迭代器，里面有 hasNext()和 next()的方法
 *      ③实现容器的类，里面有存储数据的数据结构，同时有一个实现了迭代器接口的内部类，用来对数据结构进行 hasNext的判断和 next()的取值
 *      ④在客户端里初始化容器的实现，并注入数据，然后就可以通过获取容器里的迭代器来进行迭代操作
 *
 * 优缺点：可以以各种自定义的方式去遍历一个聚合对象，增加新的聚合类和迭代器都很方便；
 *       但这种模式将数据存储和便利数据的职责分离，每增加新的聚合类就需要增加新的迭代器，类的个数成对增加，增加了系统复杂度。
 */
public class IteratorPattern {
    public static void main(String[] args) {
        Container repository = new StringRepository("one","two","three");

        for (Iterator iterator = repository.getIterator(); iterator.hasNext();){
            String str = (String) iterator.next();
            System.out.printf("Now count to %s\n",str);
        }
    }
}

interface Container{
    Iterator getIterator();
}

interface Iterator{
    boolean hasNext();
    Object next();
}
// 实现了 Container接口，并且有一个实现了 Iterator接口的内部类，用来存储 支持迭代的String类型的集合
class StringRepository implements Container{
    ArrayList<String> list;
    StringRepository(String... str){
        list = new ArrayList<String>();
        for (String each:str) {
            list.add(each);
        }
    }
    public void add(String str){
        list.add(str);
    }
    public Iterator getIterator() {
        return new StringIterator();
    }

    private class StringIterator implements Iterator{

        int index;

        public boolean hasNext() {
            if (index<list.size()){
                return true;
            }
            return false;
        }

        public Object next() {
            if (this.hasNext()){
                return list.get(index++);
            }
            return null;
        }
    }
}
// 实现了 Container接口，用来存储 支持迭代的Object类型的集合
class ObjectRepository implements Container{
    ArrayList<Object> objectList;

    ObjectRepository(Object... objects){
        objectList = new ArrayList<Object>();
        for (Object each:objects) {
            objectList.add(each);
        }
    }
    public Iterator getIterator() {
        return null;
    }
}
