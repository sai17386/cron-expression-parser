package com.cron.parser;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        if (args.length != 1) {
            StringBuilder message = new StringBuilder();
            message.append("Expected [");
            for (CronParserFieldType type : CronParserFieldType.values()) {
                message.append(type.description).append("] [");
            }
            message.append("command] but got :").append(Arrays.toString(args));
            System.err.println(message);
            return;
        }

        try {

            CronParserExpression expr = new CronParserExpression(args[0]);
            System.out.println(expr);

        } catch (InvalidCronParserFieldException invalidCronExpression) {
            System.err.println(invalidCronExpression.getMessage());
        }
    }
}