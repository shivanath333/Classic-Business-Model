package com.sprint.cbm.models;



public class JwtResponseBuilder {
    private String jwtToken;
    private String username;

    private JwtResponseBuilder() {
    }

    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }

    public JwtResponseBuilder jwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
        return this;
    }

    public JwtResponseBuilder username(String username) {
        this.username = username;
        return this;
    }

    public JwtResponse build() {
        return new JwtResponse(jwtToken, username);
    }
}


