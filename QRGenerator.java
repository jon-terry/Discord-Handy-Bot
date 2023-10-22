package org.example;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.*;
import java.io.IOException;



public class QRGenerator {

    public static byte[] generateAndSendQRCode(String data) throws IOException {

        String apiURL = "https://api.qrserver.com/v1/create-qr-code/?data=" + data + "&size=250x250";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiURL)
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            // Return bytes
            return response.body().bytes();

        } else {
            throw new IOException("Failed to generate QR code");
        }
    }

}
