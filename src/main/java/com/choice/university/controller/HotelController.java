package com.choice.university.controller;

import com.choice.university.dto.AddHotelAmenities;
import com.choice.university.dto.CreateHotel;
import com.choice.university.dto.Hotel;
import com.choice.university.dto.RemoveHotelAmenities;
import com.choice.university.soap.UniversitySoapApiClient;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotels")
public class HotelController {

  private final UniversitySoapApiClient soapClient;

  public HotelController(UniversitySoapApiClient soapClient) {
    this.soapClient = soapClient;
  }

  @GetMapping("/{id}")
  public Hotel getHotelById(@PathVariable Long id) {
    return soapClient.getHotelById(id).getHotel();
  }

  @GetMapping
  public List<Hotel> getHotelsByName(@RequestParam String name,
      @RequestParam(required = false) Integer pageNumber,
      @RequestParam(required = false) Integer pageSize) {
    return soapClient.getHotelsByName(name, pageNumber, pageSize).getHotels().getHotel();
  }

  @PostMapping
  public Hotel createHotel(@RequestBody CreateHotel hotel) {
    return soapClient.createHotel(hotel).getHotel();
  }

  @DeleteMapping("/{id}")
  public void deleteHotel(@PathVariable Long id) {
    soapClient.deleteHotel(id);
  }

  @PatchMapping("/{id}/amenities/add")
  public Hotel addHotelAmenities(@PathVariable Long id,
      @RequestBody AddHotelAmenities amenities) {
    amenities.setHotelId(id);

    return soapClient.addHotelAmenities(amenities).getHotel();
  }

  @PatchMapping("/{id}/amenities/remove")
  public Hotel removeHotelAmenities(@PathVariable Long id,
      @RequestBody RemoveHotelAmenities amenities) {
    amenities.setHotelId(id);

    return soapClient.removeHotelAmenities(amenities).getHotel();
  }
}