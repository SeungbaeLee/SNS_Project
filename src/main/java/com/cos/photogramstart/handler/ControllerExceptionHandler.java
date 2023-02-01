package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.utill.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({CustomValidationException.class})
    public String validationException(CustomValidationException e){

        //CMRespDTO, Script 비교
        //1. 클라이언트에게 응답할 때는 Script 좋음
        //2. Ajax통신 - CMRestDto
        //3. Android 통신 - CMRespDto
        return Script.back(e.getErrorMap().toString());
//        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
    }

    @ExceptionHandler({CustomValidationApiException.class})
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){

        return new ResponseEntity<>(new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }
}
