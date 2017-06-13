package com.kd.apps.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kd.apps.model.EntityField;
import com.kd.apps.model.MicroServiceConfig;
/**
 * 
 * @author Myron Rogtao
 *
 */
public class KdUtilCodeGen {

	private static Charset charset = StandardCharsets.UTF_8;
	private static final String entityPackage = "com.kd.apps.model";
	private static final String seperator = System.getProperty("file.separator");

	/**
	 * Method checks for null or empty string
	 * 
	 * @param value
	 *            : String value to be evaluated
	 * @return true if null or empty else false
	 */
	public static boolean checkIfNullOrEmpty(String value) {

		if (value == null || value.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Method creates a file based on the input parameters
	 * 
	 * @param content
	 *            : Contents of the file
	 * @param outputFileDirectory
	 *            : Directory where the file will be generated
	 * @param fileName
	 *            : File name
	 * @param fileExtension
	 *            : File extension
	 * @throws Exception
	 */
	public static void createOutputFile(String content, String outputFileDirectory, String fileName,
			String fileExtension) throws Exception {

		if (outputFileDirectory != null) {
			String filePath = "";
			filePath = outputFileDirectory + seperator + fileName + fileExtension;
			createOutputFile(content, filePath);
		}
	}

	/**
	 * Method creates a file based on the input parameters
	 * 
	 * @param content
	 *            : Contents of the file
	 * @param filepath
	 *            : full file path. ../baseDirectory/filename.ext
	 * @throws Exception
	 */
	public static void createOutputFile(String content, String filepath) throws Exception {

		try {
			Path outputPath = Paths.get(filepath);
			Files.write(outputPath, content.getBytes(charset));
		} catch (Exception ex) {
			throw new Exception("Unable to create output file : " + filepath, ex);
		}

	}

	/**
	 * Method reads contents of file based on the file path parameter
	 * 
	 * @param filepath
	 *            : full path of the file to be read.
	 * @return Contents of the file as string output
	 * @throws Exception
	 */
	public static String readFile(String filepath) throws Exception {
		try {
			Path path = Paths.get(filepath);
			return new String(Files.readAllBytes(path), charset);
		} catch (Exception ex) {
			throw new Exception("Unable to read template file : " + filepath, ex);
		}
	}

	/**
	 * Method reads MicroServiceConfig JSON file from the specified location
	 * 
	 * @param configFile
	 *            : Location of the JSON configuration file
	 * @return MicroServiceConfig object holding configuration data
	 * @throws Exception
	 */
	public static MicroServiceConfig readConfiguration(String configFile) throws Exception {

		String configurationData = null;
		MicroServiceConfig config = null;
		if (configFile != null) {
			try {
				configurationData = readFile(configFile);
				Gson gson = new Gson();
				config = gson.fromJson(configurationData, MicroServiceConfig.class);
				return config;
			} catch (Exception ex) {
				throw new Exception("Unable to read configuration file: " + configFile, ex);
			}
		}
		return config;
	}

	/**
	 * Method loads Properties from the .properties file available at filePath
	 * 
	 * @param filePath
	 *            : .properties file path
	 * @return Properties object holding key-value pairs from the loaded
	 *         .properties file
	 * @throws Exception
	 */
	public static Properties loadProperties(String filePath) throws Exception {
		InputStream resourceInputStream = null;
		try {
			Properties prop = new Properties();
			resourceInputStream = new FileInputStream(filePath);
			prop.load(resourceInputStream);
			return prop;
		} catch (Exception ex) {
			throw new Exception("Unable to load properties file: " + filePath, ex);
		} finally {
			if (resourceInputStream != null)
				resourceInputStream.close();
		}
	}

	/**
	 * Method stores Properties to a .properties file.
	 * 
	 * @param filePath
	 *            : .properties file path
	 * @param properties
	 *            : List of Key-Value pairs to be stored
	 * @throws Exception
	 */
	public static void storeProperties(String filePath, Properties properties) throws Exception {
		OutputStream resourceOutputStream = null;
		try {
			resourceOutputStream = new FileOutputStream(filePath);
			properties.store(resourceOutputStream, null);
		} catch (Exception ex) {
			throw new Exception("Unable to Store properties file: " + filePath, ex);
		} finally {
			if (resourceOutputStream != null)
				resourceOutputStream.close();
		}
	}

	/**
	 * Method retrieves Fields associated with an Entity Class and its Parent in
	 * Hierarchy
	 * 
	 * @param entityName
	 *            : Entity class name
	 * @return List of EntityField objects holding entity name and its data type
	 *         information
	 * @throws Exception
	 */
	public static List<EntityField> retrieveEntityProperties(String entityName) throws Exception {
		List<EntityField> properties = null;
		if (entityName != null) {
			try {
				Class entityClass = Class.forName(entityPackage + "." + entityName);
				List<Field> fieldList = getAllFieldsInHierarchy(entityClass);
				if (fieldList != null) {
					properties = new ArrayList<EntityField>();
					for (Field field : fieldList) {
						// serialVersionUID filed is excluded
						if (!"serialVersionUID".equals(field.getName())) {
							EntityField entityField = new EntityField();
							entityField.setFieldName(field.getName());
							entityField.setFieldDataType(field.getType().getSimpleName());
							properties.add(entityField);
						}
					}
				}
			} catch (ClassNotFoundException e) {
				throw new Exception("Unable to load Entity class : " + entityName, e);
			}
		}
		return properties;
	}

	/**
	 * Method retrieves Id field Data type information for a given entity class
	 * 
	 * @param entityName
	 *            : Entity class name
	 * @return Data type of the Id field of the entity class
	 * @throws Exception
	 */
	public static String retrieveEntityIdType(String entityName) throws Exception {
		String idFieldType = null;

		if (entityName != null) {
			try {
				Class entityClass = Class.forName(entityPackage + "." + entityName);
				idFieldType = "Long";
				Field[] fields = entityClass.getDeclaredFields();
				if (fields != null) {
					for (Field field : fields) {
						if (field.getDeclaredAnnotationsByType(javax.persistence.Id.class).length > 0) {
							idFieldType = field.getType().getSimpleName();
						}
					}
				}
			} catch (ClassNotFoundException e) {
				throw new Exception("Unable to load Entity class : " + entityName, e);
			}
		}
		return idFieldType;
	}

	/**
	 * Method checks if the entity is available under com.kd.apps.model package
	 * 
	 * @param entityName
	 *            : Entity class name
	 * @return true if available else false
	 */
	public static boolean entityExists(String entityName) {

		if (entityName != null) {
			try {
				Class.forName(entityPackage + "." + entityName);
				return true;
			} catch (ClassNotFoundException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Method retrieves all Fields for a given classand its parents in hierarchy
	 * 
	 * @param type
	 *            : Class type
	 * @return List Fields for the entity class
	 */
	private static List<Field> getAllFieldsInHierarchy(Class<?> type) {

		List<Field> result = new ArrayList<Field>();
		while (type != null && type != Object.class) {
			Collections.addAll(result, type.getDeclaredFields());
			type = type.getSuperclass();
		}
		return result;
	}

	/**
	 * Method deletes directory if exists
	 * 
	 * @param directory
	 *            : Directory to be deleted
	 * @throws Exception
	 */
	public static void deleteDirectory(String directory) throws Exception {

		File outFile = new File(directory);
		if (outFile.exists()) {
			FileUtils.deleteDirectory(outFile);
		}
	}

	/**
	 * Method copies contents from one directory to another
	 * 
	 * @param sourceDir
	 *            : Source directory
	 * @param destDir
	 *            : Destination directory
	 * @throws IOException
	 */
	public static void copyDirectory(String sourceDir, String destDir) throws IOException {

		File source = new File(sourceDir);
		File dest = new File(destDir);
		dest.mkdirs();

		FileUtils.copyDirectory(source, dest);
	}

	/**
	 * Method appends properties to an existing .properties file
	 * 
	 * @param filePath
	 *            : .properties file path
	 * @param propertyFileName
	 *            : Name of the .properties file
	 * @param propertiesToAdd
	 *            : Map holding Key-Value pairs to be appended to the
	 *            .properties file
	 * @throws Exception
	 */
	public static void appendProperties(String filePath, String propertyFileName,
			HashMap<String, String> propertiesToAdd) throws Exception {

		if (propertiesToAdd != null && propertiesToAdd.size() > 0) {
			String fullPath = filePath + seperator + propertyFileName;
			Properties properties = KdUtilCodeGen.loadProperties(fullPath);
			propertiesToAdd.forEach((key, value) -> properties.setProperty(key, value));
			KdUtilCodeGen.storeProperties(fullPath, properties);
		}
	}

	/**
	 * Method generates Model data based on the property list
	 * 
	 * @param properties
	 *            : List of EntityField objects holding field info
	 * @return string holding the generated model field data
	 */
	public static String generateModelFieldData(List<EntityField> properties) {

		if (properties != null) {
			String propertBuilder = "";
			for (int i = 0; i < properties.size(); i++) {
				if (i < properties.size() - 1) {
					propertBuilder = propertBuilder.concat(properties.get(i).getFieldName() + ": null,\r\n\t\t\t\t");
				} else {
					propertBuilder = propertBuilder.concat(properties.get(i).getFieldName() + ": null");
				}
			}
			return propertBuilder;
		}
		return null;
	}

	/**
	 * Method converts Object to JSON string
	 * 
	 * @param object
	 *            : Input object to be converted
	 * @return JSON representation of the Object as string
	 */
	public static String toJSON(Object object) {

		String jsonString = "";
		if (object != null) {
			jsonString = new GsonBuilder().setPrettyPrinting().create().toJson(object);
		}
		return jsonString;
	}

	/**
	 * Method capitalizes the first character of the input string data
	 * 
	 * @param data
	 *            : Input data
	 * @return capitalized string
	 */
	public static String capitalize(String data) {

		if (data != null && !data.trim().equals("")) {
			data = data.substring(0, 1).toUpperCase() + data.substring(1);
		}
		return data;
	}

	/**
	 * Method checks if file exists at the specified location
	 * 
	 * @param baseDir
	 *            : Base directory of the file
	 * @param fileName
	 *            : Name of the file
	 * @param fileExt
	 *            : file extension
	 * @return true if file present else false
	 */
	public static boolean entityFileExists(String baseDir, String fileName, String fileExt) {

		if (baseDir != null && fileName != null && fileExt != null) {
			String filePath = baseDir + seperator + fileName + fileExt;
			File file = new File(filePath);
			if (file.exists() && file.isFile()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method generates mergeUpdates method contents based on the entity class
	 * properties
	 * 
	 * @param properties
	 *            : List of EntityField objects holding class property
	 *            information
	 * @return string holding the generated mergeUpdates method contents
	 */
	public static String generateMergeUpdateContents(List<EntityField> properties) {

		String strTemplate = "\t\tthis.setMNAME((null != tobemerged.getMNAME() && !tobemerged.getMNAME().trim().equals(\"\"))? tobemerged.getMNAME() : this.getMNAME());\n";
		String nonStrTemplate = "\t\tthis.setMNAME((null != tobemerged.getMNAME() ? tobemerged.getMNAME() : this.getMNAME()));\n";

		if (properties != null) {
			String propertBuilder = "";
			for (EntityField entityField : properties) {
				String capFieldName = capitalize(entityField.getFieldName());
				if (entityField.getFieldDataType().equals(java.lang.String.class.getSimpleName())) {
					propertBuilder += strTemplate.replaceAll("MNAME", capFieldName);
				} else {
					propertBuilder += nonStrTemplate.replaceAll("MNAME", capFieldName);
				}
			}
			return propertBuilder;
		}
		return null;
	}
}
