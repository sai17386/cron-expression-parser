package com.cron.parser;

public enum CronParserFieldType {
    MINUTES(0, 59), HOURS(0, 23), DAY_OF_MONTH(1, 31), MONTH(1, 12), DAY_OF_WEEK(1, 7);

    public final int max;
    public final int min;

    CronParserFieldType(int min, int max) {
        this.min = min;
        this.max = max;
    }

}
