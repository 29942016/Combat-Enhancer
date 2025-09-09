package net.runelite.client.plugins.MyPlugin;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import static net.runelite.api.TileItem.OWNERSHIP_GROUP;
import static net.runelite.api.TileItem.OWNERSHIP_NONE;
import static net.runelite.api.TileItem.OWNERSHIP_OTHER;
import static net.runelite.api.TileItem.OWNERSHIP_SELF;

import lombok.val;
import net.runelite.api.coords.WorldPoint;
import org.intellij.lang.annotations.MagicConstant;

@Data
@Builder
class GroundItem
{
    private int id;
    private int itemId;
    private String name;
    private int quantity;
    private WorldPoint location;
    private int height;
    private int haPrice;
    private int gePrice;
    private int offset;
    private boolean tradeable;
    @MagicConstant(intValues = {OWNERSHIP_NONE, OWNERSHIP_SELF, OWNERSHIP_OTHER, OWNERSHIP_GROUP})
    private int ownership;
    private boolean isPrivate;
    @Nullable
    private Instant spawnTime;
    private boolean stackable;
    private Duration despawnTime;
    private Duration visibleTime;

    // cached values derived from config
    boolean highlighted;
    boolean hidden;
    Color color;

    public String print() {
        String o = "N/A";
        switch(this.ownership) {
            case OWNERSHIP_NONE:
                o = "None";
                break;
            case OWNERSHIP_SELF:
                o = "Self";
                break;
            case OWNERSHIP_OTHER:
                o = "Other";
                break;
            case OWNERSHIP_GROUP:
                o = "Group";
                break;
        }

        return "\nid: " + this.id + "\nname: " + this.name + "\nowner: " + o;
    }

    int getHaPrice()
    {
        return haPrice * quantity;
    }

    int getGePrice()
    {
        return gePrice * quantity;
    }

    void reset()
    {
        highlighted = hidden = false;
        color = null;
    }
}