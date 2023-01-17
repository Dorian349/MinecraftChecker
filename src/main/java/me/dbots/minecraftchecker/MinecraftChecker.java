package me.dbots.minecraftchecker;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import me.dbots.minecraftchecker.Commands.CheckCommand;
import me.dbots.minecraftchecker.Utils.Constants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.concurrent.Executors;

public class MinecraftChecker {

    private final Constants constants;

    public static Color embedColor = Color.cyan;

    private MinecraftChecker() throws LoginException {
        System.out.println("Minecraft Checker is Starting...");

        Path path = Paths.get(System.getProperty("config.file", System.getProperty("config", "config.txt")));
        if(path.toFile().exists()) {
            if(System.getProperty("config.file") == null) System.setProperty("config.file", System.getProperty("config", "config.txt"));
            ConfigFactory.invalidateCaches();
        }

        Config config = ConfigFactory.load();
        constants = new Constants(config);

        EventWaiter waiter = new EventWaiter(Executors.newSingleThreadScheduledExecutor(), false);
        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setPrefix(constants.getPrefix());
        builder.setOwnerId(constants.getOwnerID());
        builder.setAlternativePrefix("@mention");
        builder.setActivity(Activity.playing(constants.getActivityMessage()));
        builder.setHelpWord("ignored");
        builder.setLinkedCacheSize(200);
        builder.setEmojis("✅", "⚠", "❌");
        builder.addSlashCommand(new CheckCommand());

        CommandClient client = builder.build();

        DefaultShardManagerBuilder shardBuilder = DefaultShardManagerBuilder.createDefault(constants.getToken());
        shardBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        shardBuilder.setActivity(Activity.playing("Loading..."));
        shardBuilder.setAutoReconnect(true);
        shardBuilder.addEventListeners(client, waiter);
        shardBuilder.build();
    }

    public static void main(String[] args) throws LoginException {
        new MinecraftChecker();
    }

    public Constants getConstants() {
        return constants;
    }

    public static EmbedBuilder buildEmbed(String title, String description, String footer, JDA jda){
        EmbedBuilder embed = new EmbedBuilder();
        if(title != null){
            embed.setTitle(title);
        }
        if(description != null){
            embed.setDescription(description);
        }
        embed.setColor(MinecraftChecker.embedColor);
        if(footer != null){
            embed.setTimestamp(Instant.now());
            embed.setFooter(footer, jda.getSelfUser().getAvatarUrl());
        }
        return embed;
    }
}
