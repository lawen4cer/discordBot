package Admin;

import net.dv8tion.jda.core.entities.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Purge {
    Member member;
    List<Role> roles;
    private int numOfMessages;


    public void purgeChannel(Message message, MessageChannel channel, Guild guild, User user, String direction, String deleteamount) {
        Role admin = guild.getRoleById(392941839734538242L);
        List<Message> pastMessages = new ArrayList<>();

        if (deleteamount.equals("all")) {
            numOfMessages = 1000;
        } else {
            numOfMessages = Integer.parseInt(deleteamount);
        }


        member = message.getMember();
        roles = member.getRoles();

        if (roles.contains(admin)) {
            if (direction.equals("top")) {
                pastMessages = getMessagesFromTop(channel);
            } else if (direction.equals("bottom")) {
                pastMessages = getMessages(channel);
            } else {
                channel.sendMessage("Please specify to either delete messages from top or bottom of this channel").queue();
                return;
            }


            for (Message pastMessage : pastMessages) {

                if (numOfMessages != 0) {
                    channel.deleteMessageById(pastMessage.getId()).queue();
                    numOfMessages--;
                }

            }


        } else {
            channel.sendMessage("You do not have the power to do that. See an admin for more info").queue();
        }

    }


    public List<Message> getMessages(MessageChannel channel) {
        List<Message> pastMessages = new ArrayList<>();
        int i = numOfMessages;
        for (Message message : channel.getIterableHistory().cache(false)) {
            pastMessages.add(message);
            if (--i <= 0) break;
        }

        return pastMessages;
    }

    public List<Message> getMessagesFromTop(MessageChannel channel) {
        List<Message> pastMessages = new ArrayList<>();
        int i = 2000;
        for (Message message : channel.getIterableHistory().cache(false)) {
            pastMessages.add(message);
            if (--i <= 0) break;
        }

        Collections.reverse(pastMessages);
        return pastMessages;


    }


}



