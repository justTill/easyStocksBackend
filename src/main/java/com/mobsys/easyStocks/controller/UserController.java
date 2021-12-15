package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.models.dtos.PostLoginRequestDto;
import com.mobsys.easyStocks.models.dtos.PostLoginResponseDto;
import com.mobsys.easyStocks.models.dtos.PostRegisterRequestDto;
import com.mobsys.easyStocks.models.dtos.PostRegisterResponseDto;
import com.mobsys.easyStocks.persistence.model.User;
import com.mobsys.easyStocks.persistence.repository.UserRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Validated
@RestController
public class UserController {


    @Autowired
    UserRepository userRepository;
    @Autowired
    WatchlistRepository watchlistRepository;

    @PostMapping(path = "/login")
    public PostLoginResponseDto login(@RequestBody final PostLoginRequestDto loginData) {
        return null;
    }

    @PostMapping(path = "/register")
    public PostRegisterResponseDto register(@RequestBody final PostRegisterRequestDto postRegisterRequestDto) {
        final String username = postRegisterRequestDto.getUsername();
        final String password = postRegisterRequestDto.getPassword();
        if (username != null && password != null && userRepository.findFirstByMail(username) == null) {
            final UUID watchlistId = UUID.randomUUID();
            final User user = new User(username, password, watchlistId);
            userRepository.save(user);
            return new PostRegisterResponseDto(user.getId(), username, watchlistId.toString());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exit or missing field in Request body");
    }

}