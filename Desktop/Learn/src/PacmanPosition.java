public class PacmanPosition {
    public int xPos = -1;
    public int yPos = -1;
    public boolean isPlaced = false;
    public int dir = 0;


    public PacmanPosition(){
    }

    public PacmanPosition(int xPos, int yPos, boolean isPlaced, int dir){
        this.xPos = xPos;
        this.isPlaced = isPlaced;
        this.dir = dir;
        this.yPos = yPos;
    }

    public PacmanPosition clone(){
        return new PacmanPosition(xPos, yPos, isPlaced, dir);
    }

    @Override
    public String toString(){
        return " " + xPos + " " + " " + yPos + " " + dir + " " + isPlaced;
    }
}
