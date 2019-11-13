import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Pacman pacman = new Pacman();
        String fileName = new File("").getCanonicalPath();
        fileName = fileName.concat("/src/test5.txt");
        pacman.playMoves(fileName);
    }

}
