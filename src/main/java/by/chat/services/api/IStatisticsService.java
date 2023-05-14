package by.chat.services.api;

public interface IStatisticsService {
    public Integer getCountActiveUsers();
    public Integer addCountActiveUser();

    public Integer decCountActiveUser();

    //тут должно быть описание метода получения кол-ва зарегистрированных юзеров, но это не точно
}
