package org.tsz.iq_grower.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Function;

/**
 * @author Tochkaszapetoi on 2026-08-25
 * @project StupidClean
 */
@Mixin(ChatComponent.class)
public class ChatComponentMixin {
    @WrapMethod(method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;ILnet/minecraft/client/GuiMessageTag;Z)V")
    public void handleChatInput(Component component, MessageSignature messageSignature, int i, GuiMessageTag guiMessageTag, boolean bl, Operation<Void> original) {
        String json = Component.Serializer.toJson(component);
        original.call(replaceText(json, "ILYADNEPR", ""), messageSignature, i , guiMessageTag, bl);
    }
    @Unique
    private Component replaceText(String json, String target, String replacement) {
        return editTag(json, "text", (s) ->{
            return s.replace(target, replacement);
        });
    }
    @Unique
    private Component editTag(String json, String tag, Function<String, String> injectionConsumer) {
        StringBuilder output = new StringBuilder();
        int index, skip = tag.length() + 4;
        while((index = json.indexOf("\"" + tag + "\"")) != -1) {
            output.append(json, 0, index + skip);

            json = json.substring(index + skip);
            int quotationMarkPos = json.indexOf("\"");
            String sub = json.substring(0, quotationMarkPos);
            json = json.substring(quotationMarkPos);
            output.append(injectionConsumer.apply(sub));
        }
        output.append(json);
        return Component.Serializer.fromJson(output.toString());
    }
}
