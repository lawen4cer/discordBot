package Events;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import java.util.Arrays;
import java.util.List;

public class MessageEvents {
    private String strMessage;
    private String strUser;
    private String strChannel;
    //private MessageChannel sendChannel;



    public void handle(Message message, User user, MessageChannel channel) {
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
        List<String> commandList = Arrays.asList(commandArgs); // stores the array as a list

        //statement below used for debugging if needed
        //System.out.println(commandList);

        switch (commandList.get(0)) { //test commands again pre-defined commands
            case "test":
                channel.sendMessage("Yo, your bot now has a heart").queue();
        }


    }
}



