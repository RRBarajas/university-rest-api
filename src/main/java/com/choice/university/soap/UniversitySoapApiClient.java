package com.choice.university.soap;

import com.choice.university.dto.GetAmenitiesRequest;
import com.choice.university.dto.GetAmenitiesResponse;
import com.choice.university.dto.GetAmenityRequest;
import com.choice.university.dto.GetAmenityResponse;
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
}