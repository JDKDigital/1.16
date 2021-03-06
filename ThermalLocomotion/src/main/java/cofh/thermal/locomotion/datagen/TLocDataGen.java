package cofh.thermal.locomotion.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.core.util.constants.Constants.ID_THERMAL_LOCOMOTION;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_LOCOMOTION)
public class TLocDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event);
        }
        if (event.includeClient()) {
            registerClientProviders(event);
        }
    }

    private static void registerServerProviders(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TLocTags.Block blockTags = new TLocTags.Block(gen, exFileHelper);

        gen.addProvider(blockTags);
        gen.addProvider(new TLocTags.Item(gen, blockTags, exFileHelper));
        gen.addProvider(new TLocTags.Fluid(gen, exFileHelper));

        gen.addProvider(new TLocLootTables(gen));
        gen.addProvider(new TLocRecipes(gen));
    }

    private static void registerClientProviders(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        gen.addProvider(new TLocBlockStates(gen, exFileHelper));
        gen.addProvider(new TLocItemModels(gen, exFileHelper));
    }

}
