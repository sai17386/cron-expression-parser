package com.cron.parser;

public enum CronParserFieldType {
    MINUTES("minute", 0, 59),
    HOURS("hour", 0, 23),
    DAY_OF_MONTH("day of month", 1, 31),
    MONTH("month", 1, 12),
    DAY_OF_WEEK("day of week", 1, 7);

    public final String description;
    public final int min;
    public final int max;


    CronParserFieldType(String description, int min, int max) {
        this.description = description;
        this.min = min;
        this.max = max;
    }

}
