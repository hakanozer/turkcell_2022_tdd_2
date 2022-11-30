package com.works.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.works.utils.Util;
import org.jsoup.Jsoup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    public int userEmail( String email) {
        return email.length();
    }


    public boolean jwt( String username, String password ) {
        try {
            String url = "https://dummyjson.com/auth/login";
            Map<String, String> hm = new HashMap();
            hm.put("username", username);
            hm.put("password", password);
            Gson gson = new Gson();
            String stUser = gson.toJson(hm);

            String data = Jsoup.connect(url).ignoreContentType(true)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json").requestBody(stUser)
                    .timeout(15000).post().body().text();
            JsonObject obj = gson.fromJson(data, JsonObject.class);
            String token = obj.get("token").getAsString();
            System.out.println(token);
            if (token != null && token.equals("")) {
                return true;
            }
            return false;
        }catch (Exception ex) {
            //System.err.println("JWT Error :" + ex );
            return false;
        }
    }

    public boolean addCustomer(int price, String title) {
        Util util = new Util();
        try {
            String sql = "insert into customer values(default, ?, ?)";
            PreparedStatement pre = util.con().prepareStatement(sql);
            pre.setInt(1, price);
            pre.setString(2, title);
            int i = pre.executeUpdate();
            return i > 0 ? true : false;
        }catch (Exception ex) {
            System.err.println("sql Error :" + ex);
        }finally {
            util.close();
        }
        return false;
    }

}
