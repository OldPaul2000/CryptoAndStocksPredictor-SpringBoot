package com.universitymlproject.cryptopredictor.service.userrelatedservice;

import com.universitymlproject.cryptopredictor.config.JwtConstants;
import com.universitymlproject.cryptopredictor.dto.userrelated.LoginCredentialsDTOPost;
import com.universitymlproject.cryptopredictor.dto.userrelated.LoginResponseDTO;
import com.universitymlproject.cryptopredictor.dto.userrelated.UserDTOGet;
import com.universitymlproject.cryptopredictor.dtomappers.userrelated.LoginResponseDTOMapper;
import com.universitymlproject.cryptopredictor.dtomappers.userrelated.LoginResponseMapper;
import com.universitymlproject.cryptopredictor.dtomappers.userrelated.UserMapperDTOGet;
import com.universitymlproject.cryptopredictor.model.userrelated.User;
import com.universitymlproject.cryptopredictor.repository.userrepository.UserRepository;
import com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.userrelated.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static com.universitymlproject.cryptopredictor.restcontroller.exceptionhandling.ExceptionsMessages.USER_NOT_FOUND;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapperDTOGet userMapper;
    private JwtService jwtService;
    private LoginResponseMapper loginResponseMapper;
    private LoginResponseDTOMapper loginMapper;
    private JwtConstants jwtConstants;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapperDTOGet userMapper,
                       JwtService jwtService,
                       LoginResponseMapper loginResponseMapper,
                       LoginResponseDTOMapper loginMapper,
                       JwtConstants jwtConstants) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.loginResponseMapper = loginResponseMapper;
        this.loginMapper = loginMapper;
        this.jwtConstants = jwtConstants;
    }

    public UserDTOGet getUserById(Long id){
        User user = userRepository.findUserById(id);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return userMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginCredentialsDTOPost loginCredentials, AuthenticationManager authenticationManager){
        Authentication authentication = UsernamePasswordAuthenticationToken
                .unauthenticated(loginCredentials.username(), loginCredentials.password());
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        LoginResponseDTO loginResponseDTO = null;
        if(authenticationResponse != null && authenticationResponse.isAuthenticated()){
            User user = userRepository.findUserByUsernameWithRoles(authenticationResponse.getName());
            SecretKey secretKey = Keys.hmacShaKeyFor(jwtConstants.getSECRET().getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().issuer("Crypto predictor").subject("JWT Token")
                    .claim("username", authenticationResponse.getName())
                    .claim("authorities", authenticationResponse.getAuthorities()
                            .stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")))
                    .claim("userId", user.getId())
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 3600 * 8))
                    .signWith(secretKey).compact();

            jwtService.addNewToken(user, jwt);

            loginResponseDTO = loginMapper.toDTO(user, jwt);
        }
        return loginResponseDTO;
    }

}
