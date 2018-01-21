package FortniteStats;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageChannel;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Stats {

    private String userName;
    private String argThree;
    private MessageChannel channel;
    private String url = " https://fortnite.y3n.co/v2/player/";
    Player player = new Player();

    public Stats(String username, MessageChannel channel, String argThree) {
        userName = username;
        this.channel = channel;
        this.argThree = argThree;
    }

    public String urlBuild() {
        return url + userName;
    }

    public void ProcessStats() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlBuild())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                channel.sendMessage("We are unable to connect to the server at this time. Try again later").queue();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                updateDetails(jsonData);
                updateDisplay(channel);


            }
        });



    }

    private void updateDetails(String jsonData) {
        JSONObject initial = null;
        try {
            initial = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
            channel.sendMessage("There is no data for that username").queue();
        }
        JSONObject br = initial.getJSONObject("br");
        JSONObject stats = br.getJSONObject("stats");
        JSONObject pc = stats.getJSONObject("pc");
        JSONObject gameType = null;

        switch (argThree) {
            case "solo":
                gameType = pc.getJSONObject("solo");
                break;
            case "duo":
                gameType = pc.getJSONObject("duo");
                break;
            case "squad":
                gameType = pc.getJSONObject("squad");
                break;
            case "all":
                gameType = pc.getJSONObject("all");
                break;
        }

        player.setKills(gameType.getInt("kills"));
        player.setMatchesPlayed(gameType.getInt("matchesPlayed"));
        player.setKillDeath(gameType.getDouble("kpd"));
        player.setKillPerMin(gameType.getDouble("kpm"));
        player.setWinRate(gameType.getDouble("winRate"));
        player.setWins(gameType.getInt("wins"));



    }

    public void updateDisplay(MessageChannel channel) {
        channel.sendMessage("**" + userName + "**" + " Fortnite stats for " + "**" + argThree + "**\n" +
                                    "\n" +
                                    "**Matches Played:**  " + player.getMatchesPlayed() + "\n" +
                                    "**Wins:**  " + player.getWins() + "\n" +
                                    "**Win Percentage:**  " + player.getWinRate() + "%" + "\n" +
                                    "**Kills:**  " + player.getKills() + "\n" +
                                    "**Kill/Death:**  " + player.getKillDeath() + "\n" +
                                    "**Kills per minute:**  " + player.getKillPerMin() + "\n").queue();

    }
}













