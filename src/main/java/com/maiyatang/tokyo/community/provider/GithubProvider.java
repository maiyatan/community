package com.maiyatang.tokyo.community.provider;

import com.alibaba.fastjson.JSON;
import com.maiyatang.tokyo.community.dto.AccessTokenDTO;
import com.maiyatang.tokyo.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    /**
     * AccessTokenを取得メソッド
     * @param accessTokenDTO
     * @return AccessToken
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        final String url = "https://github.com/login/oauth/access_token";

        // okHttpのPost例を利用して、AccessTokenを取得する
        // 参考：https://square.github.io/okhttp/#post-to-a-server
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //　例：access_token=5ac55ac4c743f0fb55fd10bbe3297f090a51900f&scope=&token_type=bearer
            String accessToken = string.split("&")[0].split("=")[1];
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *Githubウーザ個人情報を取得メソッド
     * @param accessToken
     * @return Githubウーザ個人情報
     */
    public GithubUser getGithubUser(String accessToken) {

        final String url = "https://api.github.com/user?access_token="+accessToken;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
