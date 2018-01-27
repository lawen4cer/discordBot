package Events;

import Utils.Server;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.managers.ChannelManager;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

/*This class allows for twitch streamers that enable streamer mode have a link to there stream automatically post in a specific channel
 * notifying guild members that they are live. Currently is hardcoded to notify @everyone*/

public class GameEventChange {


    public void handleGameEventChange(User user, Game game, Guild guild, List whitelist) {
        String url;

        Game.GameType gameType = game.getType();
        String userName = user.getName();
        TextChannel channel = guild.getTextChannelById(402930803396313098L);
        Role streamer = guild.getRoleById(403634299141881857L);
        Member member = guild.getMember(user);

        if (gameType == Game.GameType.STREAMING) {
            if (whitelist.contains(user.getIdLong())) {
                url = game.getUrl();
                userName = user.getName();
                channel.sendMessage("Hey @everyone " + userName + " is live at " + url + " ! Come watch the destruction from the storm!").queue();
            } else if (member.getRoles().contains(streamer)) {
                url = game.getUrl();
                userName = user.getName();
                channel.sendMessage("Hey " + userName + " is live at " + url + " ! Go watch them and show your support!").queue();
            }


        }
    }
}

