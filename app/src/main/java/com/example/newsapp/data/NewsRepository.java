package com.example.newsapp.data;

import android.content.Context;
import android.util.Log;

import com.example.newsapp.R;
import com.example.newsapp.models.NewsItem;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

public class NewsRepository {

    private final NewsDao newsDao;

    public NewsRepository(Context context) {
        NewsDatabase db = NewsDatabase.getInstance(context);
        newsDao = db.newsDao();
    }

    public void seedDataIfEmpty(Runnable onComplete) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                if (newsDao.getAllNews().isEmpty()) {
                    newsDao.insertAll(Arrays.asList(
                            new NewsItem("Global Leaders Meet at Climate Summit",
                                    "World leaders gather to discuss urgent climate policies and global cooperation.",
                                    R.drawable.story1, "top"),

                            new NewsItem("Tech Giants Unveil AI Innovations",
                                    "Major tech companies showcase breakthrough AI products at annual developer conference.",
                                    R.drawable.story2, "top"),

                            new NewsItem("Stock Market Sees Sharp Turnaround",
                                    "Global markets rebound after economic forecasts show signs of recovery.",
                                    R.drawable.story3, "top"),

                            new NewsItem("NASA Confirms Water on Moon Surface",
                                    "Scientists confirm the presence of water molecules in sunlit areas of the Moon.",
                                    R.drawable.story4, "top"),

                            new NewsItem("Historic Peace Agreement Signed",
                                    "Two nations sign a landmark treaty ending decades of political tension.",
                                    R.drawable.story5, "top"),
                            new NewsItem("Local Schools Introduce AI Curriculum",
                                    "Students as young as 10 are now learning about AI and machine learning basics.",
                                    R.drawable.news1, "news"),

                            new NewsItem("Public Transport Fares Cut by 30%",
                                    "Government announces reduced fare scheme to boost public transport usage.",
                                    R.drawable.news2, "news"),

                            new NewsItem("Farmers Celebrate Record Harvest",
                                    "Thanks to good weather, farmers report the highest crop yield in a decade.",
                                    R.drawable.news3, "news"),

                            new NewsItem("Electric Vehicles Gain Momentum",
                                    "New survey reveals 60% of consumers plan to buy electric in the next 5 years.",
                                    R.drawable.news4, "news"),

                            new NewsItem("City to Plant One Million Trees",
                                    "Urban reforestation program aims to improve air quality and reduce heat.",
                                    R.drawable.news5, "news"),
                            new NewsItem("Climate Change Impact on Coral Reefs",
                                    "Rising ocean temperatures continue to damage delicate reef ecosystems.",
                                    R.drawable.related1, "related"),

                            new NewsItem("AI's Role in Healthcare Diagnostics",
                                    "AI-driven tools improve early detection of diseases by 35%, study shows.",
                                    R.drawable.related2, "related"),

                            new NewsItem("Tech Startups Fuel Job Growth",
                                    "Emerging startups in fintech and green tech are hiring rapidly in urban centers.",
                                    R.drawable.related3, "related"),

                            new NewsItem("New Renewable Energy Targets Set",
                                    "Government pledges to shift 70% of power grid to renewables by 2030.",
                                    R.drawable.related4, "related"),

                            new NewsItem("Education Reforms Focus on STEM",
                                    "Curriculum updates prioritize science, tech, engineering, and math nationwide.",
                                    R.drawable.related5, "related")
                            ));
                }
            } finally {
                onComplete.run();
            }
        });
    }


    public void getNewsByCategory(String category, NewsCallback callback) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                List<NewsItem> list = newsDao.getNewsByCategory(category);
                callback.onResult(list);
            } catch (Exception e) {
                Log.e("NewsRepository", "Error fetching category: " + category, e);
                callback.onError(e);
            }
        });
    }


    public void getAllNews(NewsCallback callback) {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<NewsItem> list = newsDao.getAllNews();
            callback.onResult(list);
        });
    }

    public interface NewsCallback {
        void onResult(List<NewsItem> list);
        void onError(Exception e);
    }
}
