package Events;


import FortniteStats.Stats;
import net.dv8tion.jda.core.entities.*;


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
            handleCommands(message, channel, guild); // passes the data off to a separate function to handle commands
        }

        //commented code below used for debugging if needed
        /*System.out.println("Message is: "+ strMessage);
        System.out.println("User is: "+ strUser);
        System.out.println("Channel is: "+ strChannel);*/


    }

    private void handleCommands(Message message, MessageChannel channel, Guild guild) {

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
                case "stats":
                    statsErrorMessage(channel);
                    break;

            }
        } else if (commandArgsLength >= 2) {
            argTwo = commandArgs[1];
            if (commandArgsLength == 3) {
                argThree = commandArgs[2];
            }


            switch (argOne) {
                case "stats":
                    if (commandArgsLength == 3) {
                        Stats stats = new Stats(argTwo, channel, argThree);
                        stats.ProcessStats();
                    } else {
                        statsErrorMessage(channel);
                    }
                    break;
            }
        }

    }

    private void statsErrorMessage(MessageChannel channel) {
        channel.sendMessage("Make sure you use the correct format for this command! Try **!stats fortnitename gametype** . " +
                "gametype should equal solo, duo, squad, all").queue();
    }

}


















