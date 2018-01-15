package Events;

import jdk.net.SocketFlow;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;

public class StatusChangeEvents {

    public void handleStatusChange(JDA.Status status, User user){

        System.out.println(status.toString());
        if (status.equals(JDA.Status.CONNECTED)){
            user.openPrivateChannel().queue();
        }
    }

}
