package org.example;



import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.*;
import java.io.IOException;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class MyEventListener extends ListenerAdapter {
    private Random random = new Random();



    // Example
    @Override

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return; // Ignore messages from bots

        String message = event.getMessage().getContentRaw();
        //

        if (message.equalsIgnoreCase("!hello")) {
            int minValue = 0;
            int maxValue = 5;
            int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;

            switch (randomNumber) {
                case 0:
                    event.getChannel().sendMessage("How's it going " + event.getAuthor().getName() + "?").queue();
                    break;
                case 1:
                    event.getChannel().sendMessage("What's up, " + event.getAuthor().getName() + "?").queue();
                    break;
                case 2:
                    event.getChannel().sendMessage("Hello, " + event.getAuthor().getName() + "!").queue();
                    break;
                case 3:
                    event.getChannel().sendMessage("Hey there, " + event.getAuthor().getName() + "!").queue();
                    break;
                case 4:
                    event.getChannel().sendMessage("Greetings, " + event.getAuthor().getName() + "!").queue();
                    break;
                }

            } else if (message.startsWith("!generateQR")) {
                // Extract data from message
                String[] parts = message.split(" ", 2);
                if (parts.length == 2) {
                    String data = parts[1];

                    try {
                        // Scrape QR code image URL
                        String imageURL = scrapeQRCodeImageUrl(data);

                        if (imageURL != null) {
                            // Send message with QR image
                            event.getChannel().sendMessage("QR code for: " + data).addFile(Jsoup.connect(imageUrl)
                                    .ignoreContentType(true).execute().bodyStream(), "qrcode.png").queue();
                        } else {
                            event.getChannel().sendMessage("QR code not found for " + data).queue();

                        }

                    } catch (Exception e) {
                        event.getChannel().sendMessage("Failed to generate QR code: " + e.getMessage()).queue();
                    }

                } else {
                    event.getChannel().sendMessage("Usage: !generateQR <data>").queue();
                }
            }
        }

        private String scrapeQRCodeImageUrl(String data) throws IOException {
            String url = "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=" + data;

            Document document = Jsoup.connect(url).get();
            Elements imgElements = document.select("img[src$=.png");

            if (!imgElements.isEmpty()) {
                Element imgElement = imgElements.first();
                return imgElements.absUrl("src");
            }

            return null; // QR not found
        }


    }

