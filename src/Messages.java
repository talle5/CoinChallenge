
public class Messages {
    public static final String KEY = "6fbf572aae07c153af99cb7c";

    public static String getUrl(String coin) {
        return "https://v6.exchangerate-api.com/v6/" + KEY + "/latest/" + coin;
    }

    public static String getUrl(String coin1,String coin2) {
        return "https://v6.exchangerate-api.com/v6/" + KEY + "/pair/" + coin1 + "/" + coin2;
    }
}
