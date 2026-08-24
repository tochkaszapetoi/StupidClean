package org.tsz.iq_grower.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Tochkaszapetoi on 2026-08-25
 * @project StupidClean
 */
@Mixin(Component.class)
public interface ComponentMixin {
    @WrapMethod(method = "literal")
    private static MutableComponent a(String string, Operation<MutableComponent> original) {

        return original.call(string.replace("ILYADNEPR", ""));
    }
}
