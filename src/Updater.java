import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Updater {
    private Map<String, Double> cache;
    private final String KEY = "6fbf572aae07c153af99cb7c";

    public Updater() {
        new Thread(this::reload).start();
    }

    public void reload() {
        this.cache = request();
    }

    public Map<String, Double> getCache() {
        return cache;
    }

    private Map<String, Double> request() {
        var request = new Request(getUrl());
        var u = JsonParser.parseString(request.getResponse()).getAsJsonObject().getAsJsonObject("conversion_rates");
        try {
            Type a = new TypeToken<Map<String, String>>(){}.getType();
            return new Gson().fromJson(u, a);
        } catch (Exception e) {
            return null;
        }
    }

    private String getUrl() {
        return "https://v6.exchangerate-api.com/v6/" + KEY + "/latest/USD";
    }
}