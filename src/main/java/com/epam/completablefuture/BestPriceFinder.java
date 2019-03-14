package com.epam.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

public class BestPriceFinder {

    private final List<Shop> shops = Arrays.asList(new Shop("Best Price"), new Shop("LetssSaveBig"), new Shop("MyFavoriteShop"));
    private final Executor executor = Executors.newFixedThreadPool(shops.size(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });
    public List<String> findPricesSequential(String product) {
        return shops.stream().map(shop->shop.getName() + " price is "+shop.getPrice(product)).collect(toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.parallelStream().map(shop->shop.getName() + " price is "+shop.getPrice(product)).collect(toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is "
                                + shop.getPrice(product), executor))
                        .collect(toList());
        List<String> prices = priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
        return prices;

    }




}
