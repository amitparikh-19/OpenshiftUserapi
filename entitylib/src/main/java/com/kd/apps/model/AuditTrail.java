package com.kd.apps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AuditTrail extends BaseAutoIdEntity<AuditTrail> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// name of the entity class
	@Column
	private String entity;

	// entity usertype means
	// superadmin, merchant
	// customer
	// this field will remain
	// blank for non user
	// entities
	@Column
	private String entityusertype;

	// primary key or ID
	// of the entity
	@Column
	private String entityid;

	// create, update, delete
	@Column
	private String action;

	// specific functional operation
	// like, password change, address change
	// system settings change etc. etc.
	@Column
	private String operation;

	// sms, ussd, web, mobileapp, api, webpos
	@Column
	private String channel;

	// json of the entity
	// after operation
	@Column(length = 65535)
	private String changeddata;

	// free form comment
	@Column
	private String comment;

	// username who did the
	// changes or executed
	// the operation
	@Column
	private String changedby;

	// changed by usertype means
	// superadmin, merchant
	// customer
	@Column
	private String changedbyusertype;

	// date and time of the operation
	// or change
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date opdatetime;

	// date time string of the operation
	// or change
	@Transient
	private String opdatetimestr;

	/**
	 * 
	 * @return  wsp - {@link String} wallet service provider id or tenant id
	 */
	public String getWsp() {
		return wsp;
	}

	/**
	 * 
	 * @param wsp {@link String} wallet service provider id or tenant id
	 */
	public void setWsp(String wsp) {
		this.wsp = wsp;
	}

	/**
	 * 
	 * @return entity - {@link String} name of the entity class
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * 
	 * @param entity {@link String} name of the entity class
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * 
	 * @return entityusertype - {@link String} entity usertype means superadmin, merchant customer
	 */
	public String getEntityusertype() {
		return entityusertype;
	}

	/**
	 * 
	 * @param entityusertype {@link String} entity usertype means superadmin, merchant customer
	 */
	public void setEntityusertype(String entityusertype) {
		this.entityusertype = entityusertype;
	}

	/**
	 * 
	 * @return entityid - {@link String} primary key or ID of the entity
	 */
	public String getEntityid() {
		return entityid;
	}

	/**
	 * 
	 * @param entityid {@link String} primary key or ID of the entity
	 */
	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}

	/**
	 * 
	 * @return action - {@link String} create,update,delete
	 */
	public String getAction() {
		return action;
	}

	/**
	 * 
	 * @param action {@link String} create,update,delete
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * 
	 * @return operation - {@link String} specific functional operation like, password change, address change system settings change etc.
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * 
	 * @param operation {@link String} specific functional operation like, password change, address change system settings change etc.
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 
	 * @return channel - {@link String} sms, ussd, web, mobileapp, api, webpos
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * 
	 * @param channel {@link String} sms, ussd, web, mobileapp, api, webpos
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * 
	 * @return changeddata - {@link String} json of the entity after operation
	 */
	public String getChangeddata() {
		return changeddata;
	}

	/**
	 * 
	 * @param changeddata {@link String} json of the entity after operation
	 */
	public void setChangeddata(String changeddata) {
		this.changeddata = changeddata;
	}

	/**
	 * 
	 * @return comment - {@link String} free form comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 
	 * @param comment {@link String} free form comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 
	 * @return changedby - {@link String} username who did the changes or executed the operation
	 */
	public String getChangedby() {
		return changedby;
	}

	/**
	 * 
	 * @param changedby {@link String} username who did the changes or executed the operation
	 */
	public void setChangedby(String changedby) {
		this.changedby = changedby;
	}

	/**
	 * 
	 * @return changedbyusertype - {@link String} user type of user who did the changes or executed the operation
	 */
	public String getChangedbyusertype() {
		return changedbyusertype;
	}

	/**
	 * 
	 * @param changedbyusertype {@link String} user type of user who did the changes or executed the operation
	 */
	public void setChangedbyusertype(String changedbyusertype) {
		this.changedbyusertype = changedbyusertype;
	}

	/**
	 * 
	 * @return opdatetime - {@link Date} date and time of the operation or change
	 */
	public Date getOpdatetime() {
		return opdatetime;
	}

	/**
	 * 
	 * @param opdatetime {@link Date} date and time of the operation or change
	 */
	public void setOpdatetime(Date opdatetime) {
		this.opdatetime = opdatetime;
	}

	/**
	 * 
	 * @return opdatetimestr - {@link String} string date and time of the operation or change
	 */
	public String getOpdatetimestr() {
		return opdatetimestr;
	}

	/**
	 * 
	 * @param opdatetimestr {@link String} string and time of the operation or change
	 */
	public void setOpdatetimestr(String opdatetimestr) {
		this.opdatetimestr = opdatetimestr;
	}

	/**
	 * 
	 * @param AuditTrail {@link AuditTrail}
	 */
	@Override
	public AuditTrail mergeUpdates(AuditTrail tobemerged) {
		this.wsp = (null != tobemerged.getWsp() && !("".equals(tobemerged.getWsp()))
				&& !(" ".equals(tobemerged.getWsp())) ? tobemerged.getWsp() : this.getWsp());
		this.entity = (null != tobemerged.getEntity() && !("".equals(tobemerged.getEntity()))
				&& !(" ".equals(tobemerged.getEntity())) ? tobemerged.getEntity() : this.getEntity());
		this.entityusertype = (null != tobemerged.getEntityusertype() && !("".equals(tobemerged.getEntityusertype()))
				&& !(" ".equals(tobemerged.getEntityusertype())) ? tobemerged.getEntityusertype()
						: this.getEntityusertype());
		this.entityid = (null != tobemerged.getEntityid() && !("".equals(tobemerged.getEntityid()))
				&& !(" ".equals(tobemerged.getEntityid())) ? tobemerged.getEntityid() : this.getEntityid());
		this.action = (null != tobemerged.getAction() && !("".equals(tobemerged.getAction()))
				&& !(" ".equals(tobemerged.getAction())) ? tobemerged.getAction() : this.getAction());
		this.operation = (null != tobemerged.getOperation() && !("".equals(tobemerged.getOperation()))
				&& !(" ".equals(tobemerged.getOperation())) ? tobemerged.getOperation() : this.getOperation());
		this.channel = (null != tobemerged.getChannel() && !("".equals(tobemerged.getChannel()))
				&& !(" ".equals(tobemerged.getChannel())) ? tobemerged.getChannel() : this.getChannel());
		this.changeddata = (null != tobemerged.getChangeddata() && !("".equals(tobemerged.getChangeddata()))
				&& !(" ".equals(tobemerged.getChangeddata())) ? tobemerged.getChangeddata() : this.getChangeddata());
		this.changedbyusertype = (null != tobemerged.getChangedbyusertype() ? tobemerged.getChangedbyusertype()
				: this.getChangedbyusertype());
		this.opdatetime = (null != tobemerged.getOpdatetime() && !("".equals(tobemerged.getOpdatetime()))
				&& !(" ".equals(tobemerged.getOpdatetime())) ? tobemerged.getOpdatetime() : this.getOpdatetime());
		this.opdatetimestr = (null != tobemerged.getOpdatetimestr() && !("".equals(tobemerged.getOpdatetimestr()))
				&& !(" ".equals(tobemerged.getOpdatetimestr())) ? tobemerged.getOpdatetimestr()
						: this.getOpdatetimestr());
		return this;
	}
}
