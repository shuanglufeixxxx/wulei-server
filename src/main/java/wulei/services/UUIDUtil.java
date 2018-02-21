package wulei.services;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    private static UUIDUtil instance = new UUIDUtil();

    public static UUIDUtil getInstance() {
        return instance;
    }

    private UUIDUtil() {}

    private Random random = new Random();

    public String generateId() {
        return new UUID( Calendar.getInstance().getTimeInMillis(), random.nextLong() ).toString();
    }
}