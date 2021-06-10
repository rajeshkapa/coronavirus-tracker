package com.rajagrit.coronavirustracker.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name="coronastats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationStats implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

     @SerializedName ("Active Cases_text")
     private String active_cases_text;
     @SerializedName ("Country_text")
     private String country_text;
     @SerializedName ("Last Update")
     private String last_update;
     @SerializedName ("New Cases_text")
     private String new_cases_text;
     @SerializedName ("New Deaths_text")
     private String new_death_text;
     @SerializedName ("Total Cases_text")
     private String total_cases_text;
     @SerializedName ("Total Deaths_text")
     private String total_deaths_text;
     @SerializedName ("Total Recovered_text")
     private String total_recover_text;


}
