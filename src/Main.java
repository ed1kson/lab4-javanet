import model.*;
import myjavanet.*;

public class Main {
    public static void main(String[] args) {

        String[] actions = {"newcard", "studentinfo", "addbalance", "ride", "balanceinfo"};

        new Client("eduard", "newcard");
        new Client("eduard", actions[1]);
        new Client("eduard", actions[2], 25);
        new Client("eduard", actions[3]);
        new Client("eduard", actions[4]);

        System.out.println("damn that's crazy");
    }
}




