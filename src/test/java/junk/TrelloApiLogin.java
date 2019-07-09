package junk;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.testng.annotations.Test;
import ui.core.BrowserFactory;

import java.io.IOException;
import java.util.Map;

public class TrelloApiLogin extends BrowserFactory {

    CookieStorage cookieStorage = new CookieStorage();

    OkHttpClient client = new OkHttpClient.Builder().build();
    Gson gson = new Gson();

    @Test (priority = 1)
    public void testCookies() throws IOException {
        client.newCall(new Request.Builder().url("https://trello.com").build()).execute().body().string();
        for (Cookie cookie : cookieStorage.cookies){
            System.out.println(cookie.name() + ":" + cookie.value());
        }
    }

    @Test (priority = 2)
    public void loginByApi() throws IOException, InterruptedException {

        FormBody formData = new FormBody.Builder()
                .add("method", "password")
                .add("factors[user]", "test.qa9966@gmail.com")
                .add("factors[password]", "test_Qa9966$")
                .build();
        Request request = new Request.Builder().url("http://trello.com/1/authentication").post(formData).build();
        String response = client.newCall(request).execute().body().string();
        System.out.println("Response: " + response);
        Map<String, String> map = gson.fromJson(response, new TypeToken<Map<String, String>>() {
        }.getType());
        String code = map.get("code");
        System.out.println("CODE: " + code);


        // authorization/session
        String dsc = cookieStorage.cookies.stream().filter(cookie -> cookie.name().equals("dsc")).findFirst().get().value();
        FormBody sessionFormData = new FormBody.Builder()
                .add("authentication", code)
                .add("dsc", dsc)
                .build();
        Request requestSession = new Request.Builder().url("https://trello.com/1/authorization/session").post(sessionFormData).build();
        response = client.newCall(requestSession).execute().body().string();
        System.out.println(response);

        //SELENIUM
        driver().get("https://trello.com");

        for (Cookie cookie : cookieStorage.cookies) {
            org.openqa.selenium.Cookie seleniumCookie = new org.openqa.selenium.Cookie(cookie.name(), cookie.value());
        }

        driver().navigate().refresh();
        Thread.sleep(10000);
    }
}
