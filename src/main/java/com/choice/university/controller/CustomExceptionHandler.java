package com.choice.university.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.soap.client.SoapFaultClientException;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleMissingParamsException(
      MissingServletRequestParameterException exception) {
    var parameterName = exception.getParameterName();
    var errorMessage = String.format("missing %s parameter", parameterName);

    return new ExceptionResponse(errorMessage);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException exception) {
    return new ExceptionResponse(exception.getMessage());
  }

  @ExceptionHandler(SoapFaultClientException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionResponse handleSoapFaultClientException(SoapFaultClientException exception) {
    return new ExceptionResponse(exception.getMessage());
  }

  @ExceptionHandler(WebServiceIOException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ExceptionResponse handleWebServiceIOException(WebServiceIOException exception) {
    var errorMessage = String.format("Error connecting to the SOAP Server - %s",
        exception.getMessage());
    return new ExceptionResponse(errorMessage);
  }
}
