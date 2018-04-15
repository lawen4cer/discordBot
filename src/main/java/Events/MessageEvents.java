package Events;


import Admin.Purge;
import FortniteStats.Stats;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;

import java.util.List;


public class MessageEvents {
    private String strMessage;
    private String strUser;
    private String strChannel;
    private String argOne;
    private String argTwo;
    private String argThree;
    private Guild guild;


    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public String getStrMessage() {
        return strMessage;
    }

    public void setStrMessage(String strMessage) {
        this.strMessage = strMessage;
    }

    public String getStrUser() {
        return strUser;
    }

    public void setStrUser(String strUser) {
        this.strUser = strUser;
    }

    public String getStrChannel() {
        return strChannel;
    }

    public void setStrChannel(String strChannel) {
        this.strChannel = strChannel;
    }

    public String getArgOne() {
        return argOne;
    }

    public void setArgOne(String argOne) {
        this.argOne = argOne;
    }

    public String getArgTwo() {
        return argTwo;
    }

    public void setArgTwo(String argTwo) {
        this.argTwo = argTwo;
    }


    public void handleMessage(Message message, User user, MessageChannel channel, Guild guild) {
        strMessage = message.getContentRaw();
        strUser = user.getName();
        strChannel = channel.getName();
        this.guild = guild;


        if (message.getContentRaw().startsWith("!")) { //checks to see if the message starts with an !, which will be the default command invoke character on this bot
            handleCommands(message, channel, guild, user); // passes the data off to a separate function to handle commands
        }

        //commented code below used for debugging if needed
        /*System.out.println("Message is: "+ strMessage);
        System.out.println("User is: "+ strUser);
        System.out.println("Channel is: "+ strChannel);*/


    }

    private void handleCommands(Message message, MessageChannel channel, Guild guild, User user) {
        Member member = guild.getMember(user);

        String[] commandArgs = message.getContentRaw().substring(1).split(" ", 3); // splits the message into separate strings using a single white space as the separator

        int commandArgsLength = commandArgs.length;
        argOne = commandArgs[0];


        if (commandArgsLength == 1) {
            switch (argOne) { //test commands again pre-defined commands
                case "site":
                    channel.sendMessage("Check out our website -> https://www.category6esports.com/").queue();
                    break;
                case "hbird":
                    channel.sendMessage("Check out hBird's stream -> https://www.twitch.tv/hbird").queue();
                    break;
                case "mittehh":
                    channel.sendMessage("Check out mittehh's stream -> https://www.twitch.tv/mittehh").queue();
                    break;
                case "forum":
                    channel.sendMessage("Check out our forum and get involved! -> https://www.category6esports.com/forum").queue();
                    break;
                case "shop":
                    channel.sendMessage("Here, go buy some Category6 merchandise! -> https://www.category6esports.com/shop-1").queue();
                    break;
                case "twitter":
                    channel.sendMessage("Go follow us on twitter to stay up to date with all Category6 news/events -> https://twitter.com/cat6esports").queue();
                    break;
                case "5":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**FIVE MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**FIVE MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();
                    }
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;

                case "4":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**FOUR MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**FOUR MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();}
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;
                case "3":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**THREE MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**THREE MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();}
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;
                case "2":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**TWO MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**TWO MINUTES** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();}
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;
                case "1":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**ONE MINUTE** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**ONE MINUTE** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();}
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;
                case "30":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**30 SECONDS** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**30 SECONDS** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();}
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;
                case "15":
                    if (member.hasPermission(Permission.ADMINISTRATOR)) {
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("Category 6 Storm Scrims")
                                .setThumbnail("https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg")
                                .setDescription("**15 SECONDS** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown. \n\n" +
                                        "**15 SECONDS** until the next queue for Fortnite Squads. \n\n" +
                                        "**EAST SERVERS** Be sure to have a **PLAYER** in the Fortnite Queue Room to hear the countdown.")
                                .setFooter("www.category6esports.com",
                                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");

                        channel.sendMessage("@here").queue();
                        channel.sendMessage(eb.build()).queue();}
                    else    {
                        channel.sendMessage("You must be admin to use this command!").queue();
                    }
                    break;
                case "stats":
                    statsErrorMessage(channel);
                    break;
                case "purge":
                    purgeErrorMessage(channel);
                    break;


            }
        } else if (commandArgsLength >= 2) {
            argTwo = commandArgs[1];
            if (commandArgsLength == 3) {
                argThree = commandArgs[2];
            }


            switch (argOne) {
                case "stats":
                    if (commandArgsLength == 2) {
                        Stats stats = new Stats(argTwo, channel);
                        stats.ProcessStats();
                    } else if (commandArgsLength == 3) {
                        Stats stats = new Stats(argTwo, channel, argThree);
                        stats.ProcessStats();
                    } else {
                        statsErrorMessage(channel);
                    }
                    break;
                case "purge":
                    if (commandArgsLength != 3) {
                        purgeErrorMessage(channel);

                    }
                    Purge purge = new Purge();
                    purge.purgeChannel(message, channel, guild, user, argTwo, argThree);
                    break;

            }
        }

    }

    private void purgeErrorMessage(MessageChannel channel) {
        channel.sendMessage("Make sure you use the correct format when purging messages. **!purge [direction(top or bottom)] [number of messages to delete]**\n\n" +
                "Example: **!purge top 4** would delete the top 4 messages of this channel").queue();
    }

    private void statsErrorMessage(MessageChannel channel) {
        channel.sendMessage("Make sure you use the correct format for this command! Try **!stats fortnitename** . " +
                "Make sure the Epic username is spelled correctly").queue();
    }

}





















