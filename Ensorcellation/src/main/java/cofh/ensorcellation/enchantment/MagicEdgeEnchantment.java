package cofh.ensorcellation.enchantment;

import cofh.core.enchantment.DamageEnchantmentCoFH;
import cofh.core.util.Utils;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.server.ServerWorld;

public class MagicEdgeEnchantment extends DamageEnchantmentCoFH {

    public MagicEdgeEnchantment() {

        super(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
        maxLevel = 3;
        treasure = true;
    }

    @Override
    public int getMinEnchantability(int level) {

        return 15 + (level - 1) * 9;
    }

    @Override
    protected int maxDelegate(int level) {

        return getMinEnchantability(level) + 50;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        Item item = stack.getItem();
        return enable && (item instanceof SwordItem || item instanceof AxeItem || supportsEnchantment(stack));
    }

    // region HELPERS
    public static float getExtraDamage(int level) {

        return level * 0.5F;
    }

    public static void onHit(LivingEntity entity, int level) {

        if (entity.world instanceof ServerWorld) {
            for (int i = 0; i < 2 * level; ++i) {
                Utils.spawnParticles(entity.world, ParticleTypes.ENCHANT, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
                Utils.spawnParticles(entity.world, ParticleTypes.ENCHANTED_HIT, entity.getPosX() + entity.world.rand.nextDouble(), entity.getPosY() + 1.0D + entity.world.rand.nextDouble(), entity.getPosZ() + entity.world.rand.nextDouble(), 1, 0, 0, 0, 0);
            }
        }
    }
    // endregion
}
