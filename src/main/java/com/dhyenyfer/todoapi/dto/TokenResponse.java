package com.dhyenyfer.todoapi.dto;

public class TokenResponse {

    private String token;

    // Construtor necessário para o seu AuthController fazer: new TokenResponse(token)
    public TokenResponse(String token) {
        this.token = token;
    }

    // Construtor vazio padrão (boa prática para o Jackson serializar)
    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}