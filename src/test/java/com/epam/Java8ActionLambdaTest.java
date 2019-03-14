package com.epam;

import com.epam.completablefuture.BestPriceFinder;
import com.epam.completablefuture.Shop;
import com.epam.concurrent.forkjoin.ForkJoinSumCalculator;
import com.epam.concurrent.threadlocal.ThreadLocalUtility;
import com.epam.demo.Dish;
import org.junit.Test;

import java.util.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Java8ActionLambdaTest {
    @Test
    public void java8LambdaStreamTest() {
        Dish.menu.stream().filter(d->d.getCalories()< 400).sorted(comparing(a->a.getCalories())).map(Dish::getName).collect(toList()).forEach(System.out::println);
    }

    @Test
    public void java8LambdaStreamListTest() {
        List<String> list = Dish.menu.stream().filter(d->d.getCalories()< 400).sorted(comparing(a->a.getCalories())).map(Dish::getName).collect(toList());
        Stream<Dish> stream = Dish.menu.stream();
        stream.forEach(System.out::println);

    }

    @Test
    public void splitToStreamCharactersTest() {
        List<String> words = Arrays.asList("Hello", "World");
        words.stream().map(word->word.split("")).flatMap(Arrays::stream).distinct().collect(toList()).forEach(System.out::println);
    }

    @Test
    public void integerStreamTest() {
        List<String> words = Arrays.asList("Java8","Lambda","Action");
        words.stream().map(String::length).collect(toList()).forEach(System.out::println);
    }

    @Test
    public void maximumCalorieTest() {
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(comparingInt(Dish::getCalories)));
        Dish dish =  mostCalorieDish.get();
        System.out.println(mostCalorieDish);
        System.out.println(dish);

    }

    @Test
    public void dishJoiningTest() {
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu);

        String shortMenu2 = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        System.out.println(shortMenu2);
    }

    @Test
    public void dishCalorieSumTest() {
        int total = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(total);
    }

    @Test
    public void averageCalorieTest() {
        double total = Dish.menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(total);
    }

    @Test
    public void reduceTest() {
        int totalCalories = Dish.menu.stream().collect(reducing(1, Dish::getCalories, (i,j)->i+j));
        System.out.println(totalCalories);

        int sum = Dish.menu.stream().collect(reducing(0, Dish::getCalories,Integer::sum));
        System.out.println(sum);
    }

    @Test
    public void streamIntegerReduceTest() {
        int total = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println(total);
    }

    @Test
    public void reduceSumTest() {
        String shortMenu = Dish.menu.stream().collect(reducing("", Dish::getName, (s1,s2)->s1+s2));
        System.out.println(shortMenu);
        String menu = Dish.menu.stream().map(Dish::getName).collect(reducing((s1,s2)->s1+s2)).get();
        System.out.println(menu);
    }

    @Test
    public void groupCollectionApiWithType() {
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);
    }

    @Test
    public void groupCollectionApiWith() {
        Map<Boolean, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::isVegetarian));
        System.out.println(dishesByType);
    }

    @Test
    public void testGetPrice() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");

        // Do some more tasks, like querying other shops
        doSomethingElse();
        // while the price of the product is being calculated

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
    private static void doSomethingElse() {
        System.out.println("Doing something else...");
    }

    @Test
    public void testAllPrice() {
        final BestPriceFinder bestPriceFinder = new BestPriceFinder();
        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
    }

    @Test
    public void testParallelPrice() {
        final BestPriceFinder bestPriceFinder = new BestPriceFinder();
        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
    }
    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");

    }
    @Test
    public void testParallelFuturePrice() {
        final BestPriceFinder bestPriceFinder = new BestPriceFinder();
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
    }

    @Test
    public void multiple_threads_should_not_share_threadlocal() {

        // Arrange
        String value = "Hippopotomonstrosesquipedaliophobia";
        // Act
        ThreadLocalUtility.set(value);
        // Assert
        assertEquals(value, ThreadLocalUtility.get());
        // create another thread
//        new Thread(new Runnable() {
//            public void run() {
//                assertNull(ThreadLocalUtility.get());
//            }
//        }).start();

        new Thread(()-> {assertNull(ThreadLocalUtility.get());
        System.out.println("to see the thread local value in new thread the value is :"+ThreadLocalUtility.get());
        }).start();
        assertEquals(value, ThreadLocalUtility.get());
    }

    @Test
    public void testIterativeSum() {
        long num = Stream.iterate(1L,i->i+1).limit(10_000_000).reduce(0L, Long::sum);
        System.out.println(num);
        //long num2 = iterativeSum(1000_000);
        //System.out.println(num2);
    }

    @Test
    public void testForkJoin() {
        long[] numbers = LongStream.rangeClosed(1,10_000_000).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long sum =  new ForkJoinPool().invoke(task);
        System.out.println(sum);
    }


    public static long iterativeSum(long n) {
        long result = 0;
        for(long i=1L; i<=n;i++) {
            result+=i;
        }
        return result;
    }


}
