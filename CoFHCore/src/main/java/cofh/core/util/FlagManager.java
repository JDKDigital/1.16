package cofh.core.util;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.loot.LootConditionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.crafting.CraftingHelper;

import java.util.function.BooleanSupplier;

import static cofh.core.util.constants.Constants.FALSE;
import static cofh.core.util.constants.Constants.TRUE;

public class FlagManager {

    private static final Object2ObjectOpenHashMap<String, BooleanSupplier> FLAGS = new Object2ObjectOpenHashMap<>(64);

    public LootConditionType flagConditionType;

    public FlagManager(String modId) {

        CraftingHelper.register(new FlagRecipeCondition.Serializer(this, new ResourceLocation(modId, "flag")));
        flagConditionType = new LootConditionType(new FlagLootCondition.Serializer(this));
        Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(modId, "flag"), flagConditionType);
    }

    private BooleanSupplier getFlagRaw(String flag) {

        FLAGS.putIfAbsent(flag, FALSE);
        return FLAGS.get(flag);
    }

    public synchronized void setFlag(String flag, boolean enable) {

        FLAGS.put(flag, enable ? TRUE : FALSE);
    }

    public synchronized void setFlag(String flag, BooleanSupplier condition) {

        FLAGS.put(flag, condition == null ? FALSE : condition);
    }

    public BooleanSupplier getFlag(String flag) {

        return getFlagRaw(flag);
    }

}
