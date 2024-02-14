package me.xsiet.flag.plugin.events

import com.destroystokyo.paper.event.player.*
import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent
import io.papermc.paper.event.player.*
import me.xsiet.flag.plugin.enums.PlayerFlag
import me.xsiet.flag.plugin.managers.getFlagValue
import org.bukkit.entity.Player
import org.bukkit.event.*
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.event.player.*

object PlayerEventListener: Listener {
    private fun Cancellable.flag(event: Event) {
        fun flag(player: Player) {
            if (!player.getFlagValue(PlayerFlag.entries.find { it.toString() == event.eventName }!!)) isCancelled = true
        }
        if (event is PlayerEvent) flag(event.player)
        else if (event is EntityEvent) {
            val entity = event.entity
            if (entity is Player) flag(entity)
        }
        else if (event is InventoryEvent) {}
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PlayerEvent: org.bukkit.event.player
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAnimationEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerArmorStandManipulateEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAttemptPickupItemEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBedEnterEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBedLeaveEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBucketEmptyEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBucketEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBucketFillEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerCommandPreprocessEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerDropItemEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerEditBookEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerFishEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerGameModeChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerHarvestBlockEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerInteractAtEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerInteractEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerInteractEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemConsumeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemDamageEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemHeldEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemMendEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerKickEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerMoveEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPickupArrowEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPortalEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerRecipeDiscoverEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerShearEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStatisticIncrementEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerSwapHandItemsEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTakeLecternBookEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTeleportEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerToggleFlightEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerToggleSneakEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerToggleSprintEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerUnleashEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerVelocityEvent.on() { flag(this) }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PlayerEvent: com.destroystokyo.paper.event.player
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAdvancementCriterionGrantEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerAttackEntityCooldownResetEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerElytraBoostEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerJumpEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLaunchProjectileEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPickupExperienceEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerReadyArrowEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerRecipeBookClickEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerSetSpawnEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStartSpectatingEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStopSpectatingEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTeleportEndGatewayEvent.on() { flag(this) }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PlayerEvent: io.papermc.paper.event.player
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun AsyncChatEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerArmSwingEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerBedFailEnterEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerChangeBeaconEffectEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerDeepSleepEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerFlowerPotManipulateEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemCooldownEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerItemFrameChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLecternPageChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLoomPatternSelectEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerNameEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerOpenSignEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPickItemEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerPurchaseEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerShieldDisableEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerSignCommandPreprocessEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerStonecutterRecipeSelectEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTrackEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerTradeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PrePlayerAttackEntityEvent.on() { flag(this) }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // EntityEvent: org.bukkit.event.entity
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun AreaEffectCloudApplyEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ArrowBodyCountChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun BatToggleSleepEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun CreatureSpawnEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun CreeperPowerEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EnderDragonChangePhaseEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityAirChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityBreakDoorEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityBreedEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityChangeBlockEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityCombustByBlockEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityCombustByEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityCombustEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityDamageByBlockEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityDamageByEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityDamageEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityDeathEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityDismountEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityDropItemEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityEnterBlockEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityEnterLoveModeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityExhaustionEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityExplodeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityInteractEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityMountEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityPickupItemEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityPlaceEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityPortalEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityPortalExitEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityPotionEffectEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityRegainHealthEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityResurrectEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityShootBowEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntitySpawnEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntitySpellCastEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityTameEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityTargetEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityTargetLivingEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityTeleportEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityToggleGlideEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityToggleSwimEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityTransformEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun EntityUnleashEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ExpBottleEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ExplosionPrimeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun FireworkExplodeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun FoodLevelChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun HorseJumpEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ItemDespawnEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ItemMergeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ItemSpawnEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun LingeringPotionSplashEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PiglinBarterEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PigZapEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PigZombieAngerEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerDeathEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PlayerLeashEntityEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun PotionSplashEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ProjectileHitEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun ProjectileLaunchEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun SheepDyeWoolEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun SheepRegrowWoolEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun SlimeSplitEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun SpawnerSpawnEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun StriderTemperatureChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun VillagerAcquireTradeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun VillagerCareerChangeEvent.on() { flag(this) }
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun VillagerReplenishTradeEvent.on() { flag(this) }
}