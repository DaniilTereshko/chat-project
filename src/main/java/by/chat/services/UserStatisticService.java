package by.chat.services;

import by.chat.services.api.IUserService;
import by.chat.services.api.IUserStatisticsService;

public class UserStatisticService implements IUserStatisticsService {
    private final IUserService userService;

    public UserStatisticService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Integer getCountActiveUsers() {
        return null;
    }

    @Override
    public Integer getCountUsers() {
        return userService.get().size();
    }
}
