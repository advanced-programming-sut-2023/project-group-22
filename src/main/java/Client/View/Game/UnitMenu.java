package Client.View.Game;

import Client.Model.Person.Military.MilitaryUnit;
import Client.View.Commands.UnitMenuCommands;
import Client.Controller.UnitMenuController;

import java.util.ArrayList;
import java.util.regex.Matcher;

import static Client.View.InputOutput.input;
import static Client.View.InputOutput.output;

public class UnitMenu {
    public static ArrayList<MilitaryUnit> userUnitInTile;

    public void run() {
        String command;
        Matcher matcher;
        while (true) {
            command = input();
            if (command.matches("\\s*show\\s+related\\s+commands\\s*")) {
                output("exit\nmove unit to -x <X> -y <Y>\nset -s <status>\nattack -e <X> <Y>\nattack -x <X> -y <Y>\ndisband unit\npatrol unit -x1 <X1> -y1 <Y1> -x2 <X2> -y2 <Y2>\npour oil -d <direction>\nbuild equipment");
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.EXIT)) != null) {
                output("Unit menu exited manually!");
                return;
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.MOVE_UNIT)) != null) {
                int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
                UnitMenuController.moveUnit(x, y);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.SET_STATUS)) != null) {
                String status = matcher.group("status");
                UnitMenuController.setStatus(status);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.ATTACK_ENEMY)) != null) {
                int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
                UnitMenuController.attackEnemy(x, y);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.ATTACH_ARCHER)) != null) {
                int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
                UnitMenuController.attackArcher(x, y);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.DISBAND)) != null) {
                UnitMenuController.disbandUnit();
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.PATROL_UNIT)) != null) {
                int x1 = Integer.parseInt(matcher.group("X1")), y1 = Integer.parseInt(matcher.group("Y1")),
                        x2 = Integer.parseInt(matcher.group("X2")), y2 = Integer.parseInt(matcher.group("Y2"));
                if (UnitMenuController.patrolUnit(x1, x2, y1, y2)) {
                    output("Exiting Unit Menu");
                    return;
                }
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.POUR_OIL)) != null) {
                char direction = matcher.group("direction").charAt(0);
                UnitMenuController.pourOil(direction);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.BUILD_EQUIPMENT)) != null) {
                UnitMenuController.buildEquipment();
                if (userUnitInTile.size() == 0) {
                    output("There are no more units remaining in this tile\nexiting!");
                    return;
                }
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.DIG_MOAT)) != null) {
                int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
                UnitMenuController.digMoat(x, y);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.FILL_MOAT)) != null) {
                int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
                UnitMenuController.fillMoat(x, y);
            } else if ((matcher = UnitMenuCommands.getMatcher(command, UnitMenuCommands.DIG_TUNNEL)) != null) {
                int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
                UnitMenuController.digTunnel(x, y);
            } else if (command.equalsIgnoreCase("show health")) {
                UnitMenuController.showHealth();
            } else {
                output("Invalid command!");
            }
        }
    }
}
