package me.dbots.minecraftchecker.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.dbots.minecraftchecker.MinecraftChecker;
import net.chris54721.openmcauthenticator.OpenMCAuthenticator;
import net.chris54721.openmcauthenticator.exceptions.AuthenticationUnavailableException;
import net.chris54721.openmcauthenticator.exceptions.RequestException;
import net.chris54721.openmcauthenticator.responses.AuthenticationResponse;

public class CheckCommand extends Command {

    private final MinecraftChecker minecraftChecker;

    public CheckCommand(MinecraftChecker minecraftChecker) {
        this.name = "check";
        this.help = "Check Command.";
        this.guildOnly = false;
        this.minecraftChecker = minecraftChecker;
    }

    @Override
    protected void execute(CommandEvent event) {

        if(event.getArgs().isEmpty()){
            event.getTextChannel().sendMessage(MinecraftChecker.buildEmbed(null, "Error: ``" + minecraftChecker.getConstants().getPrefix() + "check (accounts)", null, event.getJDA()).build()).queue();
            return;
        }

        String[] accounts = event.getArgs().trim().split("\\r?\\n");
        StringBuilder accountsWithPseudo = new StringBuilder();

        for(String account : accounts){
            String pseudo = account.trim().split(":")[0];
            String password = account.trim().split(":")[1];

            try {
                AuthenticationResponse authResponse = OpenMCAuthenticator.authenticate(pseudo, password);
                accountsWithPseudo.append(account).append(":").append(authResponse.getSelectedProfile().getName()).append("\n");
            } catch (RequestException | AuthenticationUnavailableException ignored) {}
        }

        event.getTextChannel().sendMessage("```" + accountsWithPseudo +  "```").queue();

    }
}
