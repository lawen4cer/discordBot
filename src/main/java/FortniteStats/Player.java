package FortniteStats;

public class Player {

    private int kills;
    private int matchesPlayed;
    private int wins;
    private int topTen;
    private int topTwentyFive;
    private double killDeath;
    private double killPerMin;
    private double winRate;

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTopTen() {
        return topTen;
    }

    public void setTopTen(int topTen) {
        this.topTen = topTen;
    }

    public int getTopTwentyFive() {
        return topTwentyFive;
    }

    public void setTopTwentyFive(int topTwentyFive) {
        this.topTwentyFive = topTwentyFive;
    }

    public double getKillDeath() {
        return killDeath;
    }

    public void setKillDeath(double killDeath) {
        this.killDeath = killDeath;
    }

    public double getKillPerMin() {
        return killPerMin;
    }

    public void setKillPerMin(double killPerMin) {
        this.killPerMin = killPerMin;
    }

    public double getWinRate() {
        int percent;
        percent = (int) winRate;
        return percent;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }


}
