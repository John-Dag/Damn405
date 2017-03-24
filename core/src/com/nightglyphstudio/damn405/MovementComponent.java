package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {
	public float velocityX, velocityY;

	public MovementComponent(float velocityX, float velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
}
