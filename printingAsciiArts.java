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

    @Override
    public void printBackgroundScene(){
        tabPrinter(11);
        for(int i = 1; i <= 127; i++){ EncantadiaGame.prt.print("="); }
        System.out.println();
        String txt = """
                                                            +.           .     .           _..._ .            .       .                                                                   +
                                                            +      .    .       .  _..-'''''--...___ .   .'     '.    .  ___...--'''''--.._                                               +
                                                            +  ._.._.__...---'''                 '''--. .   . . --'''                    '''--..._                                        +
                                                            +                                          .   [=]  .    .                                                                    + 
                                                            +         .      .    .      .     .         _|#|_        .      .       [=]                                                  + 
                                                            +      .    .       [=]     .   . .  .      |#####|   .      .      .   _|#|_                                                 +
                                                            +   .    .    .    _|#|_   : . : . : . :    |#####| : . . : . : .      |#####|                                                +
                                                            +   .: . : . : .  |#####| : : . : . : . :   |#####|: . : . : . : . :   |#####|: . :                                           +
                                                            +  : . : . : . : .|#####|: . : . : . : . :  |#####| . : . : . : . : .  |#####| . : .                                          +
                                                            +  . : . : . : . : : : : : . : . : . : . :  : : : : : . : . : . : . :  : : : : : . :                                          +
                                                            +  : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : .                                            +
                                                            +   . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : ._.:. :                                           +
                                                            +  : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . //(_)\\ .                                           +
                                                            +   . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . |||#|||.                                            +
                                                            +  : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : .|||#|||| :                                          +
                                                            +  . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : .  |||#||||| .                                         +
                                                            +   . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : ._ . _|||#||||| :                                         + 
                                                            +  : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . :| | | |||#||||| .                                         +
                                                            +   . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : . : |#|_|#|_|#||||| :                                         +
                                                            +  _=_=_=_=_=_ . : . : . : . : . : . : . : . : . : . : . : . : . :  |#|#|#|#|#||||| .                                         +
                                                            +  \\\\:\\\\:\\\\:\\\\\\ . : . : . : . : . : . : . : . : . : ._._._. . : . : |#|#|#|#|#||||| :                                         +
                                                            +   \\\\:\\\\:\\\\:\\\\\\_= . : . : . : . : . : . : . : . : .| | | |. : . :  |#|#|#|#|#|||||                                           +
                                                            +  v \\\\:\\\\:\\\\:\\\\\\\\\\_=_=_=_=_=_=_=_=_=_=_=  . : . : .|#|#|#| . : . : |#|#|#|#|#|#|#|                                           +
                                                            +  \\|/ .\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\\\  v  . v .|#|#|#|  v  . v |#|#|#|#|#|#|#| v                                       +
                                                            +  . v .\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\:\\\\\\ \\|/  \\|/ '-------'\\|/ \\|/'---------------'\\|/                                    +
                """;
        EncantadiaGame.typePrintInline(txt, 2);

        tabPrinter(11);
        for(int i = 1; i <= 127; i++){ EncantadiaGame.prt.print("="); }
        System.out.println();


    }

    @Override
    public void printPVP(){
        System.out.println("\n");
        tabPrinter(11);
        System.out.print("=");
        for (int i = 1; i <= 124; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();
        String txt = "";
        txt = """
                                                             +                                                                                                                            +
                                                             +                               .   .                                                              .   .                     +
                                                             +                              / \\ / \\                                                            / \\ / \\                    +
                                                             +                             |   |   |                                                          |   |   |                   +
                                                             +                              \\ / \\ /                                                            \\ / \\ /                    +
                                                             +                               '   '                                                              '   '                     +
                                                             +                                 |                                                                      |                   +
                                                             +                                / \\                                                                    / \\                  +
                                                             +                               /   \\        o                                                o        /   \\                 +
                                                             +                              |     |      /|\\                                              /|\\      |     |                +
                                                             +                              |  H  |      / \\                                              / \\      |  A  |                +
                                                             +                              |_____|                                                                |_____|                +
                                                             +                             /       \\    /   \\                                            /   \\    /       \\               +
                                                             +                            /_________\\  ^^^^^^^                                          ^^^^^^^  /_________\\              +
                                                             +                           ^^^^^^^^^^^^^                                                          ^^^^^^^^^^^^^             +
                                                             +                                                                                                                            +
                                                             +                                            "Ang isa ay tatayo, ang isa ay babagsak"                                        +
                                                             +                                                                                                                            +
                  """;
        EncantadiaGame.typePrintInline(txt, 2);

        tabPrinter(11);
        System.out.print("=");
        for (int i = 1; i <= 124; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();

    }

    @Override
    public void printPVE(){
        System.out.println("\n");
        tabPrinter(11);
        System.out.print(" =");
        for (int i = 1; i <= 124; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();
        String txt = "";
        txt = """
                                                               +                                                                                                                           +
                                                               +                                                                                                                           +
                                                               +                                                                                                                           +
                                                               +                                                                          (      )                                         +
                                                               +                                             .                           (  (  )  )                                        +
                                                               +                                           / | \\\\                        ( (  )  )  )                                      +
                                                               +                                          |  |  |                      ( (      )  )                                       +
                                                               +                                           \\\\ | /                         |  |  |                                          +
                                                               +                                            \\\\|/                          |  |  |                                          +
                                                               +                                         __  |  __                      /   |   \\\\    [!!!]                                +
                                                               +                                        /  \\\\ | /  \\\\                    /    |    \\\\   / ^ \\\\                             +
                                                               +                                  o    |    \\\\|/    |            _     |   __|_    | | @ |                                 +
                                                               +                                 /|\\\\   |_____|_____|           / \\\\   /   /    \\\\   | |___|                               +
                                                               +                                 / \\\\   |           |          /   \\\\ |   | (..) |  |  |#|                                 +
                                                               +                                ^^^^^  |   GATE    |         /_____\\\\|   |  vv  |  |  |#|                                  +
                                                               +                                       |___________|        /       \\\\   |______|  |__|#|__                                +
                                                               +                                      /             \\\\      /_________\\\\ /________\\\\/________\\\\                            +
                                                               +                                                                                                                           +
                                                               +                                      "Ang kadiliman ay bumabalot sa kagubatan. Ihanda ang sarili."                        +
                                                               +                                                                                                                           +                                                               
                  """;
        EncantadiaGame.typePrintInline(txt, 2);

        tabPrinter(11);
        System.out.print(" =");
        for (int i = 1; i <= 124; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();

    }

    @Override
    public void printARCADE(){
        System.out.println("\n");
        tabPrinter(11);
        System.out.print(" =");
        for (int i = 1; i <= 125; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();
        String txt = "";
        txt = """
                                                             +                                                                                                                            +
                                                             +                                                                                                                            +
                                                             +                                                                                                                            +
                                                             +                                             .                                          .                                   +
                                                             +                                           . . .                                      . . .                                 +
                                                             +                                         . . : . .           _ ___ _                . . : . .                               +
                                                             +                                       . . : : : . .         /  |  \\\\             . . : : : . .                             +
                                                             +                                         ' . : . '          |   |   |               ' . : . '                               +
                                                             +                                           ' . '            |   |   |                 ' . '                                 +
                                                             +                                            _|_             |___|___|                  _|_                                  +
                                                             +                                           /___\\\\         /    |    \\\\               /___\\\\                                 +
                                                             +                                          | [ ] |        |    [!]    |               | [ ] |                                +
                                                             +                                          |_____|        |___________|               |_____|                                +
                                                             +                                         /       \\\\     /             \\\\            /       \\\\                              +
                                                             +                                        /    1    \\\\   /       ?       \\\\          /    2    \\\\                             +
                                                             +                                       /___________\\\\ /_________________\\\\        /___________\\\\                            +
                                                             +                                      ||||||||||||||||||||||||||||||||||||||||||||||||||||||||                              +
                                                             +                                                                                                                            +
                                                             +                                        "Kaway-kaway... hanggang kailan ka makakaligtas?"                                   +
                                                             +                                                                                                                            +
                """;
        EncantadiaGame.typePrintInline(txt, 2);

        tabPrinter(11);
        System.out.print(" =");
        for (int i = 1; i <= 125; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();

    }

    @Override
    public void lostScreen(){
        // 1. Top Border
        tabPrinter(11);
        for (int i = 1; i <= 127; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();

        // 2. The Game Over Scene
        // NOTE: Double backslashes (\\) are used to print a single backslash.
        // The spacing is calculated to equal exactly 127 characters width.
        String txt = """
                                                        +                                                                                                                             +
                                                        +                                          . . .   G A M E   O V E R   . . .                                                  +
                                                        +                                                                                                                             +
                                                        +                         .                                                                  .                                +
                                                        +               .       .   .     .                                            .       .   .                                  +
                                                        +             .  .     .     .   .  .                                        .  .     .     .                                 +
                                                        +            .    .      | |    .    .              ________________          .    .      | |                                 +
                                                        +              | |      /   \\     | |              /                \\           | |      /   \\                                +
                                                        +             /   \\    |     |   /   \\            /                  \\         /   \\    |     |                               +
                                                        +            |     |   |     |  |     |          /    R . I . P       \\       |     |   |     |                               +
                                                        +            |     |___|     |__|     |         /                      \\      |     |___|     |                               +
                                                        +           _|_____|                 _|__      |                        |    _|_____|         |                               +
                                                        +          |         |             |     |     |    H E R E   L I E S   |   |         |       |__                             +
                                                        +          |         |  v       v  |     |     |                        |   |         |          |                            +
                                                        +          |         | \\|/     \\|/ |     |     |      A   H E R O       |   |         |          |                            +
                                                        +      vv  |_________|__|_vv_vv_|__|_____|     |                        |   |_________|        vv|  vv                        +
                                                        +     \\|/  /         /   \\|/ \\|/   \\     \\     |________________________|   /         /   vv   \\|/  \\|/                       +
                                                        +      |  /         /     |   |     \\     \\     \\                      /   /         /   \\|/    |    |                        +
                                                        +     \\|/^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\\                    /^^^^^^^^^^^^^^^^^^^^|^^^^^^^^\\|/                       +
                                                        +                                                 \\__________________/                                                        +
                                                        +                                                                                                                             +
                                                        +                                       "The spirits of Encantadia mourn your loss."                                          +
                                                        +                                                                                                                             +
                                                        +                                                                                                                             +
                                                        +                                                                                                                             +
            """;

        // 3. Print the text
        EncantadiaGame.typePrintInline(txt, 2);

        // 4. Bottom Border
        tabPrinter(11);
        for (int i = 1; i <= 127; i++) { EncantadiaGame.prt.print("="); }
        System.out.println();
    }
}
