package com.webappbackend.apiModels.Responses;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webappbackend.serilizzatori.SerilizzatoreByte;
import io.jsonwebtoken.io.Encoders;

import java.util.Arrays;
import java.util.stream.Stream;

public class ImageResponse {
    @JsonSerialize(using = SerilizzatoreByte.class)
    byte[] image;

    String nome;

    public ImageResponse(byte[] image) {
        this.image =image;
        nome = "aa";
    }
}
