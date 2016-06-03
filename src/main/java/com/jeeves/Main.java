package com.jeeves;

import com.jeeves.core.preparation.CustomCodePreparation;
import com.jeeves.core.search.Parser;
import com.jeeves.core.search.regexp.parsers.UseReturnOnTryCatchFinally;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String key = "123.java";
        Map<String, String> map = new HashMap<>();
        String code = "        String key = \"123.java\";\n" +
                "        \n" +
                "        try {\n" +
                "            return;\n" +
                "        }\n" +
                "        catch (Exception e)\n" +
                "        {\n" +
                "            \n" +
                "        }\n" +
                "        finally {\n" +
                "            return;\n" +
                "        }";


        map.put(key, code);

        Parser parser = new UseReturnOnTryCatchFinally(new CustomCodePreparation(map));

        System.out.println(parser.execute(key).toString());
    }

}