package dev.kikugie.shulkerfix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.kikugie.shulkerfix.Util;
import dev.kikugie.shulkerfix.carpet.ShulkerFixSettings;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.vehicle.HopperMinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
	@WrapOperation(
		method = "extract(Lnet/minecraft/world/World;Lnet/minecraft/block/entity/Hopper;)Z",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/HopperBlockEntity;extract(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/entity/ItemEntity;)Z")
	)
    private static boolean limitCollectCount(Inventory inventory, ItemEntity itemEntity, Operation<Boolean> original) {
        if (!Util.isShulkerBoxChecked(itemEntity.getStack())) return original.call(inventory, itemEntity);
        if (ShulkerFixSettings.hopperCollectSingleShulkers && inventory instanceof HopperBlockEntity)
            return Util.collectOneItem(inventory, itemEntity);
        else if (ShulkerFixSettings.minecartCollectSingleShulkers && inventory instanceof HopperMinecartEntity)
            return Util.collectOneItem(inventory, itemEntity);
        else return original.call(inventory, itemEntity);
    }

    @Inject(
        method = "canMergeItems",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void cancelItemMerging(ItemStack first, ItemStack second, CallbackInfoReturnable<Boolean> cir) {
        if (Util.isShulkerBoxChecked(first) || Util.isShulkerBox(second)) cir.setReturnValue(false);
    }

    @WrapOperation(
        method = "insert",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getMaxCount()I"),
        require = 0
    )
    private static int modifyInsertMaxCount(ItemStack instance, Operation<Integer> original) {
        return Util.isShulkerBoxChecked(instance) ? instance.getCount() : original.call(instance);
    }
}
