package io.github.bloepiloepi.pvp.events;

import net.minestom.server.entity.Entity;
import net.minestom.server.event.trait.CancellableEvent;
import net.minestom.server.event.trait.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class FinalAttackEvent implements EntityEvent, CancellableEvent {
	
	private final Entity entity;
	private final Entity target;
	
	private boolean critical;
	private boolean sweeping;
	private float baseDamage;
	private float enchantsExtraDamage;
	
	private boolean cancelled;
	
	public FinalAttackEvent(@NotNull Entity entity, @NotNull Entity target,
	                        boolean critical, boolean sweeping,
	                        float baseDamage, float enchantsExtraDamage) {
		this.entity = entity;
		this.target = target;
		this.critical = critical;
		this.sweeping = sweeping;
		this.baseDamage = baseDamage;
		this.enchantsExtraDamage = enchantsExtraDamage;
	}
	
	@Override
	public @NotNull Entity getEntity() {
		return entity;
	}
	
	public @NotNull Entity getTarget() {
		return target;
	}
	
	/**
	 * Gets whether the attack was critical.
	 *
	 * @return whether the attack was critical or not
	 */
	public boolean isCritical() {
		return critical;
	}
	
	/**
	 * Sets whether the attack was critical.
	 *
	 * @param crit whether the attack was critical or not
	 */
	public void setCritical(boolean crit) {
		this.critical = crit;
	}
	
	/**
	 * Gets whether the attack was sweeping.
	 *
	 * @return whether the attack as sweeping or not
	 */
	public boolean isSweeping() {
		return sweeping;
	}
	
	/**
	 * Sets whether the attack was sweeping.
	 *
	 * @param sweeping whether the attack was sweeping or not
	 */
	public void setSweeping(boolean sweeping) {
		this.sweeping = sweeping;
	}
	
	/**
	 * Gets the base damage of the attack.
	 * Tool enchantments are excluded, but attack cooldown strength (for 1.9+) is not.
	 *
	 * @return the base damage of the attack
	 */
	public float getBaseDamage() {
		return baseDamage;
	}
	
	/**
	 * Sets the base damage of the attack.
	 * Enchantment extra damage will be added to this to get the final damage.
	 *
	 * @param baseDamage the base damage of the attack
	 */
	public void setBaseDamage(float baseDamage) {
		this.baseDamage = baseDamage;
	}
	
	/**
	 * Gets the extra damage from enchantments of the attack
	 * (e.g. sharpness, but also mob based, e.g. impaling).
	 * This does not include the base damage.
	 *
	 * @return the extra damage from enchantments of the attack
	 */
	public float getEnchantsExtraDamage() {
		return enchantsExtraDamage;
	}
	
	/**
	 * Sets the extra damage of the attack.
	 * This will be added to the base damage to get the final damage.
	 *
	 * @param enchantsExtraDamage the extra damage of the attack
	 */
	public void setEnchantsExtraDamage(float enchantsExtraDamage) {
		this.enchantsExtraDamage = enchantsExtraDamage;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
}
