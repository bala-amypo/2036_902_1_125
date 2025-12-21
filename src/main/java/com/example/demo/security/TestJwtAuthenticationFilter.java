package com.example.demo.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestJwtAuthenticationFilter {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter; // the class you want to test

    @Mock
    private JwtTokenProvider jwtTokenProvider; // mock JwtTokenProvider used in the filter

    @Mock
    private FilterChain filterChain;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    @WithMockUser
    public void testJwtAuthenticationFilter_validToken() throws Exception {
        String token = "valid_jwt_token";
        request.addHeader("Authorization", "Bearer " + token);

        // Mock JwtTokenProvider behavior
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);
        when(jwtTokenProvider.getAuthentication(token)).thenReturn(
                new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>())
        );

        // Call the filter method
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verify the authentication was set in the context
        SecurityContext securityContext = SecurityContextHolder.getContext();
        assertNotNull(securityContext.getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testJwtAuthenticationFilter_invalidToken() throws Exception {
        String token = "invalid_jwt_token";
        request.addHeader("Authorization", "Bearer " + token);

        // Mock JwtTokenProvider behavior
        when(jwtTokenProvider.validateToken(token)).thenReturn(false);

        // Call the filter method
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Verify the filter chain is called
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
