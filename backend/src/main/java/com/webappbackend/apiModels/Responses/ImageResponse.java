package com.webappbackend.apiModels.Responses;

import java.util.Arrays;
import java.util.stream.Stream;

public class ImageResponse {
    Byte[] image;

    String nome;

    public ImageResponse(byte[] image) {
        this.image = new Byte[image.length];
        for (int i = 0; i < image.length; i++) {
            this.image[i] = image[i];
        }
        nome = "aa";
    }
}
