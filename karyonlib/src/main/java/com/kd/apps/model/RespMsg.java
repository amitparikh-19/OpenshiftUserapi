package com.kd.apps.model;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public class RespMsg extends BaseModel {

	private String respMsgKey;

	private String respMsgVal;

	private String dateofcreation;

	private Boolean baseFrameworkKey = false;

	public String getRespMsgKey() {
		return respMsgKey;
	}

	public void setRespMsgKey(String respMsgKey) {
		this.respMsgKey = respMsgKey;
	}

	public String getRespMsgVal() {
		return respMsgVal;
	}

	public void setRespMsgVal(String respMsgVal) {
		this.respMsgVal = respMsgVal;
	}

	public String getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(String dateofcreation) {
		this.dateofcreation = dateofcreation;
	}

	public Boolean getBaseFrameworkKey() {
		return baseFrameworkKey;
	}

	public void setBaseFrameworkKey(Boolean baseFrameworkKey) {
		this.baseFrameworkKey = baseFrameworkKey;
	}
}