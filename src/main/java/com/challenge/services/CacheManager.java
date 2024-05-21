package com.challenge.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.util.Map;

public class CacheManager {
    private static Map<String, Double> cache;
    private static final String KEY;
    private static final File localCache;
    private static long nextUpdate;

    static {
        KEY = System.getenv("EXCHANGE_KEY");
        if (KEY == null) {
            System.out.println("variavel EXCHANGE_KEY não definida");
            System.exit(1);
        }
        localCache = new File("data");
    }

    public CacheManager() {
        if (cache == null) {
            reload();
        }
    }

    public Map<String, Double> getCache() {
        return cache;
    }

    public void reload() {
        try {
            if (!localCache.exists()) {
                writeCache();
            }
            var json = getJson();
            nextUpdate = json.get("time_next_update_unix").getAsInt();
            if (isUpdated()) {
                writeCache();
                json = getJson();
            }
            var valores = json.get("conversion_rates");
            cache = (new Gson()).fromJson(valores, (new TypeToken<Map<String, Double>>(){}).getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isUpdated(){
        return System.currentTimeMillis() / 1000 >= nextUpdate;
    }

    private JsonObject getJson() throws FileNotFoundException {
        return JsonParser.parseReader(new FileReader(localCache)).getAsJsonObject();
    }

    private void writeCache() throws IOException {
        var writer = new FileWriter(localCache);
        writer.write(ApiRequest.getHttps(getUrl()));
        writer.close();
    }

    private URI getUrl() {
        return URI.create("https://v6.exchangerate-api.com/v6/" + KEY + "/latest/USD");
    }
}