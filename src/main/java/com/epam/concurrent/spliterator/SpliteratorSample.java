package com.epam.concurrent.spliterator;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorSample {

    private List<String> quote = Arrays.asList("This ", "above", "all- ", "to ", "thine ", "own ", "self ", "be ", "true");

    public List<String> getQuoteList() {
        return quote;
    }

    public void printAll() {
        Spliterator<String> s = quote.spliterator();
        Spliterator<String> f = s.trySplit();

        f.forEachRemaining(System.out::print);
        System.out.println("then put the second spliterator");
        s.forEachRemaining(System.out::print);
    }

}
