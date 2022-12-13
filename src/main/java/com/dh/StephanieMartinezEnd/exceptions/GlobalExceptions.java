package com.dh.StephanieMartinezEnd.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
//Asesor de Excepciones
public class GlobalExceptions {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptions.class);
//    Lo siguiente es hacer un manejador de la exccepcion y este seria como un mapping del error
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamientoResourceNotFoundException(ResourceNotFoundException rnfe){
        LOGGER.error("Se esta presentando un error: " + rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamientoBadRequestException(BadRequestException rnfe){
        LOGGER.error("Se esta presentando un error: " + rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rnfe.getMessage());
    }
}
