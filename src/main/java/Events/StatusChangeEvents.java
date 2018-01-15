package Events;


import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;

/*Does absolutely nothing at this point except log status change events to console.
 * TODO Try to have the bot send a message to the admin user if the bot is disconnected. May be impossible because of connection loss. Needs more R&D */


public class StatusChangeEvents {

    public void handleStatusChange(JDA.Status status, User user){

        System.out.println(status.toString());
        if (status.equals(JDA.Status.DISCONNECTED)) {

        }
    }

}
