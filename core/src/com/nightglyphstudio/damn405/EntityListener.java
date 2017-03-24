package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.Entity;

public interface EntityListener {
	public void entityAdded(Entity entity);
	public void entityRemoved(Entity entity);
}
