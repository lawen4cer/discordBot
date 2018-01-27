package Events;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.xml.soap.Text;

public class NewMemberAction extends ListenerAdapter {

    public void handleNewMember(Member member, Guild guild) {


        TextChannel channel = guild.getTextChannelById(405892851709509633L);
        channel.sendMessage("Welcome to the Cat6 discord " + member.getAsMention() + " . If you are interested in joining a roster and haven't already " +
                "done so, go to the forum and fill out the required form at https://www.category6esports.com/forum ! It will be reviewed and we will contact you. " +
                "Don't hesitate to get involved and play with other members! ").queue();
    }

}


