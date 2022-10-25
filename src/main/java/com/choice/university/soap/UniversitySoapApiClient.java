package com.choice.university.soap;

import com.choice.university.dto.AddHotelAmenities;
import com.choice.university.dto.CreateHotel;
import com.choice.university.dto.DeleteHotel;
import com.choice.university.dto.GetAmenitiesRequest;
import com.choice.university.dto.GetAmenitiesResponse;
import com.choice.university.dto.GetAmenityRequest;
import com.choice.university.dto.GetAmenityResponse;
import com.choice.university.dto.GetHotelById;
import com.choice.university.dto.GetHotelResponse;
import com.choice.university.dto.GetHotelsByName;
import com.choice.university.dto.GetHotelsResponse;
import com.choice.university.dto.RemoveHotelAmenities;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class UniversitySoapApiClient extends WebServiceGatewaySupport {

  public GetAmenityResponse getAmenity(Long id) {
    var request = new GetAmenityRequest();
    request.setId(id);

    var response = getWebServiceTemplate().marshalSendAndReceive(request);
    return (GetAmenityResponse) response;
  }

  public GetAmenitiesResponse getAllAmenities() {
    var request = new GetAmenitiesRequest();

    return (GetAmenitiesResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public GetHotelResponse getHotelById(Long id) {
    var request = new GetHotelById();
    request.setId(id);

    return (GetHotelResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public GetHotelsResponse getHotelsByName(String name, Integer pageNumber, Integer pageSize) {
    var request = new GetHotelsByName();
    request.setName(name);
    request.setPageNumber(pageNumber);
    request.setPageSize(pageSize);

    return (GetHotelsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public GetHotelResponse createHotel(CreateHotel hotel) {
    return (GetHotelResponse) getWebServiceTemplate().marshalSendAndReceive(hotel);
  }

  public void deleteHotel(Long id) {
    var request = new DeleteHotel();
    request.setId(id);

    getWebServiceTemplate().marshalSendAndReceive(request);
  }

  public GetHotelResponse addHotelAmenities(AddHotelAmenities amenities) {
    return (GetHotelResponse) getWebServiceTemplate().marshalSendAndReceive(amenities);
  }

  public GetHotelResponse removeHotelAmenities(RemoveHotelAmenities amenities) {
    return (GetHotelResponse) getWebServiceTemplate().marshalSendAndReceive(amenities);
  }
}