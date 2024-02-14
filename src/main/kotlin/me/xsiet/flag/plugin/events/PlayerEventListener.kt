package me.xsiet.flag.plugin.events

import com.destroystokyo.paper.event.player.*
import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent
import io.papermc.paper.event.player.*
import me.xsiet.flag.plugin.enums.PlayerFlag
import me.xsiet.flag.plugin.managers.getFlagValue
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.*

object PlayerEventListener: Listener {
    private fun Cancellable.flag(player: Player, flag: PlayerFlag) { if (!player.getFlagValue(flag)) isCancelled = true }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAnimationEvent.on() { flag(player, PlayerFlag.ANIMATION) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerArmorStandManipulateEvent.on() { flag(player, PlayerFlag.ARMOR_STAND_MANIPULATE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAttemptPickupItemEvent.on() { flag(player, PlayerFlag.ATTEMPT_PICKUP_ITEM) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBedEnterEvent.on() { flag(player, PlayerFlag.BED_ENTER) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBedLeaveEvent.on() { flag(player, PlayerFlag.BED_LEAVE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBucketEmptyEvent.on() { flag(player, PlayerFlag.BUCKET_EMPTY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBucketEntityEvent.on() { flag(player, PlayerFlag.BUCKET_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBucketFillEvent.on() { flag(player, PlayerFlag.BUCKET_FILL) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerCommandPreprocessEvent.on() { flag(player, PlayerFlag.COMMAND_PREPROCESS) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerDropItemEvent.on() { flag(player, PlayerFlag.DROP_ITEM) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerEditBookEvent.on() { flag(player, PlayerFlag.EDIT_BOOK) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerFishEvent.on() { flag(player, PlayerFlag.FISH) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerGameModeChangeEvent.on() { flag(player, PlayerFlag.GAME_MODE_CHANGE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerHarvestBlockEvent.on() { flag(player, PlayerFlag.HARVEST_BLOCK) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerInteractAtEntityEvent.on() { flag(player, PlayerFlag.INTERACT_AT_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerInteractEntityEvent.on() { flag(player, PlayerFlag.INTERACT_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerInteractEvent.on() { flag(player, PlayerFlag.INTERACT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemConsumeEvent.on() { flag(player, PlayerFlag.ITEM_CONSUME) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemDamageEvent.on() { flag(player, PlayerFlag.ITEM_DAMAGE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemHeldEvent.on() { flag(player, PlayerFlag.ITEM_HELD) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemMendEvent.on() { flag(player, PlayerFlag.ITEM_MEND) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerKickEvent.on() { flag(player, PlayerFlag.KICK)  }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerMoveEvent.on() { flag(player, PlayerFlag.MOVE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPickupArrowEvent.on() { flag(player, PlayerFlag.PICKUP_ARROW) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPortalEvent.on() { flag(player, PlayerFlag.PORTAL) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerRecipeDiscoverEvent.on() { flag(player, PlayerFlag.RECIPE_DISCOVER) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerShearEntityEvent.on() { flag(player, PlayerFlag.SHEAR_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStatisticIncrementEvent.on() { flag(player, PlayerFlag.STATISTIC_INCREMENT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerSwapHandItemsEvent.on() { flag(player, PlayerFlag.SWAP_HAND_ITEMS) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTakeLecternBookEvent.on() { flag(player, PlayerFlag.TAKE_LECTERN_BOOK) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTeleportEvent.on() { flag(player, PlayerFlag.TELEPORT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerToggleFlightEvent.on() { flag(player, PlayerFlag.TOGGLE_FLIGHT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerToggleSneakEvent.on() { flag(player, PlayerFlag.TOGGLE_SNEAK) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerToggleSprintEvent.on() { flag(player, PlayerFlag.TOGGLE_SPRINT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerUnleashEntityEvent.on() { flag(player, PlayerFlag.UNLEASH_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerVelocityEvent.on() { flag(player, PlayerFlag.VELOCITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAdvancementCriterionGrantEvent.on() { flag(player, PlayerFlag.ADVANCEMENT_CRITERION_GRANT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAttackEntityCooldownResetEvent.on() { flag(player, PlayerFlag.ATTACK_ENTITY_COOLDOWN_RESET) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerElytraBoostEvent.on() { flag(player, PlayerFlag.ELYTRA_BOOST) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerJumpEvent.on() { flag(player, PlayerFlag.JUMP) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLaunchProjectileEvent.on() { flag(player, PlayerFlag.LAUNCH_PROJECTILE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPickupExperienceEvent.on() { flag(player, PlayerFlag.PICKUP_EXPERIENCE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerReadyArrowEvent.on() { flag(player, PlayerFlag.READY_ARROW) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerRecipeBookClickEvent.on() { flag(player, PlayerFlag.RECIPE_BOOK_CLICK) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerSetSpawnEvent.on() { flag(player, PlayerFlag.SET_SPAWN) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStartSpectatingEntityEvent.on() { flag(player, PlayerFlag.START_SPECTATING_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStopSpectatingEntityEvent.on() { flag(player, PlayerFlag.STOP_SPECTATING_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTeleportEndGatewayEvent.on() { flag(player, PlayerFlag.TELEPORT_END_GATEWAY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun AsyncChatEvent.on() { flag(player, PlayerFlag.ASYNC_CHAT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerArmSwingEvent.on() { flag(player, PlayerFlag.ARM_SWING) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBedFailEnterEvent.on() { flag(player, PlayerFlag.BED_FAIL_ENTER) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerChangeBeaconEffectEvent.on() { flag(player, PlayerFlag.CHANGE_BEACON_EFFECT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerDeepSleepEvent.on() { flag(player, PlayerFlag.DEEP_SLEEP) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerFlowerPotManipulateEvent.on() { flag(player, PlayerFlag.FLOWER_POT_MANIPULATE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemCooldownEvent.on() { flag(player, PlayerFlag.ITEM_COOLDOWN) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemFrameChangeEvent.on() { flag(player, PlayerFlag.ITEM_FRAME_CHANGE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLecternPageChangeEvent.on() { flag(player, PlayerFlag.LECTERN_PAGE_CHANGE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLoomPatternSelectEvent.on() { flag(player, PlayerFlag.LOOM_PATTERN_SELECT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerNameEntityEvent.on() { flag(player, PlayerFlag.NAME_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerOpenSignEvent.on() { flag(player, PlayerFlag.OPEN_SIGN) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPickItemEvent.on() { flag(player, PlayerFlag.PICK_ITEM) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPurchaseEvent.on() { flag(player, PlayerFlag.PURCHASE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerShieldDisableEvent.on() { flag(player, PlayerFlag.SHIELD_DISABLE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerSignCommandPreprocessEvent.on() { flag(player, PlayerFlag.SIGN_COMMAND_PREPROCESS) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStonecutterRecipeSelectEvent.on() { flag(player, PlayerFlag.STONECUTTER_RECIPE_SELECT) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTrackEntityEvent.on() { flag(player, PlayerFlag.TRACK_ENTITY) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTradeEvent.on() { flag(player, PlayerFlag.TRADE) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PrePlayerAttackEntityEvent.on() { flag(player, PlayerFlag.PRE_ATTACK_ENTITY) }
}