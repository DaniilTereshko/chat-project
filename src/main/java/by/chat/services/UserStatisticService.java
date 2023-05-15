package by.chat.services;

import by.chat.services.api.IUserService;
import by.chat.services.api.IUserStatisticsService;
import java.util.concurrent.atomic.AtomicInteger;

public class UserStatisticService implements IUserStatisticsService {
    private final IUserService userService;
    private final AtomicInteger activeUserCounter;

    public UserStatisticService(IUserService userService) {
        this.userService = userService;
        this.activeUserCounter =  new AtomicInteger(0);
    }

    @Override
    public Integer getCountActiveUsers() {
        return activeUserCounter.get();
    }

    @Override
    public Integer getCountUsers() {
        return userService.get().size();
    }
    @Override
    public Integer addCountActiveUser() {
        return activeUserCounter.incrementAndGet();
    }

    @Override
    public Integer decCountActiveUser() {
        return activeUserCounter.decrementAndGet();
    }

}
