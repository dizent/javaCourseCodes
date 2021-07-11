package cn.dizent.javaCourseCodes.week03.gateway.getData;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 15:05
 * @Description:
 */
public enum HttpClientRequestService {
    /**
     * 单例HttpClient
     */
    INSTANCE;

    CloseableHttpClient httpClient = HttpClientBuilder.create().build();


    public String httpClientGet(String urlStr){

        HttpGet httpGet = new HttpGet(urlStr);

        CloseableHttpResponse response = null;

        String result = null;
        try {
            response = httpClient.execute(httpGet);

            response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);
        }catch (IOException e) {
            return "请求异常" + e.getMessage();
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
}
