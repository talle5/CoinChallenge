import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private final HttpClient client;
    private final HttpRequest hrequest;

    public Request(String url) {
        client = HttpClient.newHttpClient();
        hrequest = HttpRequest.newBuilder(URI.create(url)).build();
    }

    public String getResponse() {
        try {
            return client.send(hrequest, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}