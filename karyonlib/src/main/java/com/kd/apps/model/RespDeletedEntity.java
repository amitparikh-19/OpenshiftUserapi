package com.kd.apps.model;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public class RespDeletedEntity extends RespBaseEntity {

	private String entityId = "null";

	private Boolean isdeleted;

	public RespDeletedEntity() {
		super();
	}

	public RespDeletedEntity(String entityname) {
		super(entityname);
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
}
