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


    public void handleGameEventChange(User user, Game game, Guild guild) {
        String url;
        String userName;

        Game.GameType gameType = game.getType();
        TextChannel channel = guild.getTextChannelById(402930803396313098L);
        Role streamer = guild.getRoleById(403634299141881857L);
        Member member = guild.getMember(user);
        List<Long> whitelist = new ArrayList<>();

        whitelist.add(176402603410718720L);
        //whitelist.add(201860276197392385L); //lawen4cer
        whitelist.add(168857926201638913L);
        whitelist.add(173962438507495424L);
        whitelist.add(371345745397415937L); //baklava
        whitelist.add(190508990487068672L); //fozmaniac
        whitelist.add(199232847314157568L); //nobody
        whitelist.add(225236568305041418L); //videlak
        whitelist.add(143231148015747073L); //coop

        if (gameType == Game.GameType.STREAMING) {
            if (whitelist.contains(user.getIdLong())) {
                url = game.getUrl();
                userName = user.getName();
                channel.sendMessage("Hey @everyone " + userName + " is live at " + url + " ! Go watch them and show your support!").queue();
            } else if (member.getRoles().contains(streamer)) {
                url = game.getUrl();
                userName = user.getName();
                channel.sendMessage("Hey " + userName + " is live at " + url + " ! Go watch them and show your support!").queue();
            }


        }
    }
}

