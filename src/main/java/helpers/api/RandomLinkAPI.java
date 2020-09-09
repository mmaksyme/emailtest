package helpers.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class RandomLinkAPI {
    private HttpURLConnection connection;
    private String url;
    private JSONObject jsonObject;

    public RandomLinkAPI(String url) {
        this.url = url;
        String content;
        try {
            content = getResponseContent();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response content", e);
        }
        jsonObject = new JSONObject(content);
    }

    public String getValueByKey(String key) {
        return jsonObject.get(key).toString();
    }

    public String getRandomLink() {
        String value = "";
        Iterator<String> key = jsonObject.keys();
        while (key.hasNext()) {
            value = jsonObject.get(key.next()).toString();
            if (value.contains("https://") || value.contains("http://"))
                break;
        }
        return value;
    }

    private String getResponseContent() throws IOException {
        connection = (HttpURLConnection) new URL(url).openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");
        connection.setRequestMethod("GET");
        return convertResponseIntoString();
    }

    private String convertResponseIntoString() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            connection.disconnect();
            return stringBuilder.toString();
        }
    }
}
