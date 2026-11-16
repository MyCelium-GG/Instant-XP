package my.celium.org.instantxp;

import java.util.List;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;

/**
 * Bottled XP: sneak and use an empty glass bottle to put some of your XP into a Bottle o' Enchanting
 * that remembers the exact amount; throwing it gives exactly that back (vanilla bottles give 3 to 11).
 * Everything happens on the server, so players without the mod can use it too.
 */
public final class XpBottles {
    static final String KEY = "instantxp_xp";

    private XpBottles() {
    }

    /** From BottleItem.use when vanilla did nothing: fills a bottle, or returns {@code null} to leave it. */
    public static InteractionResult fill(Player player, InteractionHand hand) {
        if (!(player instanceof ServerPlayer server) || !player.isShiftKeyDown() || !InstantXpMod.bottlesEnabled()) {
            return null;
        }
        int have = totalXp(player);
        if (have <= 0) {
            return null;
        }
        int amount = Math.min(have, InstantXpMod.bottleXp.get());
        player.giveExperiencePoints(-amount);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH,
                SoundSource.PLAYERS, 0.8F, 1.2F);
        XpStats.count("bottle_fill");
        ItemStack held = player.getItemInHand(hand);
        ItemStack filled = bottle(amount);
        return InteractionResult.SUCCESS.heldItemTransformedTo(ItemUtils.createFilledResult(held, server, filled));
    }

    /** A Bottle o' Enchanting holding exactly {@code amount} XP. */
    public static ItemStack bottle(int amount) {
        ItemStack stack = new ItemStack(Items.EXPERIENCE_BOTTLE);
        CompoundTag tag = new CompoundTag();
        tag.putInt(KEY, amount);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        stack.set(DataComponents.CUSTOM_NAME, Component.translatable("item.minecraft.experience_bottle")
                .append(" (" + amount + " XP)").withStyle(style -> style.withItalic(false)));
        stack.set(DataComponents.LORE, new ItemLore(List.of(
                Component.literal("Throw it to get exactly " + amount + " XP back")
                        .withStyle(style -> style.withItalic(false).withColor(0xA8A8A8)))));
        return stack;
    }

    /** The XP a thrown bottle gives: its stored amount, or vanilla's roll for normal bottles. */
    public static int stored(ItemStack stack, int vanilla) {
        if (stack == null || stack.isEmpty()) {
            return vanilla;
        }
        CustomData data = stack.get(DataComponents.CUSTOM_DATA);
        if (data == null) {
            return vanilla;
        }
        int amount = data.copyTag().getIntOr(KEY, 0);
        if (amount > 0) {
            XpStats.count("bottle_throw");
        }
        return amount > 0 ? amount : vanilla;
    }

    /** All the XP points a player has: their levels plus the progress bar. */
    public static int totalXp(Player player) {
        int level = player.experienceLevel;
        long points = pointsForLevel(level) + Math.round(player.experienceProgress * player.getXpNeededForNextLevel());
        return (int) Math.min(Integer.MAX_VALUE, points);
    }

    /** Vanilla's XP needed to reach a level from 0. */
    static long pointsForLevel(int level) {
        long l = level;
        if (level <= 16) {
            return l * l + 6 * l;
        }
        if (level <= 31) {
            return Math.round(2.5 * l * l - 40.5 * l + 360);
        }
        return Math.round(4.5 * l * l - 162.5 * l + 2220);
    }
}
