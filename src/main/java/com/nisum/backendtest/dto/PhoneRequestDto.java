package com.nisum.backendtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
public class PhoneRequestDto {


    @JsonProperty("countrycode")
    private Integer countryCode;
    @JsonProperty("citycode")
    private Integer cityCode;
    @JsonProperty("number")
    private Integer number;
}
