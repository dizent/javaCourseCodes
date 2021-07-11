package cn.dizent.javaCourseCodes.week03.gateway.getData;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 15:05
 * @Description:
 */
public enum OkHttpRequestService {
    /**
     * 枚举单例
     */
    INSTANCE;

    /**
     * 实现单例模式
     */
    OkHttpClient client = new OkHttpClient();

    public String okHttpGet(String urlStr) {

        Request request = new Request.Builder()
                .url(urlStr)
                .build();
        String result = null;

        try  {
            Response response = client.newCall(request).execute();
            result =  response.body().string();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
