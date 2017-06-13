package com.kd.apps.ctrlr;

import java.io.Serializable;
import java.security.Principal;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.kd.apps.Const;
import com.kd.apps.KLog;
import com.kd.apps.Utils;
import com.kd.apps.exception.ServerException;
import com.kd.apps.model.BaseEntity;
import com.kd.apps.model.RespDeletedEntity;
import com.kd.apps.model.RespMonoEntity;
import com.kd.apps.model.RespMultiEntity;
import com.kd.apps.srvc.BaseSrvc;

/**
 * Abstract class for CRUD on entities to be extended by entity specific
 * controllers
 * @param <E> Entity datatype
 * @param <ID> ID datatype
 * @author Pradyumna Roy
 */
public abstract class BaseAbstractCtrlr<E extends BaseEntity<E, ID>, ID extends Serializable> {

	protected final KLog logger = new KLog(this.getClass());
	protected String methodname = "methodnull";
	protected String loggedinuser = Const.DEFAULT_SUPERADMIN;
	protected String ctrlrname;
	public static String defaultSortField = "created";
	protected BaseSrvc<E, ID> srvc;
	@Autowired
	protected Utils utils;

	public BaseAbstractCtrlr() {

	}

	/**
	 * @param srvc Spring component/service
	 * @param ctrlrname name of the RestController
	 */
	public BaseAbstractCtrlr(BaseSrvc<E, ID> srvc, String ctrlrname) {
		super();
		this.srvc = srvc;
		this.ctrlrname = ctrlrname;
	}

	public Log getLogger() {
		return logger;
	}

	public String getMethodname() {
		return methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	public String getLoggedinuser() {
		return loggedinuser;
	}

	public void setLoggedinuser(String loggedinuser) {
		this.loggedinuser = loggedinuser;
	}

	public String getCtrlrname() {
		return ctrlrname;
	}

	public void setCtrlrname(String ctrlrname) {
		this.ctrlrname = ctrlrname;
	}

	public BaseSrvc<E, ID> getSrvc() {
		return srvc;
	}

	public void setSrvc(BaseSrvc<E, ID> srvc) {
		this.srvc = srvc;
	}

	public Utils getUtils() {
		return utils;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public String logid() {
		return utils.logid(loggedinuser);
	}

	public abstract ResponseEntity<E> create(E inputentity, Principal principal) throws ServerException;

	public abstract ResponseEntity<E> read(ID id, Principal principal) throws ServerException;

	public abstract ResponseEntity<Iterable<E>> readAll(Principal principal) throws ServerException;

	public abstract ResponseEntity<Iterable<E>> readAllPageable(Integer firstresult, Integer maxresult, String sortdir,
			String sortfield, Principal principal) throws ServerException;

	public abstract ResponseEntity<E> update(ID id, E tobemerged, Principal principal) throws ServerException;

	public abstract ResponseEntity<RespDeletedEntity> delete(ID id, Principal principal) throws ServerException;

	/**
	 * @param inputentity Entity to be created
	 * @param principal Spring Security Principal object having username
	 * @return responseEntity 
	 * @throws ServerException
	 */
	protected ResponseEntity<E> createEntity(E inputentity, Principal principal) throws ServerException {
		methodname = "createEntity";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname) + inputentity.toString());
		ResponseEntity<E> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		RespMonoEntity<E> respMonoEntity = new RespMonoEntity<E>();
		respMonoEntity = srvc.create(logid(), inputentity);

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respMonoEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respMonoEntity.getRespmsgval());
		responseEntity = new ResponseEntity<E>(respMonoEntity.getEntity(), headers, respMonoEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respMonoEntity.toString());
		return responseEntity;
	}

	/**
	 * 
	 * @param id
	 * @param principal
	 * @return responseEntity
	 * @throws ServerException
	 */
	protected ResponseEntity<E> readEntity(ID id, Principal principal) throws ServerException {
		methodname = "readEntity";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname) + utils.getjson("id", id));
		ResponseEntity<E> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		RespMonoEntity<E> respMonoEntity = new RespMonoEntity<E>();
		respMonoEntity = srvc.read(logid(), id);

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respMonoEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respMonoEntity.getRespmsgval());
		responseEntity = new ResponseEntity<E>(respMonoEntity.getEntity(), headers, respMonoEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respMonoEntity.toString());
		return responseEntity;
	}

	protected ResponseEntity<Iterable<E>> readAllEntities(Principal principal) throws ServerException {
		methodname = "readAllEntities";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname));

		ResponseEntity<Iterable<E>> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		RespMultiEntity<E> respMultiEntity = srvc.readAll(logid());
		Iterable<E> entities = respMultiEntity.getEntities();

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respMultiEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respMultiEntity.getRespmsgval());
		responseEntity = new ResponseEntity<Iterable<E>>(entities, headers, respMultiEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respMultiEntity.toString());
		return responseEntity;
	}

	protected ResponseEntity<Iterable<E>> readAllEntitiesPageable(Integer firstresult, Integer maxresult,
			String sortdir, String sortfield, Principal principal) throws ServerException {
		methodname = "readAllEntitiesPageable";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname));
		ResponseEntity<Iterable<E>> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		// Default sort Direction and Field
		if (sortdir == null) {
			sortdir = "DESC";
		}
		if (sortfield == null) {
			sortfield = defaultSortField;
		}
		Pageable pageable = new PageRequest(firstresult, maxresult, Sort.Direction.fromString(sortdir), sortfield);

		RespMultiEntity<E> respMultiEntity = srvc.readAllPageable(logid(), pageable);
		Iterable<E> entities = respMultiEntity.getEntities();

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respMultiEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respMultiEntity.getRespmsgval());
		responseEntity = new ResponseEntity<Iterable<E>>(entities, headers, respMultiEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respMultiEntity.toString());

		return responseEntity;
	}

	protected ResponseEntity<E> updateEntity(ID id, E tobemerged, Principal principal) throws ServerException {
		methodname = "updateEntity";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname));
		ResponseEntity<E> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		RespMonoEntity<E> respMonoEntity = srvc.update(logid(), id, tobemerged);

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respMonoEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respMonoEntity.getRespmsgval());
		responseEntity = new ResponseEntity<E>(respMonoEntity.getEntity(), headers, respMonoEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respMonoEntity.toString());

		return responseEntity;
	}

	protected ResponseEntity<RespDeletedEntity> deleteEntity(ID id, Principal principal) throws ServerException {
		methodname = "deleteEntity";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname));
		ResponseEntity<RespDeletedEntity> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		RespDeletedEntity respDeletedEntity = srvc.delete(logid(), id);

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respDeletedEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respDeletedEntity.getRespmsgval());
		responseEntity = new ResponseEntity<RespDeletedEntity>(respDeletedEntity, headers,
				respDeletedEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respDeletedEntity.toString());

		return responseEntity;
	}

	protected ResponseEntity<Iterable<E>> readAllEntitiesPageSearchable(Integer firstresult, Integer maxresult,
			String sortdir, String sortfield, Specification<E> specification, Principal principal)
			throws ServerException {
		methodname = "readAllEntitiesPageable";
		loggedinuser = ((null != principal) ? principal.getName() : loggedinuser);
		logger.info(logid() + utils.logstage("input") + utils.logmethod(methodname));
		ResponseEntity<Iterable<E>> responseEntity;
		HttpHeaders headers = new HttpHeaders();

		// Default sort Direction and Field
		if (sortdir == null) {
			sortdir = "DESC";
		}
		if (sortfield == null) {
			sortfield = defaultSortField;
		}
		Pageable pageable = new PageRequest(firstresult, maxresult, Sort.Direction.fromString(sortdir), sortfield);

		RespMultiEntity<E> respMultiEntity = srvc.readAllPageSearch(logid(), pageable, specification);
		Iterable<E> entities = respMultiEntity.getEntities();

		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, respMultiEntity.getRespmsgkey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, respMultiEntity.getRespmsgval());
		responseEntity = new ResponseEntity<Iterable<E>>(entities, headers, respMultiEntity.getHttpstatus());
		logger.info(logid() + utils.logstage("output") + utils.logmethod(methodname) + respMultiEntity.toString());

		return responseEntity;
	}
}
