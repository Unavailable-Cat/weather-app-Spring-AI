package com.springAI.tools.Tools;

import org.apache.http.protocol.HTTP;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
@Service
public class WeatherTool {

    private String baseUrl="https://api.weatherapi.com/v1";

    @Value("${Weather.Api.Key}")
    private String key;

    @Tool(description = "fetches the weather information for a given location")
    public String getWeatherInfo(@ToolParam(description = "location") String location) {
        System.out.println("Fetching weather information for location: " + location);
        String url=baseUrl + "/current.json?key=" + key + "&q=" + location;
        RestTemplate restTemplate=new RestTemplate();
        System.out.println(url);
        Map<String, Object> response = restTemplate.getForObject(url,Map.class);
        System.out.println(response);
        return response.toString();
    }
}
