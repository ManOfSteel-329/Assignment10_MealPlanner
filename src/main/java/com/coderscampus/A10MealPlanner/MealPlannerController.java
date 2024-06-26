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

    // http://localhost:8080/mealplanner/day?numCalories=2000&diet=vegetarian&exclusions=shellfish
    @GetMapping("mealplanner/day")
    public ResponseEntity<DayResponse> getDayMeals(@RequestParam String numCalories, @RequestParam String diet, @RequestParam String exclusions) {
        String url = String.format("%s%s?timeFrame=day&targetCalories=%s&diet=%s&exclude=%s&apiKey=%s", baseUrl, mealPlanEndPoint, numCalories, diet, exclusions, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        DayResponse response = restTemplate.getForObject(url, DayResponse.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("mealplanner/week")
    public ResponseEntity<WeekResponse> getWeekMeals(@RequestParam String numCalories,@RequestParam(required = false, defaultValue = "Gluten Free") String diet,@RequestParam String exclusions){
        String url = String.format("%s%s?timeFrame=week&targetCalories=%s&diet=%s&exclude=%s&apiKey=%s", baseUrl, mealPlanEndPoint, numCalories, diet, exclusions, apiKey);
        RestTemplate rt = new RestTemplate();
        WeekResponse response = rt.getForObject(url, WeekResponse.class);
        return ResponseEntity.ok(response);
    }
}