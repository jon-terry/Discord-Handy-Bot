package org.handybot;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.*;

import static java.lang.System.currentTimeMillis;


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

        } else if (message.startsWith("!flip")) {
            long time = currentTimeMillis();
            if (time % 2 == 0) {
                event.getChannel().sendMessage("Heads").queue();
            } else {
                event.getChannel().sendMessage("Tails").queue();
            }


        }
    }

    }

