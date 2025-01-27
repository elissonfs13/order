package com.elissonfs.order.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public OrderException(String message) {
    super(message);
  }

}