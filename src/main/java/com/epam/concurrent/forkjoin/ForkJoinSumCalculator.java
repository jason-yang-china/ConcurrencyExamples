package com.epam.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] number, int start, int end) {
        this.numbers = number;
        this.start = start;
        this.end = end;
    }


    protected Long compute() {
        int length = end - start;
        if(length <= THRESHHOLD) {
            return computeSequentially();
        }else {
            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start+length/2);
            //leftTask.fork();
            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start+length/2, end);
            //Long rightResult = rightTask.compute();
            rightTask.fork();
            //leftTask.compute();//test
            Long leftResult = leftTask.compute();
            Long rightResult = rightTask.join();
            //Long leftResult = leftTask.join();
            return leftResult + rightResult;
        }

    }

    public long computeSequentially() {
        long sum = 0;
        for(int i = start;i<end; i++) {
            sum+=numbers[i];
        }
        return sum;
    }
}
