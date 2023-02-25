package com.moonsworkshop.vexgot;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Formats durations to a readable form
 */
public enum DurationFormatter {
    LONG(false, Integer.MAX_VALUE),
    CONCISE(true, Integer.MAX_VALUE),
    CONCISE_LOW_ACCURACY(true, 3);

    private static final Unit[] UNITS = new Unit[]{
            new Unit(ChronoUnit.YEARS),
            new Unit(ChronoUnit.MONTHS),
            new Unit(ChronoUnit.WEEKS),
            new Unit(ChronoUnit.DAYS),
            new Unit(ChronoUnit.HOURS),
            new Unit(ChronoUnit.MINUTES),
            new Unit(ChronoUnit.SECONDS)
    };

    private final int accuracy;
    private final boolean concise;

    DurationFormatter(boolean concise, int accuracy) {
        this.concise = concise;
        this.accuracy = accuracy;
    }

    /**
     * Convenience method for accessing {@link #format(Duration, boolean, int)}
     * through existing enumeration implementations.
     *
     * @param duration the duration
     * @return the formatted string
     */
    public String format(Duration duration) {
        return format(duration, concise, accuracy);
    }

    /**
     * Formats {@code duration} as a string, either in a {@code concise} (1 letter)
     * or full length format, displaying up to the specified number of {@code elements}.
     *
     * @param duration the duration
     * @param concise  if the output should be concisely formatted
     * @param elements the maximum number of elements to display
     * @return the formatted string
     */
    public static String format(Duration duration, boolean concise, int elements) {
        long seconds = duration.getSeconds();
        StringBuilder output = new StringBuilder();
        int outputSize = 0;

        for (Unit unit : UNITS) {
            long n = seconds / unit.duration;
            if (n > 0) {
                seconds -= unit.duration * n;
                output.append(' ').append(n).append(unit.toString(concise, n));
                outputSize++;
            }
            if (seconds <= 0 || outputSize >= elements) {
                break;
            }
        }

        if (output.length() == 0) {
            return "0" + (UNITS[UNITS.length - 1].toString(concise, 0));
        }
        return output.substring(1);
    }

    /**
     * Formats {@code duration} as a string, either in a {@code concise} (1 letter)
     * or full length format, displaying all possible elements.
     *
     * @param duration the duration
     * @param concise  if the output should be concisely formatted
     * @return the formatted string
     */
    public static String format(Duration duration, boolean concise) {
        return format(duration, concise, Integer.MAX_VALUE);
    }

    private static final class Unit {
        private final long duration;
        private final String formalStringPlural, formalStringSingular;
        private final String conciseString;

        Unit(ChronoUnit unit) {
            this.duration = unit.getDuration().getSeconds();
            this.formalStringPlural = " " + unit.name().toLowerCase();
            this.formalStringSingular = " " + unit.name().substring(0, unit.name().length() - 1).toLowerCase();
            this.conciseString = String.valueOf(Character.toLowerCase(unit.name().charAt(0)));
        }

        public String toString(boolean concise, long n) {
            if (concise) {
                return this.conciseString;
            }
            return n == 1 ? this.formalStringSingular : this.formalStringPlural;
        }
    }
}