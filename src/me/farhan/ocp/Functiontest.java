package me.farhan.ocp;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Functiontest {

    public static void test1(){
        Function<Integer,Integer> f = x -> x * x;
        Stream.of(1,3,5)
                .map(f).forEach(System.out::println);

    }
}
