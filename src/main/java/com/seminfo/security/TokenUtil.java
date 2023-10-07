package com.seminfo.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;

import com.seminfo.domain.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

	private static final String EMISSOR = "seminfo2023";
	private static final String TOKEN_KEY = "01234567890123456789012345678901"; // Chave deve ter 256 bits, nesse caso
																				// 32 caracteres, para a criptografia
	private static final long MINUTOS = 60;

	public static String getToken(User user) {
		
		System.out.println("Sujeito para o token = "+user.getUsername());

		String token = JWT.create()
				.withSubject(user.getUsername()) // (Payload) define para quem é esse token (Sujeito)
																		
				.withIssuer(EMISSOR) // (Payload) minha referencia (Emissor)
				.withExpiresAt(LocalDateTime.now().plusMinutes(MINUTOS).toInstant(ZoneOffset.of("-03:00"))) // (Payload)
				.withClaim("user", user.getUsername()) // (Payload) id do usuário
				.sign(Algorithm.HMAC256(TOKEN_KEY.getBytes())); // (Signature)

		return token;
	}

	public static Authentication getAuthentication(HttpServletRequest request) throws Exception {

		String token = request.getHeader("Authorization");
		String emissor = "";
		Date validade;

		if (token != null && !token.isEmpty()) {

			token = token.replace("Bearer ", "");

			// A segurança do token, está na chave, apenas eu tenho
			DecodedJWT decode = JWT.require(Algorithm.HMAC256(TOKEN_KEY))
					.withIssuer(EMISSOR)
					.build()
					.verify(token);// verifica se é valido

			if (decode != null) {
				// token verificado e descriptografado
				emissor = decode.getIssuer();
				validade = decode.getExpiresAt();
				
				if (emissor.equals(EMISSOR) && validade.after(new Date(System.currentTimeMillis()))) {

					// caso a requisição tenha o cabeçalho correto, gero um "token interno"
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("usuario", null,
							Collections.emptyList());

					return auth;
				}
				
			}else {
				return null;
			}

		} else {
			return null;
		}

		return null;
	}

}
