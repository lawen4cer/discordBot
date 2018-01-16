package Utils;


import Music.PlayerControl;
import Events.GameEventChange;
import Events.MessageEvents;
import Events.StatusChangeEvents;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.StatusChangeEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.EventListener;


/*This class will set up the JDA object with token to access our bot
* in the main method
* This class will also listen for events and call the appropriate class and handle method when
* the event is an instance of a certain event that we want to scan for*/

public class Server implements EventListener {
    private static String TOKEN = ""; //this token is the token provided by discord (Insert your bot token here!!!!)
    private MessageEvents messageEvents = new MessageEvents(); // create a new messageEvents object to forward message events to the handler in the events class
    private StatusChangeEvents statusChangeEvents = new StatusChangeEvents();
    private GameEventChange gameEventChange = new GameEventChange();


    public static void main(String[] args) throws Exception {
        JDA jda = new JDABuilder(AccountType.BOT).setGame(Game.playing("www.category6esports.com"))
                .setToken(TOKEN)
                .buildBlocking();

        jda.addEventListener(new Server());
        jda.addEventListener(new PlayerControl());
    }


    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            Message message = ((MessageReceivedEvent) event).getMessage();
            User author = ((MessageReceivedEvent) event).getAuthor();
            MessageChannel channel = ((MessageReceivedEvent) event).getChannel();
            Guild guild = ((MessageReceivedEvent) event).getGuild();
            messageEvents.handleMessage(message, author, channel, guild);

        } else if (event instanceof StatusChangeEvent) {
            JDA.Status status = ((StatusChangeEvent) event).getStatus();
            User serverMaintainer = event.getJDA().getUserById(201860276197392385L);
            statusChangeEvents.handleStatusChange(status, serverMaintainer);

        } else if (event instanceof UserGameUpdateEvent) {
            User user = ((UserGameUpdateEvent) event).getUser();
            Game game = ((UserGameUpdateEvent) event).getCurrentGame();
            Guild guild = ((UserGameUpdateEvent) event).getGuild();
            gameEventChange.handleGameEventChange(user, game, guild);
        }
    }

}









