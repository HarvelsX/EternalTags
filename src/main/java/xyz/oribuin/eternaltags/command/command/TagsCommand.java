package xyz.oribuin.eternaltags.command.command;

import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.command.framework.BaseRoseCommand;
import dev.rosewood.rosegarden.command.framework.CommandContext;
import dev.rosewood.rosegarden.command.framework.CommandInfo;
import dev.rosewood.rosegarden.command.framework.annotation.RoseExecutable;
import org.bukkit.entity.Player;
import xyz.oribuin.eternaltags.gui.MenuProvider;
import xyz.oribuin.eternaltags.gui.menu.CategoryGUI;
import xyz.oribuin.eternaltags.gui.menu.TagsGUI;
import xyz.oribuin.eternaltags.manager.ConfigurationManager.Setting;
import xyz.oribuin.eternaltags.manager.LocaleManager;

public class TagsCommand extends BaseRoseCommand {

    public TagsCommand(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    @RoseExecutable
    public void execute(CommandContext context) {
        final LocaleManager locale = this.rosePlugin.getManager(LocaleManager.class);

        // Make sure the sender is a player.
        if (!(context.getSender() instanceof Player player)) {
            locale.sendMessage(context.getSender(), "only-player");
            return;
        }

        if (Setting.OPEN_CATEGORY_GUI_FIRST.getBoolean())
            MenuProvider.get(CategoryGUI.class).open(player);
        else
            MenuProvider.get(TagsGUI.class).open(player, null);
    }

    @Override
    protected CommandInfo createCommandInfo() {
        return CommandInfo.builder(null)
                .permission("eternaltags.use")
                .build();
    }

}
