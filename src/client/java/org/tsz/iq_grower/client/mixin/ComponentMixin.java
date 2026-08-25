package org.tsz.iq_grower.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.tsz.iq_grower.client.Iq_growerClient;

/**
 * @author Tochkaszapetoi on 2026-08-25
 * @project StupidClean
 */
@Mixin(Component.class)
public interface ComponentMixin {
    @WrapMethod(method = "literal")
    private static MutableComponent literal(String string, Operation<MutableComponent> original) {
        if (!Iq_growerClient.config.enable) {
            return original.call(string);
        }
        String replacement = Iq_growerClient.config.replacement;

        for (String target : Iq_growerClient.config.targets) {
            string = string.replace(target, replacement);
        }

        return original.call(string);
    }
}
