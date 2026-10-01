package com.springAI.tools.Tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.ZoneId;

public class DateAndTimeTool {

    @Tool(description = "Get the current date and time of my local area")
    public String getCurrentDateAndTime() {
        System.out.println("local Date and Time Called");
        return java.time.LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

}
