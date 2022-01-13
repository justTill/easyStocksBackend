package com.mobsys.easyStocks.controller;

import com.mobsys.easyStocks.models.dtos.GetNotificationsDto;
import com.mobsys.easyStocks.models.dtos.PostNotificationsDto;
import com.mobsys.easyStocks.persistence.model.WatchlistData;
import com.mobsys.easyStocks.persistence.repository.UserRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistNotificationRepository;
import com.mobsys.easyStocks.persistence.repository.WatchlistRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class NotificationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WatchlistNotificationRepository watchlistRepository;
    private int defaultInterval = 3;
    private float defaultPercentage = 10;

    public NotificationController(final Environment env) {
        if (env.getProperty("percentage") != null) {
            this.defaultPercentage = Float.parseFloat(Objects.requireNonNull(env.getProperty("percentage")));
        }
        if (env.getProperty("Interval") != null) {
            this.defaultInterval = Integer.parseInt(Objects.requireNonNull(env.getProperty("interval")), 10);
        }
    }

    @GetMapping("/notifications")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public List<GetNotificationsDto> getNotifications() {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final UUID watchlistId = userRepository.findFirstByMail(user.getUsername()).getWatchlistId();

        final List<WatchlistData> notifications = watchlistRepository.findByWatchlistIdAndSeen(watchlistId, false);
        final List<GetNotificationsDto> response = new ArrayList<>();
        notifications.forEach(n -> {
            final int interval = n.getDayInterval() != null ? n.getDayInterval() : defaultInterval;
            final float percentage = n.getPercentage() != null ? n.getPercentage() : defaultPercentage;
            response.add(new GetNotificationsDto(n.getSymbol().getSymbol(), n.getSymbol().getName(), interval, percentage));
        });
        return response;
    }

    @PostMapping("/notifications")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "jwtToken")})
    public void notificationsSeen(@RequestBody final PostNotificationsDto notificationsSeen) {
        final UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final UUID watchlistId = userRepository.findFirstByMail(user.getUsername()).getWatchlistId();
        final List<WatchlistData> watchlist = watchlistRepository.findByWatchlistId(watchlistId);
        if (notificationsSeen != null && notificationsSeen.getSymbolsSeen() != null) {
            watchlist.forEach(watchlistData -> {
                if (notificationsSeen.getSymbolsSeen().contains(watchlistData.getSymbol().getSymbol())) {
                    watchlistData.setSeen(true);
                }
            });
            watchlistRepository.saveAll(watchlist);
        }
    }
}