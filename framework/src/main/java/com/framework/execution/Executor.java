package com.framework.execution;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.framework.utils.ExcelParser;

public class Executor {

	public void startExecution(String scenarioPath, String scenarioSheet) throws Exception {
		int count = 0;
		ArrayList<String[]> steps = ExcelParser.getStepsDetails(scenarioPath, scenarioSheet);
		ArrayList<String> keywords = ExcelParser.getKeywords(steps);
		for (String keyword : keywords) {
			String methodName = keyword.replace(" ", "").toLowerCase().trim();
			Method method = Class.forName("com.framework.execution.Actions").getMethod(methodName, String[].class);
			String[] step = steps.get(count);
			Object value = method.invoke(null, new Object[] { step });
			count++;
		}
	}
}
