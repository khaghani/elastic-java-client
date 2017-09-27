/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.sadeghpro.elasticsearch.client;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 *
 * @author peter
 */
public class Elastic {

    private final int port;
    private final boolean isAuthorizate;
    private final String ip, username, password, host, base64login;

    public Elastic(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.isAuthorizate = false;
        this.host = "http://" + this.ip + ":" + this.port + "/";
        this.username = "";
        this.password = "";
        this.base64login = "";
    }

    public Elastic(String ip, int port, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.isAuthorizate = true;
        this.host = "http://" + this.ip + ":" + this.port + "/";
        this.username = username;
        this.password = password;
        String login = this.username + ":" + this.password;
        base64login = new String(Base64.encodeBase64(login.getBytes()));
    }
    
    public String customQuery(String path) {
        return customQuery(path, "", Method.GET);
    }
    
    public String customQuery(String path, String body) {
        return customQuery(path, body, Method.GET);
    }

    public String customQuery(String path, String body, Method method) {
        try {
            Connection c = Jsoup.connect(host + path).method(method).ignoreContentType(true).ignoreHttpErrors(true);
            if(body.length() > 0){
                c = c.requestBody(body);
            }
            if (isAuthorizate) {
                c = c.header("Authorization", "Basic " + base64login);
            }
            Response r = c.execute();
            return r.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
