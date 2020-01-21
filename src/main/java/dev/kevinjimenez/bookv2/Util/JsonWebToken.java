package dev.kevinjimenez.bookv2.Util;

public class JsonWebToken {
    public String token;

    public JsonWebToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
