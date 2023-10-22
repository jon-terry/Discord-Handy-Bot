package org.example;

import net.dv8tion.jda.api.*;

public class App {

    public static void main(String[] args) {


        String token = "MTE1NjA2NjQ3MTQ4MzU1OTkzNg.GCrNOp.KDRDhiDU4SVV-ecHciViZBbff_ILoDCU017ZBQ";

        try {
            // remove token, place in config.properties file
            JDABuilder.createDefault(token)
                    .addEventListeners(new MyEventListener()) // Event listener class
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


