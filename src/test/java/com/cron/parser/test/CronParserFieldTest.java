package com.cron.parser.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.cron.parser.CronParserField;
import com.cron.parser.CronParserFieldType;
import com.cron.parser.InvalidCronParserFieldException;
import org.junit.jupiter.api.Test;

public class CronParserFieldTest {

    /**
     * Test valid range expressions (E.g. 1-5, 0-3, etc. )
     */
    @Test
    public void correctRange() throws InvalidCronParserFieldException {
        CronParserField f = new CronParserField("1-5", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5", f.toString());
        f = new CronParserField("1-1", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1", f.toString());
        f = new CronParserField("1-2", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 2", f.toString());
        f = new CronParserField("1-15", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", f.toString());


        f = new CronParserField("0-1", CronParserFieldType.HOURS);
        assertEquals("0 1", f.toString());
        f = new CronParserField("0-3", CronParserFieldType.HOURS);
        assertEquals("0 1 2 3", f.toString());
        f = new CronParserField("0", CronParserFieldType.HOURS);
        assertEquals("0", f.toString());
        f = new CronParserField("23", CronParserFieldType.HOURS);
        assertEquals("23", f.toString());
    }

    /**
     * Test invalid range expressions (E.g. 1-100 for month, 0-3 for year, etc. )
     */
    @Test
    public void incorrectRange() {
        tryParse("0-5", CronParserFieldType.DAY_OF_MONTH, "outside valid range");
        tryParse("0-5-6", CronParserFieldType.DAY_OF_MONTH, "Invalid number");
        tryParse("1-32", CronParserFieldType.DAY_OF_MONTH, "outside valid range");
        tryParse("1-0", CronParserFieldType.DAY_OF_MONTH, "ends before it starts");
    }


    /**
     * Test correct fixed value cron field ( e.g. 1,3,4 or 15,16 )
     */
    @Test
    public void correctFixedValues() throws InvalidCronParserFieldException {
        CronParserField f = new CronParserField("1", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1", f.toString());
        f = new CronParserField("1,2,3,4,5", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5", f.toString());
        f = new CronParserField("1,1,1", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1", f.toString());
        f = new CronParserField("1,2", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 2", f.toString());
        f = new CronParserField("2,1,3,5,6,7,4", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 2 3 4 5 6 7", f.toString());


        f = new CronParserField("0,1", CronParserFieldType.HOURS);
        assertEquals("0 1", f.toString());
        f = new CronParserField("0,1,2,3", CronParserFieldType.HOURS);
        assertEquals("0 1 2 3", f.toString());
        f = new CronParserField("0", CronParserFieldType.HOURS);
        assertEquals("0", f.toString());
        f = new CronParserField("0,0", CronParserFieldType.HOURS);
        assertEquals("0", f.toString());
        f = new CronParserField("23,0", CronParserFieldType.HOURS);
        assertEquals("0 23", f.toString());
    }

    /**
     * Test incorrect fixed value cron field ( e.g. 1,3,100 for MONTH or 15,16 for DAY_OF_WEEK )
     */
    @Test
    public void incorrectFixedValues() {
        tryParse("0,5", CronParserFieldType.DAY_OF_MONTH, "outside valid range");
        tryParse("1,32", CronParserFieldType.DAY_OF_MONTH, "outside valid range");
    }

    @Test
    public void incorrectFixedValue() {
        tryParse("100", CronParserFieldType.DAY_OF_MONTH, "outside valid range");
    }

    /**
     * Test correct interval expressions ( e.g. * , *\/15 , *\/20 )
     */
    @Test
    public void correctIntervals() throws InvalidCronParserFieldException {
        CronParserField f = new CronParserField("*/10", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 11 21 31", f.toString());
        f = new CronParserField("*/20", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 21", f.toString());
        f = new CronParserField("*/30", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1 31", f.toString());
        f = new CronParserField("*/40", CronParserFieldType.DAY_OF_MONTH);
        assertEquals("1", f.toString());


        f = new CronParserField("*/10", CronParserFieldType.HOURS);
        assertEquals("0 10 20", f.toString());
        f = new CronParserField("*/15", CronParserFieldType.HOURS);
        assertEquals("0 15", f.toString());
        f = new CronParserField("*/20", CronParserFieldType.HOURS);
        assertEquals("0 20", f.toString());
        f = new CronParserField("*/23", CronParserFieldType.HOURS);
        assertEquals("0 23", f.toString());
        f = new CronParserField("*/24", CronParserFieldType.HOURS);
        assertEquals("0", f.toString());

        f = new CronParserField("*", CronParserFieldType.DAY_OF_WEEK);
        assertEquals("1 2 3 4 5 6 7", f.toString());

        f = new CronParserField("*", CronParserFieldType.MONTH);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", f.toString());
    }

    @Test
    public void incorrectIntervals() {
        tryParse("*/0", CronParserFieldType.DAY_OF_MONTH, "interval is 0");
        tryParse("*/10/10", CronParserFieldType.DAY_OF_MONTH, "has too many intervals");
        tryParse("*/A", CronParserFieldType.DAY_OF_MONTH, "Invalid number 'A'");
        tryParse("A/A", CronParserFieldType.DAY_OF_MONTH, "Invalid number 'A/A'");
        tryParse("0/0", CronParserFieldType.DAY_OF_MONTH, "Invalid number '0/0'");
        tryParse("0/15", CronParserFieldType.DAY_OF_MONTH, "Invalid number '0/15'");
    }

    private void tryParse(String incomingText, CronParserFieldType fieldType, String msg) {
        try {
            new CronParserField(incomingText, fieldType);
            fail(incomingText + " should not be a valid " + fieldType);
        } catch (InvalidCronParserFieldException e) {
            assertTrue(e.getMessage().contains(msg));
            assertTrue(e.getMessage().contains(fieldType.toString()));
        }
    }


}
