package Events;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

import java.util.Arrays;
import java.util.List;

public class MessageEvents {
    private String strMessage;
    private String strUser;
    private String strChannel;
    private String argOne;
    private String argTwo;

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




    public void handleMessage(Message message, User user, MessageChannel channel) {
        strMessage = message.getContentRaw();
        strUser = user.getName();
        strChannel = channel.getName();


        if (strMessage.startsWith("!")){ //checks to see if the message starts with an !, which will be the default command invoke character on this bot
            handleCommands(strMessage, channel); // passes the data off to a separate function to handle commands
        }

        //commented code below used for debugging if needed
        /*System.out.println("Message is: "+ strMessage);
        System.out.println("User is: "+ strUser);
        System.out.println("Channel is: "+ strChannel);*/


    }

    private void handleCommands(String command, MessageChannel channel) {

        String trimCommand = command.substring(1); //trims off the ! of the command
        String[] commandArgs = trimCommand.split(" "); // splits the message into separate strings using a single white space as the separator

        int commandArgsLength = commandArgs.length;
        argOne = commandArgs[0];
        if (commandArgsLength > 1) argTwo = commandArgs[1];

        switch (argOne) { //test commands again pre-defined commands
            case "site":
                channel.sendMessage("Check out our website -> https://www.category6esports.com/").queue();
                break;
            case "hbird":
                channel.sendMessage("Check out hBird's stream -> https://www.twitch.tv/hbird").queue();
                break;
            default:
                channel.sendMessage("That is not an valid command").queue();

        }
    }
}













