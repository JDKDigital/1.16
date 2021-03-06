package cofh.thermal.expansion.plugins.crt.machine;

import cofh.thermal.expansion.init.TExpRecipeTypes;
import cofh.thermal.expansion.plugins.crt.actions.ActionRemoveThermalRecipeByOutput;
import cofh.thermal.expansion.plugins.crt.base.CRTRecipe;
import cofh.thermal.expansion.util.recipes.machine.RefineryRecipe;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.fluid.IFluidStack;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.item.MCWeightedItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name("mods.thermal.Refinery")
public class CRTRefineryManager implements IRecipeManager {

    @ZenCodeType.Method
    public void addRecipe(String name, MCWeightedItemStack itemOutput, IFluidStack[] fluidsOutput, IFluidStack inputFluid, int energy) {

        name = fixRecipeName(name);
        ResourceLocation resourceLocation = new ResourceLocation("crafttweaker", name);

        CRTRecipe crtRecipe = new CRTRecipe(resourceLocation).energy(energy).input(inputFluid).output(fluidsOutput).output(itemOutput);
        CraftTweakerAPI.apply(new ActionAddRecipe(this, crtRecipe.recipe(RefineryRecipe::new), ""));
    }

    @Override
    public IRecipeType<RefineryRecipe> getRecipeType() {

        return TExpRecipeTypes.RECIPE_REFINERY;
    }

    @Override
    public void removeRecipe(IItemStack output) {

        removeRecipe(new IItemStack[]{output}, new IFluidStack[0]);
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack[] itemOutputs, IFluidStack[] fluidOutputs) {

        CraftTweakerAPI.apply(new ActionRemoveThermalRecipeByOutput(this, itemOutputs, fluidOutputs));
    }

}
