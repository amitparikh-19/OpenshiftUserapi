package com.kd.apps;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.kd.apps.common.KdConstsCodeGen;
import com.kd.apps.common.KdUtilCodeGen;
import com.kd.apps.enums.GenerationFileType;
import com.kd.apps.enums.ModuleType;
import com.kd.apps.model.MicroServiceConfig;
import com.kd.apps.model.TemplateEntityConfig;
/**
 * 
 * @author Myron Rogtao
 *
 */
public class KdInitCodeGen {

	// Predefined input
	private static final String srvcTemplate = "templates/TemplateEntityService.txt";
	private static final String repoTemplate = "templates/TemplateEntityRepo.txt";
	private static final String ctrlrTemplate = "templates/TemplateEntityController.txt";
	private static final String modelTemplate = "templates/TemplateEntityModel.txt";
	private static final String apiPomTemplate = "templates/TemplateEntityApiPom.txt";
	private static final String srvcPomTemplate = "templates/TemplateEntitySrvcPom.txt";
	private static final String apiConfTemplate = "templates/TemplateEntityApiSecConf.txt";
	private static final String apiInitTemplate = "templates/TemplateEntityApiStart.txt";
	private static final String mergeUpdateTemplate = "templates/TemplateEntityMergeUpdate.txt";
	private static final String constTemplate = "templates/TemplateEntityConst.txt";
	private static final String respKeyTemplate = "templates/TemplateEntityRespKey.txt";
	private static final String apiTestTemplate = "templates/TemplateEntityApiTest.txt";
	private static final String apiStepTemplate = "templates/TemplateEntityApiTestSteps.txt";
	private static final String apiStoryTemplate = "templates/TemplateEntityApiTestStory.txt";
	private static final String apiSkeleton = "templates/apiSkeleton";
	private static final String srvcSkeleton = "templates/srvcSkeleton";
	private static final String uiSkeleton = "templates/uiSkeleton";
	private static final String testSkeleton = "templates/testSkeleton";

	private static final String baseUrl = "/src/main";
	private static final String packageBaseUrl = baseUrl + "/java/com/kd/apps";
	private static final String entityModelBaseUrl = packageBaseUrl + "/model";
	private static final String methodUpdateRegex = "@Override\\s+\\S*\\s+public\\s+\\S+\\s+mergeUpdates\\s*\\(([^)]*)\\)\\s*\\{\\s*([^}]*)\\s*\\}";

	// Changeable input
	private static String outputdirectory = null;
	private static String configfile = null;
	private static String entityBaseDir = null;

	private static Properties applicationProperties;
	private static final String seperator = System.getProperty("file.separator");

	public static void main(String[] args) throws Exception {
		try {
			System.out.println("Starting Code Generator...");
			MicroServiceConfig microServices = null;

			System.out.println("Reading application properties...");
			applicationProperties = KdUtilCodeGen.loadProperties(KdConstsCodeGen.APP_PROP_FILE);
			outputdirectory = applicationProperties.getProperty(KdConstsCodeGen.OUTPUT_DIRECTORY);
			configfile = applicationProperties.getProperty(KdConstsCodeGen.CONFIG_FILE);
			entityBaseDir = applicationProperties.getProperty(KdConstsCodeGen.ENTITY_BASE_DIR);
			System.out.println("Successfully read application properties.");

			File outputFileDirectory = new File(outputdirectory);
			if (outputFileDirectory.exists())
				FileUtils.cleanDirectory(new File(outputdirectory));

			System.out.println("Reading Microservice Configuration..");
			microServices = KdUtilCodeGen.readConfiguration(configfile);
			System.out.println("Microservice Configuration successfully read");

			if (microServices != null && microServices.getMicroServiceConfigs() != null
					&& !microServices.getMicroServiceConfigs().isEmpty()) {
				for (TemplateEntityConfig entityTemplate : microServices.getMicroServiceConfigs()) {
					try {
						if (validateEntityTemplateData(entityTemplate)) {
							generateCodeForEntity(entityTemplate);
						} else {
							System.err.println(
									"Invalid Entity configuration. Entity Name: " + entityTemplate.getEntityName());
						}
					} catch (Exception ex) {
						System.err.println("Error while generating code : " + ex.getMessage());
						cleanEntityOutput(entityTemplate);
					}
				}
			} else {
				System.err.println("Error in Microservice Configuration.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void cleanEntityOutput(TemplateEntityConfig entityTemplate) throws Exception {

		if (entityTemplate != null) {
			KdUtilCodeGen.deleteDirectory(entityTemplate.getApiModulePath());
			KdUtilCodeGen.deleteDirectory(entityTemplate.getUiModelPath());
			KdUtilCodeGen.deleteDirectory(entityTemplate.getSrvcModulePath());
		}
	}

	private static void generateCodeForEntity(TemplateEntityConfig entityTemplate) throws Exception {

		System.out.println("\n***************************************************");
		System.out.println("Generating code for Entity : " + entityTemplate.getEntityName());
		System.out.println("***************************************************");
		updateEntity(entityTemplate);

		System.out.println("\nEntity Configuration : ");
		System.out.println(KdUtilCodeGen.toJSON(entityTemplate));

		System.out.println("\nCreating output directory structure for Entity : " + entityTemplate.getEntityName());
		createDirectoryStructure(entityTemplate);
		System.out.println("Successfully created directory structire for Entity : " + entityTemplate.getEntityName());

		addDefaultApiAppProps(entityTemplate);

		System.out.println("\nGenerating output files for Entity : " + entityTemplate.getEntityName());
		System.out.println("Generating Repository File...");
		replaceAndCreateFile(repoTemplate, entityTemplate, entityTemplate.getRepoFilePath(),
				entityTemplate.getRepoName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Successfully generated Repository file.");

		// Generate Dependency Repo Files
		if (entityTemplate.getDependentEntities() != null && entityTemplate.getDependentEntities().size() > 0) {
			System.out.println("Generating Repository Files for Dependency Entities...");
			for (String dependentEntity : entityTemplate.getDependentEntities()) {
				System.out.println("Dependency Entity : " + dependentEntity);
				TemplateEntityConfig depEntityObj = new TemplateEntityConfig(dependentEntity,
						entityTemplate.getEntityName());
				updateEntity(depEntityObj);
				replaceAndCreateFile(repoTemplate, depEntityObj, depEntityObj.getRepoFilePath(),
						depEntityObj.getRepoName(), KdConstsCodeGen.JAVA_FILE_EXT);
				System.out.println("Successfully generated Dependency Repository file.");
			}
			System.out.println("All Dependency Repositories generated");
		}
		System.out.println("Generating Service File...");
		replaceAndCreateFile(srvcTemplate, entityTemplate, entityTemplate.getSrvcFilePath(),
				entityTemplate.getSrvcName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Service file generated.");

		System.out.println("Generating Controller File...");
		replaceAndCreateFile(ctrlrTemplate, entityTemplate, entityTemplate.getCtrlrFilePath(),
				entityTemplate.getCtrlrName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Controller file generated.");

		System.out.println("Generating Constant File...");
		replaceAndCreateFile(constTemplate, entityTemplate, entityTemplate.getConstFilePath(),
				entityTemplate.getConstFileName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Service file generated.");

		System.out.println("Generating Response Key File...");
		replaceAndCreateFile(respKeyTemplate, entityTemplate, entityTemplate.getRespKeyFilePath(),
				entityTemplate.getRespKeyFileName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Controller file generated.");

		System.out.println("Generating Service Security Config File...");
		replaceAndCreateFile(apiConfTemplate, entityTemplate, entityTemplate.getApiConfPath(),
				entityTemplate.getSrvcConfName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Service Security Config file generated.");

		System.out.println("Generating Service Init File...");
		replaceAndCreateFile(apiInitTemplate, entityTemplate, entityTemplate.getApiConfPath(),
				entityTemplate.getSrvcInitName(), KdConstsCodeGen.JAVA_FILE_EXT);
		System.out.println("Service Init file generated.");

		// Generate Service POM
		System.out.println("Generating Service Module POM File...");
		replaceAndCreateFile(srvcPomTemplate, entityTemplate, entityTemplate.getSrvcModulePath(),
				entityTemplate.getPomName(), KdConstsCodeGen.XML_FILE_EXT);
		System.out.println("Service POM file generated");

		System.out.println("Generating API Module POM File...");
		replaceAndCreateFile(apiPomTemplate, entityTemplate, entityTemplate.getApiModulePath(),
				entityTemplate.getPomName(), KdConstsCodeGen.XML_FILE_EXT);
		System.out.println("API POM file generated");

		System.out.println("Generating UI Model File...");
		replaceAndCreateFile(modelTemplate, entityTemplate, entityTemplate.getUiModelPath(),
				entityTemplate.getUiModelName(), KdConstsCodeGen.JS_FILE_EXT);
		System.out.println("UI Model file generated");

		generateTestFiles(entityTemplate);

		try {
			System.out.println("Updating Entity File to add mergeUpdates method...");
			regExReplaceAndUpdateFile(entityTemplate.getEntityAbsPath(), mergeUpdateTemplate, methodUpdateRegex,
					entityTemplate);
			System.out.println("Entity File updated. Added mergeUpdates method.");
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		System.out.println("\nSuccessfully generated Code for Entity : " + entityTemplate.getEntityName());
	}

	private static void generateTestFiles(TemplateEntityConfig entityTemplate) throws Exception {

		System.out.println("Generating API Test Files...");
		replaceAndCreateFile(apiTestTemplate, entityTemplate, entityTemplate.getTestFilesPath(),
				entityTemplate.getApiTestFileName(), KdConstsCodeGen.JAVA_FILE_EXT);
		replaceAndCreateFile(apiStepTemplate, entityTemplate, entityTemplate.getTestFilesPath(),
				entityTemplate.getApiTestStepFileName(), KdConstsCodeGen.JAVA_FILE_EXT);
		replaceAndCreateFile(apiStoryTemplate, entityTemplate, entityTemplate.getTestFilesPath(),
				entityTemplate.getApiTestStoryFileName(), KdConstsCodeGen.STORY_FILE_EXT);
		System.out.println("API Test files generated");

	}

	private static void addDefaultApiAppProps(TemplateEntityConfig entityTemplate) throws Exception {

		HashMap<String, String> appendProps = new HashMap<String, String>();
		appendProps.put(KdConstsCodeGen.API_NAME_PROP, entityTemplate.getApiModuleName());

		KdUtilCodeGen.appendProperties(entityTemplate.getApiResourcePath(), KdConstsCodeGen.APP_PROP_FILE, appendProps);

	}

	private static void updateEntity(TemplateEntityConfig entityTemplate) throws Exception {

		entityTemplate.setEntityRefName(entityTemplate.getEntityName().toLowerCase());
		entityTemplate.setProperties(KdUtilCodeGen.retrieveEntityProperties(entityTemplate.getEntityName()));
		entityTemplate.setEntityIdType(KdUtilCodeGen.retrieveEntityIdType(entityTemplate.getEntityName()));
		if (!entityTemplate.isDependentEntity()) {
			entityTemplate.setParentEntityName(entityTemplate.getEntityName());
			entityTemplate.setParentEntityRef(entityTemplate.getEntityRefName());
		}
		entityTemplate.setApiModuleName(entityTemplate.getParentEntityRef() + ModuleType.API.getValue());
		entityTemplate.setSrvcModuleName(entityTemplate.getParentEntityRef() + ModuleType.SRVC.getValue());
		entityTemplate.setUiModuleName(entityTemplate.getParentEntityRef() + ModuleType.UI.getValue());
		entityTemplate.setTestModuleName(entityTemplate.getParentEntityRef() + ModuleType.TEST.getValue());

		entityTemplate.setApiModulePath(outputdirectory + seperator + entityTemplate.getApiModuleName());
		entityTemplate.setSrvcModulePath(outputdirectory + seperator + entityTemplate.getSrvcModuleName());
		entityTemplate.setUiModulePath(outputdirectory + seperator + entityTemplate.getUiModuleName());
		entityTemplate.setTestModulePath(outputdirectory + seperator + entityTemplate.getTestModuleName());

		entityTemplate.setCtrlrFilePath(entityTemplate.getApiModulePath() + packageBaseUrl + "/ctrlr");
		entityTemplate.setSrvcFilePath(entityTemplate.getSrvcModulePath() + packageBaseUrl + "/srvc");
		entityTemplate.setRepoFilePath(entityTemplate.getSrvcModulePath() + packageBaseUrl + "/repo");
		entityTemplate.setApiConfPath(entityTemplate.getApiModulePath() + packageBaseUrl);
		entityTemplate.setApiResourcePath(entityTemplate.getApiModulePath() + baseUrl + "/resources");
		entityTemplate.setUiModelPath(entityTemplate.getUiModulePath() + "/model");
		entityTemplate.setTestFilesPath(entityTemplate.getTestModulePath() + "/testfiles");
		entityTemplate.setConstFilePath(entityTemplate.getSrvcModulePath() + packageBaseUrl);
		entityTemplate.setRespKeyFilePath(entityTemplate.getSrvcModulePath() + packageBaseUrl);

		entityTemplate.setCtrlrName(entityTemplate.getEntityName() + GenerationFileType.CONRTOLLER.getValue());
		entityTemplate.setRepoName(entityTemplate.getEntityName() + GenerationFileType.REPO.getValue());
		entityTemplate.setSrvcName(entityTemplate.getEntityName() + GenerationFileType.SERVICE.getValue());
		entityTemplate.setSrvcConfName(entityTemplate.getEntityName() + GenerationFileType.SEC_CONF.getValue());
		entityTemplate.setSrvcInitName(entityTemplate.getEntityName() + GenerationFileType.API_INIT.getValue());
		entityTemplate.setUiModelName(entityTemplate.getEntityName() + GenerationFileType.MODEL.getValue());
		entityTemplate.setConstFileName(entityTemplate.getEntityName() + GenerationFileType.CONST.getValue());
		entityTemplate.setRespKeyFileName(entityTemplate.getEntityName() + GenerationFileType.RESP_KEY.getValue());
		entityTemplate.setApiTestFileName(entityTemplate.getEntityName() + GenerationFileType.APITEST.getValue());
		entityTemplate
				.setApiTestStepFileName(entityTemplate.getEntityName() + GenerationFileType.APITESTSTEP.getValue());
		entityTemplate.setApiTestStoryFileName(entityTemplate.getApiTestStepFileName());
		entityTemplate.setEntityAbsPath(entityBaseDir + entityModelBaseUrl + seperator + entityTemplate.getEntityName()
				+ KdConstsCodeGen.JAVA_FILE_EXT);

		entityTemplate.setModelFieldData(KdUtilCodeGen.generateModelFieldData(entityTemplate.getProperties()));
		entityTemplate
				.setMergeUpdateContents(KdUtilCodeGen.generateMergeUpdateContents(entityTemplate.getProperties()));
	}

	private static void createDirectoryStructure(TemplateEntityConfig entityTemplate) throws Exception {

		try {

			KdUtilCodeGen.copyDirectory(apiSkeleton, entityTemplate.getApiModulePath());
			KdUtilCodeGen.copyDirectory(srvcSkeleton, entityTemplate.getSrvcModulePath());
			KdUtilCodeGen.copyDirectory(uiSkeleton, entityTemplate.getUiModulePath());
			KdUtilCodeGen.copyDirectory(testSkeleton, entityTemplate.getTestModulePath());

		} catch (Exception ex) {
			throw new Exception("Unable to create Directory structure for entity :" + entityTemplate, ex);
		}
	}

	private static void replaceAndCreateFile(String templateFilePath, TemplateEntityConfig entityTemplate,
			String outputFileDirectory, String outputFileName, String fileExtension) throws Exception {

		String content = null;
		try {
			content = replaceContents(templateFilePath, entityTemplate);
		} catch (Exception ex) {
			throw new Exception("Unable to replace Template content.\nTemplate File : " + templateFilePath
					+ "\nEntity :" + entityTemplate.getEntityName(), ex);
		}

		if (content != null) {
			KdUtilCodeGen.createOutputFile(content, outputFileDirectory, outputFileName, fileExtension);
		}
	}

	private static boolean validateEntityTemplateData(TemplateEntityConfig entityTemplate) {

		if (entityTemplate != null) {
			if (KdUtilCodeGen.checkIfNullOrEmpty(entityTemplate.getEntityName())) {
				System.err.println("Entity Name null or empty");
				return false;
			}
			String entityBase = entityBaseDir + entityModelBaseUrl;
			if (!KdUtilCodeGen.entityFileExists(entityBase, entityTemplate.getEntityName(),
					KdConstsCodeGen.JAVA_FILE_EXT)) {
				System.err.println("Entity File not found at location " + entityBaseDir + entityModelBaseUrl);
				return false;
			}

			if (!KdUtilCodeGen.entityExists(entityTemplate.getEntityName())) {
				System.err.println("Entity Not Found. Configured Entity Name: " + entityTemplate.getEntityName());
				return false;
			}
			if (entityTemplate.getDependentEntities() != null && entityTemplate.getDependentEntities().size() > 0) {
				for (String entity : entityTemplate.getDependentEntities()) {
					if (!KdUtilCodeGen.entityExists(entity)) {
						System.err.println("Dependent Entity Not Found. Configured Dependent Entity Name: " + entity);
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

	private static void regExReplaceAndUpdateFile(String sourceFile, String templateFilePath, String regEx,
			TemplateEntityConfig entityTemplate) throws Exception {
		String content = null;
		try {
			content = replaceContents(templateFilePath, entityTemplate);
		} catch (Exception ex) {
			throw new Exception("Unable to replace Template content.\nTemplate File : " + templateFilePath
					+ "\nEntity :" + entityTemplate.getEntityName(), ex);
		}

		if (content != null) {
			String sourceContent = KdUtilCodeGen.readFile(sourceFile);
			if (sourceContent != null) {
				Pattern pattern = Pattern.compile(regEx);
				Matcher matcher = pattern.matcher(sourceContent);
				int startIndex = -1;
				int endIndex = -1;
				while (matcher.find()) {
					startIndex = matcher.start();
					endIndex = matcher.end();
					break;
				}

				StringBuilder builder = new StringBuilder(sourceContent);
				if (startIndex != -1 && endIndex != -1) {
					builder.replace(startIndex, endIndex, content);
				} else {
					int insertIndex = builder.lastIndexOf("}") - 1;
					builder.insert(insertIndex, content);
				}
				sourceContent = builder.toString();
				KdUtilCodeGen.createOutputFile(sourceContent, sourceFile);
			}
		}
	}

	private static String replaceContents(String templateFilePath, TemplateEntityConfig entityTemplate)
			throws Exception {
		String content = null;

		content = KdUtilCodeGen.readFile(templateFilePath);
		content = content.replaceAll(KdConstsCodeGen.ID_PLACEHOLDER, entityTemplate.getEntityIdType());
		content = content.replaceAll(KdConstsCodeGen.ENTITY_NAME_PLACEHOLDER, entityTemplate.getEntityName());
		content = content.replaceAll(KdConstsCodeGen.ENTITY_REF_NAME_PLACEHOLDER, entityTemplate.getEntityRefName());
		content = content.replaceAll(KdConstsCodeGen.ENTITY_FIELDS_PLACEHOLDER, entityTemplate.getModelFieldData());
		content = content.replaceAll(KdConstsCodeGen.MERGE_UPDATE_PLACEHOLDER, entityTemplate.getMergeUpdateContents());

		return content;
	}
}