package com.project.pickaboo.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    public static final String Auth_HEADER = "Authorization";
    public static final String BEARER_PREPIX = "Bearer ";


    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        String token = resolveAccessToken(request);

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);

            log.info("JwtFilter authentication = {}", authentication);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(Auth_HEADER);

        log.info("resolveToken token = {}", bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREPIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
