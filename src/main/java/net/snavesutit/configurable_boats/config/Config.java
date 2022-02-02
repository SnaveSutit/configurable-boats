package net.snavesutit.configurable_boats.config;

import net.snavesutit.configurable_boats.config.data.ConfigData;

public class Config {

    public final ConfigData configData;
    public final boolean enableHardCollisions;
    public final float softCollisionForce;
    public final float collisionBoxSizeX;
    public final float collisionBoxSizeY;
    public final float collisionBoxSizeZ;

    public Config(ConfigData data) {
        this.configData = data;
        this.enableHardCollisions = data.enableHardCollisions;
        this.softCollisionForce = data.softCollisionForce;
        this.collisionBoxSizeX = data.collisionBoxSizeX;
        this.collisionBoxSizeY = data.collisionBoxSizeY;
        this.collisionBoxSizeZ = data.collisionBoxSizeZ;
    }
}
