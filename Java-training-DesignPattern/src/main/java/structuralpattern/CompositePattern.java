package structuralpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 *
 * 使用场景：树形结构的问题中，如员工结构，树形菜单，文件/文件夹的管理
 *
 * 具体元素：
 *      ①. 实际使用的类，如在员工结构中，所有层级的员工都由同一个员工类来实例化，拥有一个列表属性，用来把下级实例加入到上级实例中。
 *      ②. 客户端调用类，员工的初始化和层级设置在这里完成[层级设置通过把下级元素加入到上级元素的列表里来实现]
 *
 * 优缺点：高层模块调用简单，按层级便利也方便，节点增加自由
 */
public class CompositePattern {
    public static void main(String[] args) {
        Employee CEO = new Employee("John","CEO", 30000);

        Employee headSales = new Employee("Robert","Head Sales", 20000);

        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //打印该组织的所有员工
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}

class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    //构造函数
    public Employee(String name,String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("Employee :[ Name : "+ name
                +", dept : "+ dept + ", salary :"
                + salary+" ]");
    }
}