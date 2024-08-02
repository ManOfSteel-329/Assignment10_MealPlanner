package com.coderscampus.A10MealPlanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MealPlannerController {
    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Value("${spoonacular.urls.base}")  // "https://api.spoonacular.com"
    private String baseUrl;

    @Value("${spoonacular.urls.mealplan}") // "/mealplanner/generate"
    private String mealPlanEndPoint;

    @GetMapping("mealplanner/day")
    public ResponseEntity<DayResponse> getDayMeals(@RequestParam(required = false) String numCalories,
                                                   @RequestParam(required = false) String diet,
                                                   @RequestParam(required = false) String exclusions) {
        String url = getUrlString("day", numCalories, diet, exclusions);
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate(); //make restful calls. send fetch data
        DayResponse response = restTemplate.getForObject(url, DayResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("mealplanner/week")
    public ResponseEntity<WeekResponse> getWeekMeals(@RequestParam(required = false) String numCalories,
                                                     @RequestParam(required = false) String diet,
                                                     @RequestParam(required = false) String exclusions){
        String url = getUrlString("week", numCalories, diet, exclusions);
        RestTemplate rt = new RestTemplate();
        WeekResponse response = rt.getForObject(url, WeekResponse.class);
        return ResponseEntity.ok(response);
    }

    private String getUrlString(String timeFrame, String numCalories, String diet, String exclusions) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl + mealPlanEndPoint + "?" + "timeFrame=" + timeFrame + "&apiKey=" + apiKey);
        if (numCalories != null && !numCalories.isEmpty()) {
            urlBuilder.append("&targetCalories=").append(numCalories);
        }
        if (diet != null && !diet.isEmpty()) {
            urlBuilder.append("&diet=").append(diet);
        }
        if (exclusions != null && !exclusions.isEmpty()) {
            urlBuilder.append("&exclude=").append(exclusions);
        }
        String url = urlBuilder.toString();
        return url;
    }
}