package com.kd.apps.srvc;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.CannotCreateTransactionException;

import com.kd.apps.KLog;
import com.kd.apps.RespKey;
import com.kd.apps.Utils;
import com.kd.apps.exception.InternalServerException;
import com.kd.apps.exception.ServerException;
import com.kd.apps.model.BaseEntity;
import com.kd.apps.model.RespDeletedEntity;
import com.kd.apps.model.RespMonoEntity;
import com.kd.apps.model.RespMultiEntity;
import com.kd.apps.repo.BaseRepo;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <E>
 * @param <ID>
 */
public class BaseSrvc<E extends BaseEntity<E, ID>, ID extends Serializable> {

	protected final KLog logger = new KLog(this.getClass());
	protected BaseRepo<E, ID> entityRepo;
	protected String entityname;
	protected String methodname = "methodnull";

	@Autowired
	protected Utils utils;

	public BaseSrvc() {

	}

	public BaseSrvc(BaseRepo<E, ID> entityRepo, String entityname) {
		super();
		this.entityRepo = entityRepo;
		this.entityname = entityname;
	}

	public Log getLogger() {
		return logger;
	}

	public BaseRepo<E, ID> getEntityRepo() {
		return entityRepo;
	}

	public void setEntityRepo(BaseRepo<E, ID> entityRepo) {
		this.entityRepo = entityRepo;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public Utils getUtils() {
		return utils;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public RespMonoEntity<E> create(String logid, E entity) throws ServerException {
		methodname = "create";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ entity.toString());
		RespMonoEntity<E> respMonoEntity = new RespMonoEntity<E>(entityname);
		respMonoEntity.setHttpstatus(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			if (null != entity.getId() && entityRepo.exists((ID) entity.getId())) {
				throw new DataIntegrityViolationException("Data Integrity Violation");
			} else {
				entity.setCreated(new Date());
				E savedEntity = entityRepo.save(entity);
				respMonoEntity.setEntity(savedEntity);
				respMonoEntity.setHttpstatus(HttpStatus.OK);
				respMonoEntity.setRespmsgkey(RespKey.CREATE_OK.getKey());// Creation
																			// success
				respMonoEntity.setRespmsgval(RespKey.CREATE_OK.getMsg());
			}
		} catch (DataIntegrityViolationException e) {
			throw new InternalServerException(RespKey.CREATE_DUPLICATE_KEY.getKey(),
					RespKey.CREATE_DUPLICATE_KEY.getMsg());
		} catch (JpaSystemException e) {
			// JPASystemException propagated by HibernateException
			throw new InternalServerException(RespKey.CREATE_JPA_EX.getKey(), RespKey.CREATE_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			// DB Connection Unavailable
			throw new InternalServerException(RespKey.CREATE_DB_CONN_BAD.getKey(), RespKey.CREATE_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			// Unknown Error
			throw new InternalServerException(RespKey.CREATE_SYS_ERR.getKey(), RespKey.CREATE_SYS_ERR.getMsg());
		}
		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ respMonoEntity.toString());
		return respMonoEntity;
	}

	public RespMonoEntity<E> read(String logid, ID id) throws ServerException {
		methodname = "read";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getjson("id", id));
		E entity = null;
		RespMonoEntity<E> respMonoEntity = new RespMonoEntity<E>(entityname);
		try {
			entity = entityRepo.findOne(id);
			entity.checknull();// to check if entity is not null
			respMonoEntity.setEntity(entity);
			respMonoEntity.setHttpstatus(HttpStatus.OK);
			respMonoEntity.setRespmsgkey(RespKey.READ_OK.getKey()); // read
																	// success
			respMonoEntity.setRespmsgval(RespKey.READ_OK.getMsg());
		} catch (NullPointerException e) {
			// Entity not found
			throw new InternalServerException(RespKey.READ_NO_RECORD.getKey(), RespKey.READ_NO_RECORD.getMsg());
		} catch (JpaSystemException e) {
			// JPASystemException propagated by HibernateException
			throw new InternalServerException(RespKey.READ_JPA_EX.getKey(), RespKey.READ_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			// DB Connection Unavailable
			throw new InternalServerException(RespKey.READ_DB_CONN_BAD.getKey(), RespKey.READ_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			// Unknown Error
			throw new InternalServerException(RespKey.READ_SYS_ERR.getKey(), RespKey.READ_SYS_ERR.getMsg());
		}
		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ respMonoEntity.toString());
		return respMonoEntity;
	}

	public RespMultiEntity<E> readAll(String logid) throws ServerException {
		methodname = "readAll";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname));
		RespMultiEntity<E> respMultiEntity = new RespMultiEntity<E>(entityname);
		try {
			Iterable<E> entities = entityRepo.findAll();
			if (null != entities) {
				respMultiEntity.setEntities(entities);
				respMultiEntity.setHttpstatus(HttpStatus.OK);
				respMultiEntity.setRespmsgkey(RespKey.READALL_OK.getKey()); // read
																			// success
				respMultiEntity.setRespmsgval(RespKey.READALL_OK.getMsg());
			} else {
				throw new InternalServerException(RespKey.READALL_NO_RECORD.getKey(),
						RespKey.READALL_NO_RECORD.getMsg());
			}
		} catch (InternalServerException ex) {
			throw ex;
		} catch (JpaSystemException e) {
			throw new InternalServerException(RespKey.READALL_JPA_EX.getKey(), RespKey.READALL_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			throw new InternalServerException(RespKey.READALL_DB_CONN_BAD.getKey(),
					RespKey.READALL_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			throw new InternalServerException(RespKey.READALL_SYS_ERR.getKey(), RespKey.READALL_SYS_ERR.getMsg());
		}
		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ respMultiEntity.toString());
		return respMultiEntity;
	}

	public RespMultiEntity<E> readAllPageable(String logid, Pageable pageable) throws ServerException {
		methodname = "readAllPageable";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getpaginationstr(pageable));
		RespMultiEntity<E> respMultiEntity = new RespMultiEntity<E>(entityname);
		try {
			Iterable<E> entities = entityRepo.findAll(pageable);
			if (null != entities) {
				respMultiEntity.setEntities(entities);
				respMultiEntity.setHttpstatus(HttpStatus.OK);
				respMultiEntity.setRespmsgkey(RespKey.READ_PGBL_OK.getKey()); // read
																				// success
				respMultiEntity.setRespmsgval(RespKey.READ_PGBL_OK.getMsg());
			} else {
				throw new InternalServerException(RespKey.READ_PGBL_NO_RECORD.getKey(),
						RespKey.READ_PGBL_NO_RECORD.getMsg());
			}
		} catch (InternalServerException ex) {
			throw ex;
		} catch (JpaSystemException e) {
			throw new InternalServerException(RespKey.READ_PGBL_JPA_EX.getKey(), RespKey.READ_PGBL_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			throw new InternalServerException(RespKey.READ_PGBL_DB_CONN_BAD.getKey(),
					RespKey.READ_PGBL_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			throw new InternalServerException(RespKey.READ_PGBL_SYS_ERR.getKey(), RespKey.READ_PGBL_SYS_ERR.getMsg());
		}
		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getpaginationstr(pageable) + respMultiEntity.toString());
		return respMultiEntity;
	}

	public RespMonoEntity<E> update(String logid, ID id, E tobemerged) throws ServerException {
		methodname = "update";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getjson("id", id) + tobemerged.toString());
		RespMonoEntity<E> respMonoEntity = new RespMonoEntity<E>(entityname);
		try {
			E origEntity = entityRepo.findOne(id);
			origEntity.checknull();
			E entity = entityRepo.save(origEntity.mergeUpdates(tobemerged));
			respMonoEntity.setEntity(entity);
			respMonoEntity.setHttpstatus(HttpStatus.OK);
			respMonoEntity.setRespmsgkey(RespKey.UPDATE_OK.getKey());// Update
																		// success
			respMonoEntity.setRespmsgval(RespKey.UPDATE_OK.getMsg());
		} catch (NullPointerException e) {
			throw new InternalServerException(RespKey.UPDATE_NO_RECORD.getKey(), RespKey.UPDATE_NO_RECORD.getMsg());
		} catch (JpaSystemException e) {
			throw new InternalServerException(RespKey.UPDATE_JPA_EX.getKey(), RespKey.UPDATE_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			throw new InternalServerException(RespKey.UPDATE_DB_CONN_BAD.getKey(), RespKey.UPDATE_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			throw new InternalServerException(RespKey.UPDATE_SYS_ERR.getKey(), RespKey.UPDATE_SYS_ERR.getMsg());
		}

		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ respMonoEntity.toString());
		return respMonoEntity;
	}

	public RespDeletedEntity delete(String logid, ID id) throws ServerException {
		methodname = "delete";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getjson("id", id));
		RespDeletedEntity respDeletedEntity = new RespDeletedEntity(entityname);
		respDeletedEntity.setEntityId(id.toString());
		try {
			entityRepo.delete(id);
			respDeletedEntity.setIsdeleted(true);
			respDeletedEntity.setHttpstatus(HttpStatus.OK);
			respDeletedEntity.setRespmsgkey(RespKey.DELETE_OK.getKey());// Delete
																		// Success
			respDeletedEntity.setRespmsgval(RespKey.DELETE_OK.getMsg());
		} catch (NullPointerException e) {
			throw new InternalServerException(RespKey.DELETE_NO_RECORD.getKey(), RespKey.DELETE_NO_RECORD.getMsg());
		} catch (JpaSystemException e) {
			throw new InternalServerException(RespKey.DELETE_JPA_EX.getKey(), RespKey.DELETE_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			throw new InternalServerException(RespKey.DELETE_DB_CONN_BAD.getKey(), RespKey.DELETE_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			throw new InternalServerException(RespKey.DELETE_SYS_ERR.getKey(), RespKey.DELETE_SYS_ERR.getMsg());
		}

		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ respDeletedEntity.toString());
		return respDeletedEntity;
	}

	public RespMultiEntity<E> readAllPageSearch(String logid, Pageable pageable, Specification<E> specification)
			throws ServerException {
		methodname = "readAllPageable";
		logger.debug(logid + utils.logstage("input") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getpaginationstr(pageable));
		RespMultiEntity<E> respMultiEntity = new RespMultiEntity<E>(entityname);
		try {
			Iterable<E> entities = entityRepo.findAll(specification, pageable);
			if (null != entities) {
				respMultiEntity.setEntities(entities);
				respMultiEntity.setHttpstatus(HttpStatus.OK);
				respMultiEntity.setRespmsgkey(RespKey.READ_PGBL_OK.getKey()); // read
																				// success
				respMultiEntity.setRespmsgval(RespKey.READ_PGBL_OK.getMsg());
			} else {
				throw new InternalServerException(RespKey.READ_PGBL_NO_RECORD.getKey(),
						RespKey.READ_PGBL_NO_RECORD.getMsg());
			}
		} catch (InternalServerException ex) {
			throw ex;
		} catch (JpaSystemException e) {
			throw new InternalServerException(RespKey.READ_PGBL_JPA_EX.getKey(), RespKey.READ_PGBL_JPA_EX.getMsg());
		} catch (CannotCreateTransactionException e) {
			throw new InternalServerException(RespKey.READ_PGBL_DB_CONN_BAD.getKey(),
					RespKey.READ_PGBL_DB_CONN_BAD.getMsg());
		} catch (Exception e) {
			throw new InternalServerException(RespKey.READ_PGBL_SYS_ERR.getKey(), RespKey.READ_PGBL_SYS_ERR.getMsg());
		}
		logger.debug(logid + utils.logstage("output") + utils.logmethod(methodname) + utils.logentity(entityname)
				+ utils.getpaginationstr(pageable) + respMultiEntity.toString());
		return respMultiEntity;
	}
}
