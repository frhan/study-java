package me.farhan.ocp;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

public class MapTest {

    public static void test(){
        NavigableMap<String,String> mmap = new TreeMap<>();
        mmap.put("a","apple");
        mmap.put("b","boy");
        mmap.put("c","cat");
        mmap.put("aa","apple1");
        mmap.put("bb","boy1");
        mmap.put("cc","cat1");

        NavigableMap<String,String> mmap2 = mmap.tailMap("bb",false);
        System.out.println(mmap2.pollFirstEntry());
        System.out.println(mmap.size());


    }

    public static void test2(){

        TreeSet<Integer> treeSet = new TreeSet<>();
        TreeSet<Integer> subset = new TreeSet<>();

        for (int i = 324; i<=328; i++){
            treeSet.add(i);
        }

        subset = (TreeSet) treeSet.subSet(326,true,328,true);

        subset.add(325);

        System.out.println(subset);
    }

    public static void test3(){
        Set<Person> people = new TreeSet<>();
        people.add(new Person("Farhan"));
        people.add(new Person("abdullah"));

        System.out.println(people);
    }

    public static void test4(){

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new CopyOnWriteArrayList<>();
        Set<Integer> l3 = new ConcurrentSkipListSet<>();
        l3.addAll(l1);
    }
}
