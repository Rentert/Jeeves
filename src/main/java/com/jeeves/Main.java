package com.jeeves;

import com.jeeves.core.preparation.CustomCodePreparation;
import com.jeeves.core.search.AST.Parsers.CreationManyConstrictorForMandatoryFields;
import com.jeeves.core.search.Parser;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String key = "123.java";
        Map<String, String> map = new HashMap<>();
        String code = "class A\n" +
                "    {\n" +
                "        public A(int a){\n" +
                "            \n" +
                "        }\n" +
                "        \n" +
                "        public A(String e, int y) {\n" +
                "            return 1;\n" +
                "        }\n" +
                "\n" +
                "    }";


        map.put(key, code);

        Parser parser = new CreationManyConstrictorForMandatoryFields(new CustomCodePreparation(map));

        System.out.println(parser.execute(key).toString());
    }

}