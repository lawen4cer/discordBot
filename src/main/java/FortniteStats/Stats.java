package FortniteStats;


import Utils.Settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Stats {

    private int soloKills;
    private int soloMatchesPlayed;
    private double soloWinPercentage;
    private int soloWins;
    private double soloKillDeath;
    private double soloKillsPerGame;
    private int duoKills;
    private int duoMatchesPlayed;
    private double duoWinPercentage;
    private int duoWins;
    private double duoKillDeath;
    private double duoKillsPerGame;
    private int squadKills;
    private int squadMatchesPlayed;
    private double squadWinPercentage;
    private int squadWins;
    private double squadKillDeath;
    private double squadKillsPerGame;
    private String totalKills;
    private String totalMatchesPlayed;
    private String totalWinPercentage;
    private String totalWins;
    private String totalKillDeath;
    private String totalKillsPerGame;
    private String totalSurvivalTime;
    private String totalTimePlayed;
    private String userName;
    private String argThree = "";
    private MessageChannel channel;
    private String url = " https://api.fortnitetracker.com/v1/profile/pc/";
    Player player = new Player();

    public Stats(String username, MessageChannel channel, String argThree) {
        userName = username;
        this.channel = channel;
        this.argThree = argThree;
    }

    public Stats(String username, MessageChannel channel) {
        userName = username;
        this.channel = channel;
    }

    public String urlBuild() {
        return url + userName + "%20" + argThree;
    }

    public String trackerUserNameBuild() {
        if (argThree.equals("")) {
            return userName;
        } else {
            return userName + "%20" + argThree;
        }
    }

    public void ProcessStats() {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().header("TRN-Api-Key", Settings.getApiKey())
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
                //System.out.println(jsonData);
                updateDetails(jsonData);
                updateDisplay(channel);


            }
        });


    }

    private String displayUserName() {
        if (argThree.equals("")) {
            return userName;
        } else {
            return userName + " " + argThree;
        }
    }

    private void updateDetails(String jsonData) {
        JSONObject initial = null;
        try {
            initial = new JSONObject(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
            channel.sendMessage("No data found for that username, check your spelling!").queue();
        }

        JSONObject stats = null;
        try {
            stats = initial.getJSONObject("stats");

        } catch (JSONException e) {
            e.printStackTrace();
            channel.sendMessage("I can't find any stats for that player, double check your spelling").queue();
        }

        JSONObject solo = null;
        try {
            solo = stats.getJSONObject("p2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject soloWins = solo.getJSONObject("top1");
            player.setSoloWins(soloWins.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject soloKillDeath = solo.getJSONObject("kd");
            player.setSoloKillDeath(soloKillDeath.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            if (solo.has("winRatio")) {
                JSONObject soloWinPercentage = solo.getJSONObject("winRatio");
                player.setSoloWinPercentage(soloWinPercentage.getDouble("valueDec"));
            } else {
                player.setSoloWinPercentage(0);
            }


        } catch (JSONException e) {
            e.printStackTrace();


        }

        try {
            JSONObject soloMatches = solo.getJSONObject("matches");
            player.setSoloMatchesPlayed(soloMatches.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject soloKills = solo.getJSONObject("kills");
            player.setSoloKills(soloKills.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();

        }
        try {
            JSONObject soloKillsPerGame = solo.getJSONObject("kpg");
            player.setSoloKillsPerGame(soloKillsPerGame.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject duo = null;
        try {
            duo = stats.getJSONObject("p10");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject duoWins = duo.getJSONObject("top1");
            player.setDuoWins(duoWins.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject duoKillDeath = duo.getJSONObject("kd");
            player.setDuoKillDeath(duoKillDeath.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject duoWinPercentage = duo.getJSONObject("winRatio");
            player.setDuoWinPercentage(duoWinPercentage.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject duoMatches = duo.getJSONObject("matches");
            player.setDuoMatchesPlayed(duoMatches.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject duoKills = duo.getJSONObject("kills");
            player.setDuoKills(duoKills.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject duoKillsPerGame = duo.getJSONObject("kpg");
            player.setDuoKillsPerGame(duoKillsPerGame.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject squad = null;
        try {
            squad = stats.getJSONObject("p9");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject squadWins = squad.getJSONObject("top1");
            player.setSquadWins(squadWins.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject squadKillDeath = squad.getJSONObject("kd");
            player.setSquadKillDeath(squadKillDeath.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject squadWinPercentage = squad.getJSONObject("winRatio");
            player.setSquadWinPercentage(squadWinPercentage.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject squadMatches = squad.getJSONObject("matches");
            player.setSquadMatchesPlayed(squadMatches.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject squadKills = squad.getJSONObject("kills");
            player.setSquadKills(squadKills.getInt("valueInt"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject squadKillsPerGame = squad.getJSONObject("kpg");
            player.setSquadKillsPerGame(squadKillsPerGame.getDouble("valueDec"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray total = initial.getJSONArray("lifeTimeStats");
        JSONObject totalWins = total.getJSONObject(8);
        player.setTotalWins(totalWins.getString("value"));
        JSONObject totalKillDeath = total.getJSONObject(11);
        player.setTotalKillDeath(totalKillDeath.getString("value"));
        JSONObject totalWinPercentage = total.getJSONObject(9);
        player.setTotalWinPercentage(totalWinPercentage.getString("value"));
        JSONObject totalMatches = total.getJSONObject(7);
        player.setTotalMatchesPlayed(totalMatches.getString("value"));
        JSONObject totalKills = total.getJSONObject(10);
        player.setTotalKills(totalKills.getString("value"));
        JSONObject totalScore = total.getJSONObject(6);
        player.setTotalScore(totalScore.getString("value"));


        //Obsolete
//        JSONObject totalSurvivalTime = total.getJSONObject(14);
//        player.setTotalSurvivalTime(totalSurvivalTime.getString("value"));
//        JSONObject totalTimePlayed = total.getJSONObject(13);
//        player.setTotalTimePlayed(totalTimePlayed.getString("value"));


    }

    public void updateDisplay(MessageChannel channel) {


        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("**Fortnite stats for " + displayUserName() + "**", "https://fortnitetracker.com/profile/pc/" + trackerUserNameBuild())
                .addBlankField(false).setThumbnail("https://cdn2.unrealengine.com/Fortnite%2Fsearch-for-survivors%2FsignupBanner-155x221-7d1f31411baf91e6cadf490c6f60f98a72b38b4c.png")
                .setDescription("Click link above or fortnite logo to see more stats \n\n" +


                        "***Solo***\n" +
                        "**Matches**: " + player.getSoloMatchesPlayed() + "\n" +
                        "**Wins**: " + player.getSoloWins() + "\n" +
                        "**Win %**: " + player.getSoloWinPercentage() + "\n" +
                        "**Kills**: " + player.getSoloKills() + "\n" +
                        "**Kill/Death**: " + player.getSoloKillDeath() + "\n" +
                        "**Kills Per Game**: " + player.getSoloKillsPerGame() + "\n" +
                        "\n" +

                        "***Duo*** \n" +
                        "**Matches**: " + player.getDuoMatchesPlayed() + "\n" +
                        "**Wins**: " + player.getDuoWins() + "\n" +
                        "**Win %**: " + player.getDuoWinPercentage() + "\n" +
                        "**Kills**: " + player.getDuoKills() + "\n" +
                        "**Kill/Death**: " + player.getDuoKillDeath() + "\n" +
                        "**Kills Per Game**: " + player.getDuoKillsPerGame() + "\n" +
                        "\n" +

                        "***Squad***\n" +
                        "**Matches**: " + player.getSquadMatchesPlayed() + "\n" +
                        "**Wins**: " + player.getSquadWins() + "\n" +
                        "**Win %**: " + player.getSquadWinPercentage() + "\n" +
                        "**Kills**: " + player.getSquadKills() + "\n" +
                        "**Kill/Death**: " + player.getSquadKillDeath() + "\n" +
                        "**Kills Per Game**: " + player.getSquadKillsPerGame() + "\n"
                )
                .addField("**Total Matches**", player.getTotalMatchesPlayed(), true)
                .addField("**Total Wins**", player.getTotalWins(), true)
                .addField("**Total Win %**", player.getTotalWinPercentage(), true)
                .addField("**Total Kills**", player.getTotalKills(), true)
                .addField("**Total K/D**", player.getTotalKillDeath(), true)
                .addField("**Total Score**", player.getTotalScore(), true)
                .setFooter("www.category6esports.com",
                        "https://pbs.twimg.com/profile_images/952298315030454273/0JTwceUq_400x400.jpg");


        channel.sendMessage(eb.build()).queue();
    }
}













