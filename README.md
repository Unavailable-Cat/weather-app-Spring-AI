# Weather App — Spring AI

A Spring Boot application demonstrating how to build an AI-powered assistant using **Spring AI, Gemini, and custom Java tools**.

The application allows the AI to decide when to call backend tools based on the user's query.

## Features

* 🤖 **Gemini AI integration** using Spring AI
* 🌤️ **Weather Tool** using the WeatherAPI
* 🕐 **Date & Time Tool** using Java's `LocaleContextHolder` and `ZoneId`
* 🔧 **Spring AI Tool Calling** — Gemini can decide when a custom Java method should be invoked
* 🔐 API keys managed through environment variables
* 🌐 REST endpoints for interacting with the AI assistant and weather service

## Tools

### WeatherTool

A Spring-managed service that exposes a method to Spring AI using the `@Tool` annotation.

```java
@Tool(description = "fetches the weather information for a given location")
public String getWeatherInfo(
        @ToolParam(description = "location") String location) {
    // Calls WeatherAPI
}
```

The tool retrieves current weather information for a requested location.

### DateAndTimeTool

Provides the current date and time using the timezone associated with the current request context.

```java
@Tool(description = "Get the current date and time of my local area")
public String getCurrentDateAndTime() {
    return LocalDateTime.now()
            .atZone(LocaleContextHolder.getTimeZone().toZoneId())
            .toString();
}
```

## How Tool Calling Works

The user sends a natural-language query to the `/bot` endpoint:

```text
GET /bot?q=What's the weather in New York?
```

The flow is:

```text
User
  ↓
Spring Boot Controller
  ↓
Spring AI ChatClient
  ↓
Gemini
  ↓
Decides whether a tool is required
  ↓
WeatherTool / DateAndTimeTool
  ↓
External API or Java logic
  ↓
Tool result returned to Gemini
  ↓
Natural-language response
```

The application passes the Spring-managed `WeatherTool` bean to the `ChatClient`:

```java
.tools(new DateAndTimeTool(), weatherTool)
```

This is important because `WeatherTool` contains Spring-injected configuration such as the WeatherAPI key.

## Configuration

API keys are kept outside the source code using environment variables:

```yaml
spring:
  application:
    name: tools

  ai:
    google:
      genai:
        chat:
          model: gemini-3.8-flash
        api-key: ${GOOGLE_GENAI_API_KEY}

Weather:
  Api:
    Key: ${Weather_Api_Key}
```

Required environment variables:

```text
GOOGLE_GENAI_API_KEY=your_gemini_api_key
Weather_Api_Key=your_weatherapi_key
```

## Endpoints

### AI Assistant

```text
GET /bot?q=<your-query>
```

Example:

```text
/bot?q=What is the weather in Indore?
```

### Direct Weather API

```text
GET /weather?location=<location>
```

Example:

```text
/weather?location=Delhi
```

## Tech Stack

* Java
* Spring Boot
* Spring AI
* Google Gemini
* WeatherAPI
* REST APIs
* RestTemplate
* Maven
