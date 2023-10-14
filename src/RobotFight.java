public class RobotFight {
    public static void main(String[] args) {
        final String EXIT_BUTTON = "P";
        String currentButton;
        String tempSecondRobotName;
        String inputString;

        System.out.println("Initialization first robot name:");
        Robots firstRobot = new Robots(GamePlay.getStringFromConsole());

        do {
            System.out.println("Second robot name should not be equals first robot name." +
                    " \nInitialization unique second robot name:");
            tempSecondRobotName = GamePlay.getStringFromConsole();
        } while (firstRobot.getRobotName().equalsIgnoreCase(tempSecondRobotName));

        Robots secondRobot = new Robots(tempSecondRobotName);

        Robots currentRobot = firstRobot;
        do {
            GamePlay.PrintWelcomeMessage(currentRobot);
            System.out.println("Current robot: " + currentRobot.getRobotName());

            inputString = GamePlay.getStringFromConsole().toUpperCase();
            currentButton = GamePlay.checkInputString(inputString);

            if (currentRobot.isInvalidButton(currentButton) && !(currentButton.equals(EXIT_BUTTON))) {
                System.out.println("Wrong button. Try again!");
            } else if (currentButton.equals(EXIT_BUTTON)) {
                System.out.println("Exiting the game");
            } else {
                if (currentRobot.isActionButton(currentButton)) {
                    if (currentRobot.isActualActionButton(currentButton)) {
                        GamePlay.makeChangesWithSuccessfulHit(currentRobot, firstRobot, secondRobot, currentButton);

                        if (firstRobot.getRobotHealth() <= 0 || secondRobot.getRobotHealth() <= 0) {
                            GamePlay.printWinnersRobotName(currentRobot, firstRobot.getRobotHealth() <= 0, firstRobot, secondRobot);
                            currentButton = EXIT_BUTTON;
                            continue;
                        }
                    } else {
                        GamePlay.makeChangesWithUnsuccessfulHit(currentRobot, currentButton);

                    }
                }
                GamePlay.printRobotHealth(firstRobot, secondRobot);
                currentRobot = GamePlay.changeCurrentRobot(currentRobot, firstRobot, secondRobot);
            }

        } while (!(currentButton.equals(EXIT_BUTTON)));
    }
}
