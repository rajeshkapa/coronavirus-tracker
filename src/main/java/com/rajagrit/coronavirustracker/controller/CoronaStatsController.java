package com.rajagrit.coronavirustracker.controller;

import com.rajagrit.coronavirustracker.models.LocationStats;
import com.rajagrit.coronavirustracker.models.Paged;
import com.rajagrit.coronavirustracker.models.Paging;
import com.rajagrit.coronavirustracker.repositories.LocationStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CoronaStatsController {

    @Autowired
    LocationStatsRepository locationStatsRepository;

    @GetMapping("/coronastats")
    public String home( @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(value = "size", required = false, defaultValue = "30") int size, Model model ){
        model.addAttribute("posts", getPage(pageNumber, size));
        return "home";
    }


    public Paged<LocationStats> getPage( int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LocationStats> postPage = locationStatsRepository.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }

}
