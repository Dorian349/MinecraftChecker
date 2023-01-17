package me.dbots.minecraftchecker.Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import me.dbots.minecraftchecker.MinecraftChecker;
import net.chris54721.openmcauthenticator.OpenMCAuthenticator;
import net.chris54721.openmcauthenticator.exceptions.AuthenticationUnavailableException;
import net.chris54721.openmcauthenticator.exceptions.RequestException;
import net.chris54721.openmcauthenticator.responses.AuthenticationResponse;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckCommand extends SlashCommand {

    public CheckCommand() {
        this.name = "check";
        this.help = "Check Command.";
        this.guildOnly = false;

        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "accounts", "All the accounts to check. Format mail:password").setRequired(true));

        this.options = options;

    }

    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        String[] accounts = Objects.requireNonNull(slashCommandEvent.getOption("accounts")).getAsString().split("\\r?\\n");
        StringBuilder accountsWithPseudo = new StringBuilder();

        for(String account : accounts){
            String pseudo = account.trim().split(":")[0];
            String password = account.trim().split(":")[1];

            try {
                AuthenticationResponse authResponse = OpenMCAuthenticator.authenticate(pseudo, password);
                accountsWithPseudo.append(account).append(":").append(authResponse.getSelectedProfile().getName()).append("\n");
            } catch (RequestException | AuthenticationUnavailableException ignored) {}
        }

        slashCommandEvent.reply("```" + accountsWithPseudo +  "```").queue();

    }
}
