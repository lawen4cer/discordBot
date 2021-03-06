package Utils;


import Events.NewMemberAction;
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
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.EventListener;


/*This class will set up the JDA object with token to access our bot
 * in the main method
 * This class will also listen for events and call the appropriate class and handle method when
 * the event is an instance of a certain event that we want to scan for*/

public class Server implements EventListener {
    private MessageEvents messageEvents = new MessageEvents(); // create a new messageEvents object to forward message events to the handler in the events class
    private StatusChangeEvents statusChangeEvents = new StatusChangeEvents();
    private GameEventChange gameEventChange = new GameEventChange();
    private NewMemberAction newMemberAction = new NewMemberAction();


    private static String token = "";


    public static void main(String[] args) throws Exception {

        Settings.path = args[0];
        token = Settings.getToken();

        JDA jda = new JDABuilder(AccountType.BOT).setGame(Game.playing("https://www.category6esports.com/"))
                .setToken(token)
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


            /*List<Role> roles = guild.getRoles();
            for (Role role: roles
                 ) {
                System.out.println(role.toString());
            }*/


        } else if (event instanceof StatusChangeEvent) {
            JDA.Status status = ((StatusChangeEvent) event).getStatus();
            User serverMaintainer = event.getJDA().getUserById(201860276197392385L);
            statusChangeEvents.handleStatusChange(status, serverMaintainer);

        } else if (event instanceof UserGameUpdateEvent) {
            User user = ((UserGameUpdateEvent) event).getUser();
            Game game = ((UserGameUpdateEvent) event).getCurrentGame();
            Guild guild = ((UserGameUpdateEvent) event).getGuild();

            try {
                System.out.println("Current game: " + game.getType() + "Previous game " + ((UserGameUpdateEvent) event).getPreviousGame().getType() + user);

                if (((UserGameUpdateEvent) event).getPreviousGame().getType() != Game.GameType.STREAMING) {
                    gameEventChange.handleGameEventChange(user, game, guild);
                }

            } catch (Exception e) {

            }
        } else if (event instanceof GuildMemberJoinEvent) {
            Member member = ((GuildMemberJoinEvent) event).getMember();
            Guild guild = ((GuildMemberJoinEvent) event).getGuild();
            newMemberAction.handleNewMember(member, guild);
        }
    }

}










