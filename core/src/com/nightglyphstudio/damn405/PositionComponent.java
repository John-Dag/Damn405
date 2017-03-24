package com.nightglyphstudio.damn405;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
	public float positionX, positionY;

	public PositionComponent(float positionX, float positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
}
