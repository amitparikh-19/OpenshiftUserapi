package com.kd.apps.model;

import java.util.List;
/**
 * 
 * @author Myron Rogtao
 *
 */
public class MicroServiceConfig {

	private List<TemplateEntityConfig> microServiceConfigs;

	public List<TemplateEntityConfig> getMicroServiceConfigs() {
		return microServiceConfigs;
	}

	public void setMicroServiceConfigs(List<TemplateEntityConfig> microServiceConfigs) {
		this.microServiceConfigs = microServiceConfigs;
	}

}
