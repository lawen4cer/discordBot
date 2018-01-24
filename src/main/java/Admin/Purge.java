package Admin;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.requests.restaction.pagination.PaginationAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Purge {
    Member member;
    List<Role> roles;
    private int numOfMessages;


    public void purgeChannel(Message message, MessageChannel channel, Guild guild, User user, String numOfMessages) {
        Role admin = guild.getRoleById(392941839734538242L);

        if (numOfMessages.equals("all")){
            this.numOfMessages = 1000;
        }else{
        this.numOfMessages = Integer.parseInt(numOfMessages);
        }



        member = message.getMember();
        roles = member.getRoles();

        if (roles.contains(admin)) {
            List<Message> pastMessages = getAll(channel);
            Collections.reverse(pastMessages);

            for (Message pastMessage: pastMessages) {
                channel.deleteMessageById(pastMessage.getId()).queue();
            }

        }
        else {
            channel.sendMessage("You do not have the power to do that. See an admin for more info").queue();
        }

    }



    public List<Message> getAll(MessageChannel channel){
        List<Message> pastMessages = new ArrayList<>();
        int i = numOfMessages;
        for (Message message: channel.getIterableHistory().cache(false)) {
            pastMessages.add(message);
            if (--i <= 0) break;
        }

       return pastMessages;
    }


}



