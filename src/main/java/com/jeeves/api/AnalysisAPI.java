package com.jeeves.api;

import com.jeeves.shared.Report;

import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public interface AnalysisAPI
{
    Report analysis (String fileName, Map<String, String> map);
}
