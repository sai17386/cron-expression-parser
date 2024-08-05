package com.cron.parser;

import static java.lang.String.format;


/**
 * A CronExpression is a representation of a cron string with all members:
 * [minute] [hour] [day of month] [day of week] [command]
 *
 * It will validate basic syntax and delegate calculation of each member of the cron string (month, year ) to CronField,
 * which in turn will calculate the relevant exploded times (e.g. 1-3 will be 1,2,3 ) .
 *
 */
public class CronParserExpression {

    private CronParserField minutes;
    private CronParserField hours;
    private CronParserField dayOfMonth;
    private CronParserField month;
    private CronParserField dayOfWeek;

    private String command;

    public CronParserExpression(String arg) throws InvalidCronParserFieldException {
        String[] cronMembers = arg.split("\\s+");
        if (cronMembers.length != 6) {
            throw new InvalidCronParserFieldException(
                "Expected [minute] [hour] [day of month] [day of week] [command] but got :" + arg);
        }

        minutes = new CronParserField(cronMembers[0], CronParserFieldType.MINUTES);
        hours = new CronParserField(cronMembers[1], CronParserFieldType.HOURS);
        dayOfMonth = new CronParserField(cronMembers[2], CronParserFieldType.DAY_OF_MONTH);
        month = new CronParserField(cronMembers[3], CronParserFieldType.MONTH);
        dayOfWeek = new CronParserField(cronMembers[4], CronParserFieldType.DAY_OF_WEEK);
        command = cronMembers[5];

    }

    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append(format("%-14s%s\n", "minute", minutes.toString()));
        b.append(format("%-14s%s\n", "hour", hours.toString()));
        b.append(format("%-14s%s\n", "day of month", dayOfMonth.toString()));
        b.append(format("%-14s%s\n", "month", month.toString()));
        b.append(format("%-14s%s\n", "day of week", dayOfWeek.toString()));
        b.append(format("%-14s%s\n", "command", command));
        return b.toString();
    }

}
