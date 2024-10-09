# HttpClient5 笔记

## 一、依赖

```
<!-- HttpCLient 5 依赖 -->
<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.1.3</version>
</dependency>

<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5-fluent</artifactId>
    <version>5.1.3</version>
</dependency>
```



## 二、工具类

```
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Httpclient5 工具类
 * */
@Slf4j
public class HttpClientToole_5 {

    /**
     * GET请求（无请求参数）
     * */
    public static String getWithNoParam(String url) {
        // 请求结果
        String resultContent = null;

        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                resultContent = EntityUtils.toString(entity);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return resultContent;
    }

    /**
     * GET请求（带请求参数）
     * */
    public static String getWithParam(String url, Map map) {
        // 请求结果
        String resultContent = null;

        HttpGet httpGet = new HttpGet(url);
        List<NameValuePair> nvps = new ArrayList<>();
        // 封装请求参数
        map.forEach(new BiConsumer<String,String>() {
            @Override
            public void accept(String key, String value) {
                nvps.add(new BasicNameValuePair(key,value));
            }
        });
        // 请求参数添加至url
        try {
            URI uri = new URIBuilder(new URI(url))
                    .addParameters(nvps)
                    .build();
            httpGet.setUri(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                resultContent = EntityUtils.toString(entity);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return resultContent;
    }

    /**
     * POST请求（无请求参数）
     * */
    public static String postWithNoParam(String url) {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                // 确保流被完全消费
                EntityUtils.consume(entity);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST请求（带请求参数）
     * */
    public static String postWithParam(String url,Map map) {
        String result = null;
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> nvps = new ArrayList<>();
        // 封装请求参数
        map.forEach(new BiConsumer<String,String>() {
            @Override
            public void accept(String key, String value) {
                nvps.add(new BasicNameValuePair(key,value));
            }
        });
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                // 确保流被完全消费
                EntityUtils.consume(entity);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST请求（请求参数未JSON字符串）
     * */
    public static String postWithJSON(String url, String jsonBody) {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
```

