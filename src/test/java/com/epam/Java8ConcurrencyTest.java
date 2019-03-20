package com.epam;

import com.epam.concurrent.cryptocurrency.Crawler;
import com.epam.concurrent.java8concurrency.Task;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Java8ConcurrencyTest {

    @Test
    public void testVolatileDefault() throws InterruptedException{
        Task t1 = new Task();
        Task t2 = new Task();
        t1.start();
        t2.start();

        t1.wait(5);
        t2.threadSetVisible();
        t1.join();
        t2.join();

    }

    @Test
    public void testFutureWithThreadPool() throws InterruptedException, ExecutionException {
        final List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<Integer> callSum = ()-> {
            return   intList.stream().mapToInt(i->i.intValue()).sum();
        };

        Future<Integer> future1 = service.submit(callSum);
        Future<Integer> future2 = service.submit(callSum);
        Integer result1 = future1.get();
        Integer result2 = future2.get();

        System.out.println("result1:"+result1);
        System.out.println("result2:"+result2);
        service.shutdown();
        service.awaitTermination(0, TimeUnit.SECONDS);
        System.out.println();

    }

    @Test
    public void testCompletableFuture() {
        List<Integer> list = Arrays.asList(10, 20, 30, 40);
        list.stream().map(data -> CompletableFuture.supplyAsync(()-> data*data)).
                map(future->future.thenApply(n->n*n)).
                map(t->t.join()).forEach(result->System.out.println(result));
    }

    @Test
    public void testSumStream() {
        Instant before = Instant.now();
        int total = IntStream.of(3,1,4,1,5,9,2,6,7,8,11,22).sum();
        Instant after = Instant.now();
        Duration duration = Duration.between(before, after);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + " ms");
    }

    @Test
    public void testIntStreamRange() {
        Instant before = Instant.now();
        int total = IntStream.range(0,10000).parallel().sum();
        Instant after = Instant.now();
        Duration duration = Duration.between(before, after);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + " ms");
    }

    @Test
    public void testIntNotParallelStreamRange() {
        Instant before = Instant.now();
        int total = IntStream.range(0,10000).sum();
        Instant after = Instant.now();
        Duration duration = Duration.between(before, after);
        System.out.println("Total of doubles = " + total);
        System.out.println("time = " + duration.toMillis() + " ms");
    }

    @Test
    public void testCrawlerDownloadSite() throws InterruptedException, ExecutionException{
        ExecutorService executorService =  Executors.newFixedThreadPool(4);
        final Crawler crawler = new Crawler();
        Callable<String> callable = ()-> {
            return crawler.downloadSite(crawler.getRandomSite());
        };

        Future<String> future1 = executorService.submit(callable);
        Future<String> future2 = executorService.submit(callable);

        final String str1 = future1.get();
        final String str2 = future2.get();
        System.out.println("str1"+str1);
        System.out.println("str2"+str2);
        Callable<Document> document1 = ()-> {
          return  crawler.getDocument(str1);
        };

        Callable<Document> document2 = ()-> {
            return  crawler.getDocument(str1);
        };

        Future<Document> documentStr1 = executorService.submit(document1);
        Future<Document> documentStr2 = executorService.submit(document2);

        System.out.println(documentStr1);
        System.out.println(documentStr2);

        executorService.shutdown();
        executorService.awaitTermination(0, TimeUnit.SECONDS);


    }

    @Test
    public void testForeachLambda() {
        final  List<String> fruitList = Arrays.asList("apple","banana","pear", "watermellon");
        fruitList.forEach(f->System.out.println(f));
        fruitList.stream().forEach(fruit->System.out.println(fruit));

        final Map<String,String> map = new HashMap<String, String>();
        map.put("Jason","200");
        map.put("Terry","110");

        map.forEach((k,v)->{System.out.println("Key:"+k+",Value:"+v);});
        System.out.println(fruitList.stream().filter(a->"banana".equals(a)).count());
        fruitList.stream().filter(f->f.startsWith("p")).forEach(System.out::println);

    }

    @Test
    public void testLambdaPredicate() {
        final List<String> nameList = Arrays.asList("Terry","Jerry","Jenny","Danny","Rafe");
        nameList.stream().filter(s->s.contains("Je")).forEach(System.out::println);
    }

    @Test
    public void computeWithOutParallelTest() {
        long number = Stream.iterate(1L, i->i+1).limit(10_000_000).reduce(0L, Long::sum);
        System.out.println(number);
    }

    @Test
    public void computeWithParallelTest() {
        long number = Stream.iterate(1L, i->i+1).limit(10_000_000).parallel().reduce(0L, Long::sum);
        System.out.println(number);
    }

    @Test
    public void nonParallelRangedSumTest() {
        long number =  LongStream.rangeClosed(1, 10_000_000).reduce(0L, Long::sum);
        Assert.assertEquals(50000005000000L, number);
    }

    @Test
    public void parallelRangedSumTest() {
        long number =  LongStream.rangeClosed(1, 10_000_000).parallel().reduce(0L, Long::sum);
        Assert.assertEquals(50000005000000L, number);
    }

}
