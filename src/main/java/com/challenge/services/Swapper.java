package com.challenge.services;

import java.util.Map;

public class Swapper {

    private CacheManager cacheUpdater;
    private Map<String, Double> cache;

    public Swapper() {
        cacheUpdater = new CacheManager();
        cache = cacheUpdater.getCache();
    }

    public double rate(String coin1, String coin2) {
        return cache.get(coin2) / cache.get(coin1);
    }

    public double swap(String coin1, String coin2,double value) {
        return value * rate(coin1,coin2);
    }
}
