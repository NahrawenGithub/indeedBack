package com.backendspringboot.backend.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String jwtRefreshToken;
    public JwtResponse(String jwttoken,String jwtRefreshToken)
    {
        this.jwttoken = jwttoken;
        this.jwtRefreshToken = jwtRefreshToken;

    }
    public String getRefreshToken(){return this.jwtRefreshToken;}
    public String getToken(){
        return this.jwttoken;
    }
}