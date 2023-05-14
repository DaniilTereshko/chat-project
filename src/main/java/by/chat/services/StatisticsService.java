package by.chat.services;

import by.chat.services.api.IStatisticsService;

import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsService implements IStatisticsService {
    private AtomicInteger activeUserCounter;

    public StatisticsService() {
        this.activeUserCounter.set(0);
    }

    @Override
    public Integer getCountActiveUsers() {
        return activeUserCounter.get();
    }

    @Override
    public Integer addCountActiveUser() {
        return activeUserCounter.incrementAndGet();
    }

    @Override
    public Integer decCountActiveUser() {
        return activeUserCounter.decrementAndGet();
    }

    //тут должен быть реализован метод получения кол-ва зарегистрированных юзеров, но это не точно
}
