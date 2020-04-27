package me.farhan.ocp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class StreamTest {

    public static void test(){

        Collection<Integer> c = new ArrayList<>();
        c.parallelStream();

        Stream<Integer> stream = c.stream().parallel();

    }

    public static void test2(){

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        Runnable runnable = () -> System.out.println("DODODO");

        scheduledExecutorService.scheduleAtFixedRate(runnable,1,1, TimeUnit.SECONDS);

        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
                .stream()
                .reduce("",(c,s1) -> c + s1,
                        (s2,s3) -> s2 + s3));
    }
}
