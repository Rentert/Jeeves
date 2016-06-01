package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.preparation.CustomCodePreparation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class ParserTest
{
    private final String key = "123.java";

    protected CodePreparation init(final String code)
    {
        Map<String, String> map = new HashMap<>();
        map.put(key, code);

        return new CustomCodePreparation(map);
    }
}
