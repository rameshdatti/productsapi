package com.springweb.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		boolean error=false;
		if(error) {
			return Health.down().withDetail("Error key", 123).build();
		}
		return Health.up().withDetail("Success key", 321).build();
	}

}
