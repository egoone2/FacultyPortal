package ru.osokin.portalfbi.util;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Utils {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
}
