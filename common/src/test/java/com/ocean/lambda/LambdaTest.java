package com.ocean.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class LambdaTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRunnable() {
        //使用Lambda表达式之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO 业务实现
                System.out.println("Before Lambda");
            }
        }).start();

        //使用Lambda表达式之后
        new Thread(() -> {
            //TODO 业务实现
            System.out.println("After Lambda");
        }).start();
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(String.valueOf(i));
        }

        long startTime = System.currentTimeMillis();
        for (String str : list) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("普通for循环耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        list.forEach(item -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("单管道stream耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        startTime = System.currentTimeMillis();
        list.parallelStream().forEach(item -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("多管道stream耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Test
    public void test_method_reference() {

        //使用实例方法引用
        Stream.of(
                new Student(22, "欧阳"),
                new Student(25, "东方")
        ).map(Student::getName).forEach(System.out::println);

    }

    @Test
    public void test_method_reference2() {
        //使用构造函数引用
        Stream.of("欧阳", "东方").map(Student::new)
                .forEach(System.out::println);
        Stream.of(22, 23).map(Student::new)
                .forEach(System.out::println);
    }

    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList(4, 5, 6, 6, 1, 2, 3, 7, 8, 8, 9, 10);

        //过滤出偶数列表
        List<Integer> evens = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());

        //对列表进行排序
        List<Integer> sortIntegers = list.stream().sorted().collect(Collectors.toList());

        //转成平方列表
        List<Integer> squareList = list.stream().map(i -> i * i).collect(Collectors.toList());

        //对列表求和
        int sum = list.stream().mapToInt(Integer::intValue).sum();

        //转成其它数据结构比如set
        Set<Integer> integersSet = list.stream().collect(Collectors.toSet());

        //根据奇偶性分组
        Map<Boolean, List<Integer>> listMap = list.stream().collect(Collectors.groupingBy(i -> i % 2 == 0));

    }

    @Test
    public void testRunnable1() {
        //使用Lambda表达式之前
        int number = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
                System.out.println(this);
            }
        }).start();

        //使用Lambda表达式之后
        new Thread(() -> {
            System.out.println(number);
            System.out.println(this);
        }).start();
    }

    class Student {
        private Integer age;
        private String name;

        public Student() {
        }

        public Student(Integer age) {
            this.age = age;
        }

        public Student(String name) {
            this.name = name;
        }

        public Student(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
