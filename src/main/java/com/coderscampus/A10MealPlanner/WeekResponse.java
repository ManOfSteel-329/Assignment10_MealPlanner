package com.coderscampus.A10MealPlanner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeekResponse {
    // week (Week) -> 7 weekdays' meal plan (Day) -> list of meals (Meal[], a meal has double ...) and nutrients (double)

    @JsonProperty("week")
    private Week week;

    public static class Week {
        @JsonProperty("monday")
        private Day monday;
        @JsonProperty("tuesday")
        private Day tuesday;
        @JsonProperty("wednesday")
        private Day wednesday;
        @JsonProperty("thursday")
        private Day thursday;
        @JsonProperty("friday")
        private Day friday;
        @JsonProperty("saturday")
        private Day saturday;
        @JsonProperty("sunday")
        private Day sunday;
    }

    public static class Day {
        @JsonProperty("meals")
        private DayResponse.Meal[] meals;
        @JsonProperty("nutrients")
        private DayResponse.Nutrients nutrients;

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
}

