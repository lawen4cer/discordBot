package Events;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.managers.ChannelManager;

/*This class allows for twitch streamers that enable streamer mode have a link to there stream automatically post in a specific channel
* notifying guild members that they are live. Currently is hardcoded to notify @everyone*/

public class GameEventChange {
    private int gameType;
    private String url;
    private String userName;
    private TextChannel channel;




    public void handleGameEventChange(User user, Game game, Guild guild) {
        gameType= game.getType().getKey();
        userName = user.getName();
        channel = guild.getTextChannelById(383284329071116288L);

        if (gameType == 1) { //game type 1 == streaming
            url = game.getUrl();
            userName = user.getName();
            channel.sendMessage("Hey @everyone " + userName + " is live at " + url + ". Come watch the destruction from the storm!").queue();
        }

    }
}
