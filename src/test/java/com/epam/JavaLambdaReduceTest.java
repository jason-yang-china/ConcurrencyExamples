package com.epam;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class JavaLambdaReduceTest {

    @Test
    public void testSumNumbersList() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        int total = numbers.stream().reduce(0,(i,j)->i+j);

        Optional<Integer> total2 = numbers.stream().reduce((i, j)-> i+j);
        System.out.println(total);
        System.out.println(total2.get());
    }

    @Test
    public void testMultiplyNumbersList() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        int total = numbers.stream().reduce(1, (i,j) -> i*j);
        System.out.println(total);

        int count = numbers.stream().map(d->1).reduce(0, (i,j)-> i+j);
        System.out.println(count);
    }

    @Test
    public void testSumNumbersParallelList() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        int total = numbers.parallelStream().reduce(1, (i,j) -> i*j);
        System.out.println(total);

       Optional<Integer> min =  numbers.stream().min(Integer::compareTo);
        System.out.println(min.get());

    }
}
