import com.google.gson.Gson;

import java.util.Map;

public class Updater {
    private Map<String, Double> map;

    Updater() {
        new Thread(this::reload).start();
    }

    void reload() {
        this.map = request();
    }

    public Map<String, Double> getMap() {
        return map;
    }

    private Map<String, Double> request() {
        var request = new Request(Messages.getUrl("USD"));
        try {
            return new Gson().fromJson(request.getResponse(), rates.class).conversion_rates();
        } catch (Throwable e) {
            return null;
        }
    }
}
