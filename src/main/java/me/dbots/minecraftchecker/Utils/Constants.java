package me.dbots.minecraftchecker.Utils;

import com.typesafe.config.Config;

public class Constants {

    private final String token;
    private final String ownerID;
    private final String activityMessage;
    private final String prefix;

    public Constants(Config config){
        this.token = config.getString("token");
        this.ownerID = config.getString("ownerID");
        this.activityMessage = config.getString("activityMessage");
        this.prefix = config.getString("prefix");
    }

    public String getPrefix() {
        return prefix;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getActivityMessage() {
        return activityMessage;
    }

    public String getToken() {
        return token;
    }
}
