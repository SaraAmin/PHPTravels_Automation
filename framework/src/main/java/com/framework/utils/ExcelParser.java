package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelParser {

	public static ArrayList<String[]> getStepsDetails(String scenarioPath, String sheetName) throws IOException {
		Workbook wb = null;
		ArrayList<String[]> steps = new ArrayList<>();
		FileInputStream scenarioFile = null;
		try {
			scenarioFile = new FileInputStream(new File(scenarioPath));

			wb = WorkbookFactory.create(scenarioFile);
			Sheet sheet = wb.getSheet(sheetName);

			// Array in format : Object, Action, TestData
			String[] stepData;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row step = sheet.getRow(i);
				stepData = new String[3];
				for (int k = 0; k < 3; k++) {
					stepData[k] = step.getCell(2 + k).toString().trim();
				}
				steps.add(stepData);
			}
			return steps;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scenarioFile != null) {
				scenarioFile.close();
			}
			if (wb != null) {
				wb.close();
			}
		}
		return new ArrayList<>();
	}

	public static Map<String, String[]> getObjects(String objRepoPath, String sheetName) throws IOException {
		Workbook wb = null;
		Map<String, String[]> objectsList = new HashMap<>();
		FileInputStream repoFile = null;
		try {
			repoFile = new FileInputStream(new File(objRepoPath));

			wb = WorkbookFactory.create(repoFile);
			Sheet sheet = wb.getSheet(sheetName);

			// Array in format : findby, identifierValue
			String[] objectData;

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				objectData = new String[2];
				Row row = sheet.getRow(i);
				for (int k = 0; k < 2; k++) {
					objectData[k] = row.getCell(k + 1).toString().trim();
				}
				objectsList.put(row.getCell(0).toString().trim(), objectData);
			}
			return objectsList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (repoFile != null) {
				repoFile.close();
			}
			if (wb != null) {
				wb.close();
			}
		}
		return new HashMap<>();
	}

	public static Map<String, String> getTestdata(String objRepoPath, String sheetName) throws IOException {
		Workbook wb = null;
		Map<String, String> dataList = new HashMap<>();
		FileInputStream repoFile = null;
		try {
			repoFile = new FileInputStream(new File(objRepoPath));

			wb = WorkbookFactory.create(repoFile);
			Sheet sheet = wb.getSheet(sheetName);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				dataList.put(row.getCell(0).toString().trim(), row.getCell(1).toString().trim());
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (repoFile != null) {
				repoFile.close();
			}
			if (wb != null) {
				wb.close();
			}
		}
		return new HashMap<>();
	}

	public static ArrayList<String> getKeywords(List<String[]> scenario) {
		ArrayList<String> keywords = new ArrayList<>();
		try {

			for (String[] step : scenario) {
				keywords.add(step[1]);
			}
			return keywords;

		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
