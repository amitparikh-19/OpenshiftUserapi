package com.kd.apps.model;

import java.util.List;

import com.google.gson.annotations.Expose;
/**
 * 
 * @author Myron Rogtao
 *
 */
public class TemplateEntityConfig {

	private String entityName;

	@Expose(deserialize = false)
	private String entityRefName;

	@Expose(deserialize = false)
	private String entityIdType;

	private List<String> dependentEntities;

	@Expose(deserialize = false)
	private List<EntityField> properties;

	@Expose(deserialize = false)
	private String parentEntityName;

	@Expose(deserialize = false)
	private String parentEntityRef;

	@Expose(deserialize = false)
	private String apiModuleName;

	@Expose(deserialize = false)
	private String srvcModuleName;

	@Expose(deserialize = false)
	private String uiModuleName;

	@Expose(deserialize = false)
	private String testModuleName;

	@Expose(deserialize = false)
	private String apiModulePath;

	@Expose(deserialize = false)
	private String srvcModulePath;

	@Expose(deserialize = false)
	private String uiModulePath;

	@Expose(deserialize = false)
	private String testModulePath;

	@Expose(deserialize = false)
	private String ctrlrFilePath;

	@Expose(deserialize = false)
	private String srvcFilePath;

	@Expose(deserialize = false)
	private String repoFilePath;

	@Expose(deserialize = false)
	private String apiConfPath;

	@Expose(deserialize = false)
	private String apiResourcePath;

	@Expose(deserialize = false)
	private String uiModelPath;

	@Expose(deserialize = false)
	private String testFilesPath;

	@Expose(deserialize = false)
	private boolean isDependentEntity;

	@Expose(deserialize = false)
	private String ctrlrName;

	@Expose(deserialize = false)
	private String repoName;

	@Expose(deserialize = false)
	private String srvcName;

	@Expose(deserialize = false)
	private String srvcInitName;

	@Expose(deserialize = false)
	private String srvcConfName;

	@Expose(deserialize = false)
	private String pomName = "pom";

	@Expose(deserialize = false)
	private String modelFieldData;

	@Expose(deserialize = false)
	private String uiModelName;

	@Expose(deserialize = false)
	private String mergeUpdateContents;

	@Expose(deserialize = false)
	private String entityAbsPath;

	@Expose(deserialize = false)
	private String constFilePath;

	@Expose(deserialize = false)
	private String constFileName;

	@Expose(deserialize = false)
	private String respKeyFilePath;

	@Expose(deserialize = false)
	private String respKeyFileName;

	@Expose(deserialize = false)
	private String apiTestFileName;

	@Expose(deserialize = false)
	private String apiTestStepFileName;

	@Expose(deserialize = false)
	private String apiTestStoryFileName;

	public String getApiTestFileName() {
		return apiTestFileName;
	}

	public void setApiTestFileName(String apiTestFileName) {
		this.apiTestFileName = apiTestFileName;
	}

	public String getApiTestStepFileName() {
		return apiTestStepFileName;
	}

	public void setApiTestStepFileName(String apiTestStepFileName) {
		this.apiTestStepFileName = apiTestStepFileName;
	}

	public String getApiTestStoryFileName() {
		return apiTestStoryFileName;
	}

	public void setApiTestStoryFileName(String apiTestStoryFileName) {
		this.apiTestStoryFileName = apiTestStoryFileName;
	}

	public String getConstFilePath() {
		return constFilePath;
	}

	public void setConstFilePath(String constFilePath) {
		this.constFilePath = constFilePath;
	}

	public String getConstFileName() {
		return constFileName;
	}

	public void setConstFileName(String constFileName) {
		this.constFileName = constFileName;
	}

	public String getRespKeyFilePath() {
		return respKeyFilePath;
	}

	public void setRespKeyFilePath(String respKeyFilePath) {
		this.respKeyFilePath = respKeyFilePath;
	}

	public String getRespKeyFileName() {
		return respKeyFileName;
	}

	public void setRespKeyFileName(String respKeyFileName) {
		this.respKeyFileName = respKeyFileName;
	}

	public String getEntityAbsPath() {
		return entityAbsPath;
	}

	public void setEntityAbsPath(String entityAbsPath) {
		this.entityAbsPath = entityAbsPath;
	}

	public String getMergeUpdateContents() {
		return mergeUpdateContents;
	}

	public void setMergeUpdateContents(String mergeUpdateContents) {
		this.mergeUpdateContents = mergeUpdateContents;
	}

	public String getUiModelName() {
		return uiModelName;
	}

	public void setUiModelName(String uiModelName) {
		this.uiModelName = uiModelName;
	}

	public String getTestModuleName() {
		return testModuleName;
	}

	public void setTestModuleName(String testModuleName) {
		this.testModuleName = testModuleName;
	}

	public String getModelFieldData() {
		return modelFieldData;
	}

	public void setModelFieldData(String modelFieldData) {
		this.modelFieldData = modelFieldData;
	}

	public String getPomName() {
		return "pom";
	}

	public void setPomName(String pomName) {
		this.pomName = pomName;
	}

	public String getCtrlrName() {
		return ctrlrName;
	}

	public void setCtrlrName(String ctrlrName) {
		this.ctrlrName = ctrlrName;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getSrvcName() {
		return srvcName;
	}

	public void setSrvcName(String srvcName) {
		this.srvcName = srvcName;
	}

	public String getSrvcInitName() {
		return srvcInitName;
	}

	public void setSrvcInitName(String srvcInitName) {
		this.srvcInitName = srvcInitName;
	}

	public String getSrvcConfName() {
		return srvcConfName;
	}

	public void setSrvcConfName(String srvcConfName) {
		this.srvcConfName = srvcConfName;
	}

	public String getUiModelPath() {
		return uiModelPath;
	}

	public void setUiModelPath(String uiModelPath) {
		this.uiModelPath = uiModelPath;
	}

	public String getTestFilesPath() {
		return testFilesPath;
	}

	public void setTestFilesPath(String testFilesPath) {
		this.testFilesPath = testFilesPath;
	}

	public String getCtrlrFilePath() {
		return ctrlrFilePath;
	}

	public void setCtrlrFilePath(String ctrlrFilePath) {
		this.ctrlrFilePath = ctrlrFilePath;
	}

	public String getSrvcFilePath() {
		return srvcFilePath;
	}

	public void setSrvcFilePath(String srvcFilePath) {
		this.srvcFilePath = srvcFilePath;
	}

	public String getRepoFilePath() {
		return repoFilePath;
	}

	public void setRepoFilePath(String repoFilePath) {
		this.repoFilePath = repoFilePath;
	}

	public String getApiConfPath() {
		return apiConfPath;
	}

	public String getApiResourcePath() {
		return apiResourcePath;
	}

	public void setApiResourcePath(String apiResourcePath) {
		this.apiResourcePath = apiResourcePath;
	}

	public void setApiConfPath(String apiConfPath) {
		this.apiConfPath = apiConfPath;
	}

	public String getParentEntityRef() {
		return parentEntityRef;
	}

	public void setParentEntityRef(String parentEntityRef) {
		this.parentEntityRef = parentEntityRef;
	}

	public String getApiModulePath() {
		return apiModulePath;
	}

	public void setApiModulePath(String apiModulePath) {
		this.apiModulePath = apiModulePath;
	}

	public String getSrvcModulePath() {
		return srvcModulePath;
	}

	public void setSrvcModulePath(String srvcModulePath) {
		this.srvcModulePath = srvcModulePath;
	}

	public String getTestModulePath() {
		return testModulePath;
	}

	public void setTestModulePath(String testModulePath) {
		this.testModulePath = testModulePath;
	}

	public String getUiModulePath() {
		return uiModulePath;
	}

	public void setUiModulePath(String uiModulePath) {
		this.uiModulePath = uiModulePath;
	}

	public TemplateEntityConfig(String entityName, String parentEntityName) {
		this.parentEntityName = parentEntityName;
		this.parentEntityRef = parentEntityName.toLowerCase();
		this.entityName = entityName;
		this.isDependentEntity = true;
	}

	public boolean isDependentEntity() {
		return isDependentEntity;
	}

	public void setDependentEntity(boolean isDependentEntity) {
		this.isDependentEntity = isDependentEntity;
	}

	public String getApiModuleName() {
		return apiModuleName;
	}

	public void setApiModuleName(String apiModuleName) {
		this.apiModuleName = apiModuleName;
	}

	public String getSrvcModuleName() {
		return srvcModuleName;
	}

	public void setSrvcModuleName(String srvcModuleName) {
		this.srvcModuleName = srvcModuleName;
	}

	public String getUiModuleName() {
		return uiModuleName;
	}

	public void setUiModuleName(String uiModuleName) {
		this.uiModuleName = uiModuleName;
	}

	public String getParentEntityName() {
		return parentEntityName;
	}

	public void setParentEntityName(String parentEntityName) {
		this.parentEntityName = parentEntityName;
	}

	public List<EntityField> getProperties() {
		return properties;
	}

	public void setProperties(List<EntityField> properties) {
		this.properties = properties;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityRefName() {
		return entityRefName;
	}

	public void setEntityRefName(String entityRefName) {
		this.entityRefName = entityRefName;
	}

	public String getEntityIdType() {
		return entityIdType;
	}

	public void setEntityIdType(String entityIdType) {
		this.entityIdType = entityIdType;
	}

	public List<String> getDependentEntities() {
		return dependentEntities;
	}

	public void setDependentEntities(List<String> dependentEntities) {
		this.dependentEntities = dependentEntities;
	}
}
