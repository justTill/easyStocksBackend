package com.mobsys.easyStocks.controller;

import java.util.UUID;

import com.mobsys.easyStocks.config.JWTTokenUtil;
import com.mobsys.easyStocks.models.dtos.PostLoginRequestDto;
import com.mobsys.easyStocks.models.dtos.PostLoginResponseDto;
import com.mobsys.easyStocks.models.dtos.PostRegisterRequestDto;
import com.mobsys.easyStocks.models.dtos.PostRegisterResponseDto;
import com.mobsys.easyStocks.persistence.model.User;
import com.mobsys.easyStocks.persistence.repository.UserRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistRepository;
import com.mobsys.easyStocks.service.JWTUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Validated
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired

    private WatchlistRepository watchlistRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/login")
    public PostLoginResponseDto createAuthenticationToken(@RequestBody PostLoginRequestDto authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        var user = userRepository.findFirstByMail(userDetails.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        final PostLoginResponseDto response = new PostLoginResponseDto();
        response.setToken(token);
        response.setUsername(user.getMail());
        response.setWatchlistId(user.getWatchlistId().toString());

        return response;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @PostMapping(path = "/register")
    public PostRegisterResponseDto register(@RequestBody final PostRegisterRequestDto postRegisterRequestDto) {
        final String username = postRegisterRequestDto.getUsername();
        final String password = postRegisterRequestDto.getPassword();
        if (username != null && password != null && userRepository.findFirstByMail(username) == null) {
            final UUID watchlistId = UUID.randomUUID();
            final User user = new User(username, passwordEncoder.encode(password), watchlistId);
            userRepository.save(user);
            return new PostRegisterResponseDto(user.getId(), username, watchlistId.toString());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exit or missing field in Request body");
    }

}