package cn.dizent.javaCourseCodes.week02nio;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Auther: 布谷
 * @Date: 2021/7/6 14:35
 * @Description:
 */
public class HttpRequestDemo {

    final static String url = "http://localhost:8808/test";

    public static void main(String[] args) {
        try {
            System.out.println("httpClient 请求结果: "+new HttpRequestDemo().httpClient(url));
            System.out.println("okHttp 请求结果: "+new HttpRequestDemo().okHttp(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String httpClient(String urlStr) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(urlStr);

        CloseableHttpResponse response = null;

        String result = null;
        try {
            response = httpClient.execute(httpGet);

            response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public String okHttp(String urlStr) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlStr)
                .build();
        String result = null;

        try  {
            Response response = client.newCall(request).execute();
            result =  response.body().string();
        }catch (IOException e) {

        }
        return result;
    }
}
