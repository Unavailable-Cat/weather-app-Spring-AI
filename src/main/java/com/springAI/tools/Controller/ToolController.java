package com.springAI.tools.Controller;

import com.springAI.tools.Tools.DateAndTimeTool;
import com.springAI.tools.Tools.WeatherTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToolController {

    @Autowired
    WeatherTool weatherTool;



    private final ChatClient client;

    public ToolController(ChatClient.Builder builder) {
        this.client = builder.build();
    }

    @GetMapping("bot")
    public String getBotHelp(@RequestParam(value = "q") String query) {
        return client.prompt()
                .user(query)
                .tools(new DateAndTimeTool(),weatherTool)
                .call()
                .content();
    }

    @GetMapping("weather")
    public String getWeatherInfo(@RequestParam(value = "location") String location) {
        return weatherTool.getWeatherInfo(location);
    }
}
