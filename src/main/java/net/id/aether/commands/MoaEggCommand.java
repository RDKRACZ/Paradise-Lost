package net.id.aether.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.id.aether.api.MoaAPI;
import net.id.aether.component.MoaGenes;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class MoaEggCommand {

    private static final MoaRaceSuggester MOA_RACES = new MoaRaceSuggester();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("moaegg")
                        .requires((source) -> source.hasPermissionLevel(2))
                        .then(argument("target", EntityArgumentType.players()).then((argument("race", IdentifierArgumentType.identifier()).suggests(MOA_RACES)
                                .executes(context -> execute(context.getSource(), EntityArgumentType.getPlayers(context, "target"), IdentifierArgumentType.getIdentifier(context, "race"), false)))
                                .then(literal("asBaby")
                                        .executes(context -> execute(context.getSource(), EntityArgumentType.getPlayers(context, "target"), IdentifierArgumentType.getIdentifier(context, "race"), true)))))
        );
    }

    private static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets, Identifier raceId, boolean baby) {
        var race = MoaAPI.getRace(raceId);

        // Let them get the fallback moa if they intend to, otherwise fail.
        if (race == MoaAPI.FALLBACK_MOA && !raceId.equals(MoaAPI.FALLBACK_MOA.getId())) {
            source.sendError(new TranslatableText("commands.the_aether.moaegg.failure", raceId.toString()));
        } else {
            ItemStack template = MoaGenes.getEggForCommand(race, source.getWorld(), baby);
            targets.forEach(player -> {
                ItemStack egg = template.copy();
                if (!player.getInventory().insertStack(template)) {
                    ItemScatterer.spawn(source.getWorld(), player.getX(), player.getY(), player.getZ(), egg);
                }
                source.sendFeedback(new TranslatableText("commands.the_aether.moaegg.success", egg.toHoverableText(), targets.iterator().next().getDisplayName()), true);
            });
        }

        return targets.size();
    }

    public static class MoaRaceSuggester implements SuggestionProvider<ServerCommandSource> {
        // This suggests the fallback moa too now. If this isn't desired, a check can be added.
        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) {
            MoaAPI.getRegisteredRaces().forEachRemaining(race -> builder.suggest(race.getId().toString()));
            return builder.buildFuture();
        }
    }
}
