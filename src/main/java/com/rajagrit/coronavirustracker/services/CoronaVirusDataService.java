package com.rajagrit.coronavirustracker.services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rajagrit.coronavirustracker.models.LocationStats;
import com.rajagrit.coronavirustracker.repositories.LocationStatsRepository;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Service
public class CoronaVirusDataService {

    private static final String VIRUS_DATA_URL = "https://covid-19-tracking.p.rapidapi.com/v1";
    private static final String RAPID_API_KEY = "wvCLXxj24tmshoysjXhWB6BO2ORZp17VYEAjsnFbjt3byfjJQr";
    private static final String RAPID_API_HOST = "covid-19-tracking.p.rapidapi.com";
    String statsJson = "";
    Gson gson = new Gson();
    Type statsListType = new TypeToken<ArrayList<LocationStats>> (){}.getType();

    @Autowired
    LocationStatsRepository locationStatsRepository;

    @PostConstruct
    @Scheduled(cron = " * * * 1 * * ")
    public void fetchVirusData() throws IOException {

        AsyncHttpClient client = new DefaultAsyncHttpClient ();
        client.prepare("GET", VIRUS_DATA_URL)
                .setHeader("x-rapidapi-key", RAPID_API_KEY)
                .setHeader("x-rapidapi-host", RAPID_API_HOST)
                .execute()
                .toCompletableFuture()
                .thenAccept(response -> statsJson = response.getResponseBody () )
                .join();
        client.close();

        ArrayList<LocationStats> statsArray = gson.fromJson(statsJson, statsListType);
        locationStatsRepository.saveAll ( statsArray );

    }
}
