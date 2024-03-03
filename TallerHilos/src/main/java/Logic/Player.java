package Logic;

public class Player {
    private String name;
    private int nWin;
    private String location;
    private int points;
    private int score;

    public Player(String name, int nWin, String location, int points, int score) {
        this.name = name;
        this.nWin = nWin;
        this.location = location;
        this.points = points;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnWin() {
        return nWin;
    }

    public void setnWin(int nWin) {
        this.nWin= nWin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points_to_complete) {
        this.points = points_to_complete;
    }
}
