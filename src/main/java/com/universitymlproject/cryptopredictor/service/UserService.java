package com.universitymlproject.cryptopredictor.service;

import com.universitymlproject.cryptopredictor.dto.userrelated.LoginCredentialsDTOPost;
import com.universitymlproject.cryptopredictor.dto.userrelated.LoginResponseDTO;
import com.universitymlproject.cryptopredictor.dtomappers.userrelated.LoginResponseDTOMapper;
import com.universitymlproject.cryptopredictor.dtomappers.userrelated.UserDTOMapper;
import com.universitymlproject.cryptopredictor.model.userrelated.User;
import com.universitymlproject.cryptopredictor.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private UserDTOMapper userMapper;
    private LoginResponseDTOMapper loginMapper;

    @Value("${jwt.header}")
    private String JWT_HEADER;

    @Value("${jwt.secret-key}")
    private String SECRET;

    @Autowired
    public UserService(UserRepository userRepository,
                       JwtService jwtService,
                       UserDTOMapper userMapper,
                       LoginResponseDTOMapper loginMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.loginMapper = loginMapper;
    }

    public LoginResponseDTO getUserById(Long id){
        User user = userRepository.findUserById(id);
        return userMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginCredentialsDTOPost loginCredentials, AuthenticationManager authenticationManager){
        Authentication authentication = UsernamePasswordAuthenticationToken
                .unauthenticated(loginCredentials.username(), loginCredentials.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        LoginResponseDTO loginResponse = null;
        if(authenticationResponse != null && authenticationResponse.isAuthenticated()){
            User user = userRepository.findUserByUsernameWithRoles(authenticationResponse.getName());

            SecretKey secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().issuer("Crypto predictor").subject("JWT Token")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities()
                            .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")))
                    .claim("userId", user.getId())
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 600000))
                    .signWith(secretKey).compact();

            jwtService.addNewToken(user, jwt);

            loginResponse = loginMapper.toDTO(user, jwt);
        }
        return loginResponse;
    }

}
