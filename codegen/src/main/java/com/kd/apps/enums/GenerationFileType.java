package com.kd.apps.enums;
/**
 * 
 * @author Myron Rogtao
 *
 */
public enum GenerationFileType {

	CONRTOLLER("Ctrlr"), SERVICE("Srvc"), REPO("Repo"), MODEL("Model"), POM("Pom"), API_INIT("ApiStart"), SEC_CONF(
			"ApiSecConf"), CONST("Const"), RESP_KEY("RespKey"), APITEST("ApiTest"), APITESTSTEP("ApiTestSteps");

	private String value;

	GenerationFileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
