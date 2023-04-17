package com.soldev.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String jwtToken;

	public JwtResponse( String usuario, String jwtToken) {
		this.usuario = usuario;
		this.jwtToken = jwtToken;
	}

	public String getUsuario() {
        return usuario;
    }

    public void setUser(String usuario) {
        this.usuario = usuario;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
