package com.coderscampus.A10MealPlanner;


import com.fasterxml.jackson.annotation.JsonProperty;

public class DayResponse {
    @JsonProperty("meals")
    private Meal[] meals;
    @JsonProperty("nutrients")
    private Nutrients nutrients;

    public static class Meal {
        @JsonProperty("id")
        private int id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("imageType")
        private String imageType;
        @JsonProperty("readyInMinutes")
        private int readyInMinutes;
        @JsonProperty("servings")
        private int servings;
        @JsonProperty("sourceUrl")
        private String sourceUrl;
    }

    public static class Nutrients {
        @JsonProperty("calories")
        private double calories;
        @JsonProperty("carbohydrates")
        private double carbohydrates;
        @JsonProperty("fat")
        private double fat;
        @JsonProperty("protein")
        private double protein;
    }
}
