package com.cron.parser;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;


/**
 * A CronExpression is a representation of a cron string with all members:
 * [minute] [hour] [day of month] [month] [day of week] [command]
 * <p>
 * It will validate basic syntax and delegate calculation of each member of the cron string (month, year ) to CronField,
 * which in turn will calculate the relevant exploded times (e.g. 1-3 will be 1,2,3 ) .
 */
public class CronParserExpression {

    private List<CronParserField> fields;

    private String command;

    public CronParserExpression(String arg) throws InvalidCronParserFieldException {
        String[] cronMembers = arg.split("\\s+");
        if (cronMembers.length != 6) {
            throw new InvalidCronParserFieldException(
                "Expected [minute] [hour] [day of month] [month] [day of week] [command] but got :" + arg);
        }
        fields = new ArrayList<>();
        for (CronParserFieldType type : CronParserFieldType.values()) {
            fields.add(new CronParserField(cronMembers[type.ordinal()], type));
        }
        command = cronMembers[cronMembers.length - 1];

    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        int i = 0;
        for (CronParserFieldType type : CronParserFieldType.values()) {
            output.append(format("%-14s%s\n", type.description, fields.get(i).toString()));
            i++;
        }
        output.append(format("%-14s%s\n", "command", command));
        return output.toString();
    }

}
