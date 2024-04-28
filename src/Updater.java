import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Updater {
    private Map<String, Double> cache;
    private static final String KEY = "6fbf572aae07c153af99cb7c";

    public Updater() {
        reload();
    }

    public void reload() {
        this.cache = request();
    }

    public Map<String, Double> getCache() {
        return cache;
    }

    private Map<String, Double> request() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(getUrl()).build();
        try {
            var resposta = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            var u = JsonParser.parseString(resposta)
                    .getAsJsonObject().
                    getAsJsonObject("conversion_rates");
            Type a = new TypeToken<Map<String, Double>>(){}.getType();
            return new Gson().fromJson(u, a);
        } catch (Exception e) {
            return null;
        }
    }

    private URI getUrl() {
        return URI.create("https://v6.exchangerate-api.com/v6/" + KEY + "/latest/USD");
    }
}