package cofh.thermal.expansion.datagen;

import cofh.core.util.references.CoFHTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.core.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

public class TExpTags {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator gen, ExistingFileHelper existingFileHelper) {

            super(gen, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Block Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {

            super(gen, blockTagProvider, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Item Tags";
        }

        @Override
        protected void registerTags() {

            getOrCreateBuilder(CoFHTags.Items.MACHINE_DIES).add(ITEMS.get("press_coin_die"));
            getOrCreateBuilder(CoFHTags.Items.MACHINE_DIES).add(ITEMS.get("press_gear_die"));
            getOrCreateBuilder(CoFHTags.Items.MACHINE_DIES).add(ITEMS.get("press_packing_2x2_die"));
            getOrCreateBuilder(CoFHTags.Items.MACHINE_DIES).add(ITEMS.get("press_packing_3x3_die"));
            getOrCreateBuilder(CoFHTags.Items.MACHINE_DIES).add(ITEMS.get("press_unpacking_die"));

            getOrCreateBuilder(CoFHTags.Items.MACHINE_CASTS).add(ITEMS.get("chiller_ball_cast"));
        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator gen, ExistingFileHelper existingFileHelper) {

            super(gen, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}

