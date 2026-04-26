import model.*;
import myjavanet.*;

public class Main {
    public static void main(String[] args) {

        String[] actions = {"newcard", "studentinfo", "addbalance", "ride", "balanceinfo"};

        new Client("newcard|eduard");
        new Client("newcard|sofia");
        new Client("newcard|ning");
        new Client("studentinfo|eduard");
        new Client("addbalance|eduard|25");
        new Client("addbalance|sofia|23");
        new Client("addbalance|ning|4");
        new Client("balanceinfo|eduard");
        new Client("balanceinfo|sofia");
        new Client("balanceinfo|ning");
        new Client("ride|eduard");
        new Client("ride|ning");
        new Client("balanceinfo|eduard");
        new Client("balanceinfo|edurad");
        new Client("do a backflip|eduard");
    }
}




