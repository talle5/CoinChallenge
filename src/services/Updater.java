package services;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Updater {
    private static Map<String, Double> cache;
    private static final String KEY = "6fbf572aae07c153af99cb7c";

    public Updater() {
        if (cache == null) {
            reload();
        }
    }

    public void reload() {
        cache = request();
    }

    public Map<String, Double> getCache() {
        return cache;
    }

    private Map<String, Double> request() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(getUrl()).build();
        try {
            var resposta = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            var valores = JsonParser.parseString(resposta)
                    .getAsJsonObject().
                    getAsJsonObject("conversion_rates");
            return new Gson().fromJson(valores, new TypeToken<Map<String, Double>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private URI getUrl() {
        return URI.create("https://v6.exchangerate-api.com/v6/" + KEY + "/latest/USD");
    }
}