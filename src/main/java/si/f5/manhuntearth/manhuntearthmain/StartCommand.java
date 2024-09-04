package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("start"))) return false;
        if(args.length!=0) return false;
        commandSender.sendMessage("We're no strangers to love\n" +
                "\n" +
                "You know the rules and so do I\n" +
                "\n" +
                "A full commitment's what I'm thinking of\n" +
                "\n" +
                "You wouldn't get this from any other guy\n" +
                "\n" +
                "I just wanna tell you how I'm feeling\n" +
                "\n" +
                "Gotta make you understand\n" +
                "\n");
        commandSender.sendMessage("Never gonna give you up\n" +
                "\n" +
                "Never gonna let you down\n" +
                "\n" +
                "Never gonna run around and desert you\n" +
                "\n" +
                "Never gonna make you cry\n" +
                "\n" +
                "Never gonna say goodbye\n" +
                "\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n");
        commandSender.sendMessage("We've known each other for so long\n" +
                "\n" +
                "Your heart's been aching but you're too shy to say it\n" +
                "\n" +
                "Inside we both know what's been going on\n" +
                "\n" +
                "We know the game and we're gonna play it\n" +
                "\n");
        commandSender.sendMessage("And if you ask me how I'm feeling\n" +
                "\n" +
                "Don't tell me you're too blind to see\n" +
                "\n");
        commandSender.sendMessage("Never gonna give you up\n" +
                "\n" +
                "Never gonna let you down\n" +
                "\n" +
                "Never gonna run around and desert you\n" +
                "\n" +
                "Never gonna make you cry\n" +
                "\n" +
                "Never gonna say goodbye\n" +
                "\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "Never gonna give you up\n" +
                "\n" +
                "Never gonna let you down\n" +
                "\n" +
                "Never gonna run around and desert you\n" +
                "\n" +
                "Never gonna make you cry\n" +
                "\n" +
                "Never gonna say goodbye\n" +
                "\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n");
        commandSender.sendMessage("(Ooh, give you up)\n" +
                "\n" +
                "(Ooh, give you up)\n" +
                "\n" +
                "(Ooh) Never gonna give, never gonna give (Give you up)\n" +
                "\n" +
                "(Ooh) Never gonna give, never gonna give (Give you up)\n" +
                "\n");
        commandSender.sendMessage("We've known each other for so long\n" +
                "\n" +
                "Your heart's been aching but you're too shy to say it\n" +
                "\n" +
                "Inside we both know what's been going on\n" +
                "\n" +
                "We know the game and we're gonna play it\n" +
                "\n");
        commandSender.sendMessage("I just wanna tell you how I'm feeling\n" +
                "\n" +
                "Gotta make you understand\n" +
                "\n");
        commandSender.sendMessage("Never gonna give you up\n" +
                "\n" +
                "Never gonna let you down\n" +
                "\n" +
                "Never gonna run around and desert you\n" +
                "\n" +
                "Never gonna make you cry\n" +
                "\n" +
                "Never gonna say goodbye\n" +
                "\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "Never gonna give you up\n" +
                "\n" +
                "Never gonna let you down\n" +
                "\n" +
                "Never gonna run around and desert you\n" +
                "\n" +
                "Never gonna make you cry\n" +
                "\n" +
                "Never gonna say goodbye\n" +
                "\n" +
                "Never gonna tell a lie and hurt you\n" +
                "\n" +
                "Never gonna give you up\n" +
                "\n" +
                "Never gonna let you down\n" +
                "\n" +
                "Never gonna run around and desert you\n" +
                "\n" +
                "Never gonna make you cry\n" +
                "\n" +
                "Never gonna say goodbye\n" +
                "\n" +
                "Never gonna tell a lie and hurt you");
        return true;
    }
}
