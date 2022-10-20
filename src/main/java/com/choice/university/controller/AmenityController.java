package com.choice.university.controller;

import com.choice.university.dto.Amenity;
import com.choice.university.soap.UniversitySoapApiClient;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("amenities")
public class AmenityController {

  private final UniversitySoapApiClient soapClient;

  public AmenityController(UniversitySoapApiClient soapClient) {
    this.soapClient = soapClient;
  }

  @GetMapping("/{id}")
  public Amenity getAmenity(@PathVariable Long id) {
    return soapClient.getAmenity(id).getAmenity();
  }

  @GetMapping
  public List<Amenity> getAllAmenities() {
    return soapClient.getAllAmenities().getAmenities().getAmenity();
  }
}