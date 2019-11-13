import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pacman {

    public PacmanPosition pacmanPosition = new PacmanPosition();
    public int xMax = 5;
    public int yMax = 5;

    static Map<String, Integer> stringToDirMap = new HashMap<>();
    static Map<Integer, String> integerToString = new HashMap<>();
    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    static {
        stringToDirMap.put("NORTH", 0);
        stringToDirMap.put("SOUTH", 2);
        stringToDirMap.put("EAST", 1);
        stringToDirMap.put("WEST", 3);

        integerToString.put(0, "NORTH");
        integerToString.put(2, "SOUTH");
        integerToString.put(1, "EAST");
        integerToString.put(3, "WEST");
    }

    public boolean isValid(PacmanPosition pacmanPosition){
        if(!pacmanPosition.isPlaced) return false;
        if(pacmanPosition.xPos >= xMax ||
                pacmanPosition.xPos < 0 ||
                pacmanPosition.yPos >= yMax ||
                pacmanPosition.yPos < 0){
            return  false;
        }
        return true;
    }

    public void playMove(String cmd){
        PacmanPosition oldPacmanPosition = this.pacmanPosition;
        PacmanPosition newPacmanPosition = this.pacmanPosition.clone();
        String[] command = cmd.split(" ");
        if(command.length > 2 || command.length < 1){
            return;
        }else{
            String c = command[0];
            switch (c){
                case "PLACE":
                    if(command.length != 2) return;
                    String cor = command[1];
                    String[] pat = cor.split(",");
                    if(pat.length != 3) return ;
                    if(!(pat[0].matches("-?\\d+") && pat[1].matches("-?\\d+") && pat[2].matches("NORTH|SOUTH|EAST|WEST"))){
                        return ;
                    }
                    newPacmanPosition.xPos = Integer.parseInt(pat[0]);
                    newPacmanPosition.yPos = Integer.parseInt(pat[1]);
                    newPacmanPosition.dir = stringToDirMap.get(pat[2]);
                    newPacmanPosition.isPlaced = true;
                    break;
                case "MOVE":
                    if(command.length != 1 || !oldPacmanPosition.isPlaced) return;
                    newPacmanPosition.xPos = oldPacmanPosition.xPos + dir[oldPacmanPosition.dir][0];
                    newPacmanPosition.yPos = oldPacmanPosition.yPos + dir[oldPacmanPosition.dir][1];
                    break;
                case "LEFT":
                    if(command.length != 1 || !oldPacmanPosition.isPlaced) return ;
                    newPacmanPosition.dir = (oldPacmanPosition.dir - 1 + 4)%4;
                    break;
                case "RIGHT":
                    if(command.length != 1 || !oldPacmanPosition.isPlaced) return ;
                    newPacmanPosition.dir = (oldPacmanPosition.dir + 1)%4;
                    break;
                case "REPORT":
                    if(command.length != 1 || !oldPacmanPosition.isPlaced) return ;
                    System.out.println(oldPacmanPosition.xPos + " " + oldPacmanPosition.yPos + " " + integerToString.get(oldPacmanPosition.dir));
                    break;
                default: return ;
            }
        }

        if(isValid(newPacmanPosition)) {
            this.pacmanPosition = newPacmanPosition;
            return;
        }
    }


    public void playMoves(String fileName) throws IOException {
        Scanner sc = new Scanner(new File(fileName));
        while(sc.hasNext()){
            String line = sc.nextLine();
            this.playMove(line);
        }
    }

}
