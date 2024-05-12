package com.challenge.services;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class CacheManager {
    private static Map<String, Double> cache;
    private static final String KEY = "6fbf572aae07c153af99cb7c";
    private static final File localCache = new File("data");

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
            var json = JsonParser.parseReader(new FileReader(localCache)).getAsJsonObject();
            var nextUpdate = json.get("time_next_update_unix").getAsInt();
            if (System.currentTimeMillis() / 1000 >= nextUpdate) {
                writeCache();
                json = JsonParser.parseReader(new FileReader(localCache)).getAsJsonObject();
            }
            var valores = json.get("conversion_rates");
            cache = (new Gson()).fromJson(valores, (new TypeToken<Map<String, Double>>(){}).getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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