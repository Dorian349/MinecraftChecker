package me.dbots.minecraftchecker;

import com.typesafe.config.Config;

public class Constants {

    private String token;
    private String ownerID;
    private String activityMessage;
    private String prefix;

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
