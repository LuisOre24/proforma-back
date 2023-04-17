package com.soldev.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.soldev.entity.JwtRequest;
import com.soldev.entity.JwtResponse;
import com.soldev.entity.Usuario;
import com.soldev.repo.IUsuarioRepo;
import com.soldev.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {
	
	@Autowired
    private JwtUtil jwtUtil;

	@Autowired
	private IUsuarioRepo usuarioRepo;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		
		String usuario = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();
		authenticate(usuario, password);

		UserDetails userDetails = loadUserByUsername(usuario);
		Usuario user = usuarioRepo.findByUsername(usuario);
		String username = user.getUsername();
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		
		return new JwtResponse(username, newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepo.findByUsername(username);
		
		if (usuario == null) {
	//		throw new UsernameNotFoundException(String.format("Usuario no existe" + username));
			throw new UsernameNotFoundException("Usuario no encontrado con ese username o email : " + username);
		}

		List<GrantedAuthority> roles = new ArrayList<>();
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));
		});
		
		UserDetails userDetail = new User(usuario.getUsername(), usuario.getPassword(), roles);
		
		return userDetail;

	}

}
