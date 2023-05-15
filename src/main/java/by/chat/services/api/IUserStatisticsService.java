package by.chat.services.api;

public interface IUserStatisticsService {
    Integer getCountActiveUsers();

    Integer getCountUsers();

    Integer addCountActiveUser();

    Integer decCountActiveUser();

}
