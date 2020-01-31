package com.k15t.pat;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MaskingConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent e) {
        String message = e.getFormattedMessage();
        message = message.replaceAll("password=\'.+?\'", "password=*****");
        return message;
    }

}
