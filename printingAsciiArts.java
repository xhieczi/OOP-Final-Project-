public class printingAsciiArts implements asciiArts{

    @Override
    public void tabPrinter(int amount){
        for(int i = 1; i <= amount; i++){
            EncantadiaGame.prt.print("\t");
        }
    }

    @Override
    public void printLoadingScreen(int offset){

            String text = "         ☁     \uD83C\uDF0A   ☁  ___________      \uD83D\uDD25      ⛰     ☁    __         ☁     .___.__           \uD83C\uDF0A                    \n" +
                    "         ✦            \\_   _____/ ____   ____ _____    _____/  |______     __| _/|__|____        \uD83D\uDD25           ⛰     \n" +
                    "     ______   ______   |    __)_ /    \\_/ ___\\\\__  \\  /    \\   __\\__  \\   / __ | |  \\__  \\     ______   ______       ♦             \n" +
                    "    /_____/  /_____/   |        \\   |  \\  \\___ / __ \\|   |  \\  |  / __ \\_/ /_/ | |  |/ __ \\_  /_____/  /_____/    ♦    ♦          \n" +
                    "                      /_______  /___|  /\\___  >____  /___|  /__| (____  /\\____ | |__(____  /   \uD83D\uDD25      ⛰        ♦ ✦ ✦  ♦      \n" +
                    "            ✦         ⛰       \\/     \\/     \\/     \\/   \uD83C\uDF0A  \\/          \\/      \\/         \\/                 ♦ ✦ ✦ ✦  ♦         \n" +
                    "   ___________      \uD83C\uDF0A       \uD83D\uDD25             _____  ___________.__       ⛰   ________       ☁                  ♦ ✦ ✦ ✦ ♦          \n" +
                    "   \\__    ___/_ _________  ____✦    _____/ ____\\ \\__    ___/|  |__   ____  ☁/  _____/  ____   _____   ______     ♦ ♦ ♦       \n" +
                    "     |    | |  |  \\_  __ \\/    \\   /  _ \\   __\\    |    |   |  |  \\_/ __ \\  /   \\  ____/ __ \\ /     \\ /  ___/     \n" +
                    "     |    | |  |  /|  | \\/   |  \\ (  <_> )  |      |    |   |   Y  \\  ___/  \\    \\_\\  \\  ___/|  Y Y  \\\\___ \\      \n" +
                    "     |____| |____/ |__|  |___|  /  \\____/|__|      |____|   |___|  /\\___  >  \\______  /\\___  >__|_|  /____  >     \n" +
                    "       \uD83C\uDF0A             ♦         \\/      \uD83D\uDD25                 ♦     ☁    \\/     \\/          \\/     \\/      \\/     \\/   ";


            String[] lines = text.split("\n");

            EncantadiaGame.prt.println("\n\n");
        for (String line : lines) {
            tabPrinter(offset);
            EncantadiaGame.prt.println(line);
        }

    }
}
