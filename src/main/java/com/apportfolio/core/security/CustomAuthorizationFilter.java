package com.apportfolio.core.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.apportfolio.core.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
	private final JwtTokenProvider jwtProvider;
	private final UserServiceImpl userServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getTokenFromReq(request);

			if (StringUtils.hasText(token) && jwtProvider.isValid(token)) {
				UserDetails userDetails = userServiceImpl.loadUserByUsername(jwtProvider.getEmail(token));

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, jwtProvider.getRoles(token), userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		} catch (Exception e) {
			log.info("No se ha podido establecer la autenticación de usuario en el contexto de seguridad");
		}

		filterChain.doFilter(request, response);

	}

	private String getTokenFromReq(HttpServletRequest request) {
		String bearerToken = request.getHeader(JwtTokenProvider.TOKEN_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
			return bearerToken.substring(JwtTokenProvider.TOKEN_PREFIX.length(), bearerToken.length());
		}

		return null;
	}

}
