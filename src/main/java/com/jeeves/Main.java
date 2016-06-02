package com.jeeves;

import com.jeeves.core.preparation.CustomCodePreparation;
import com.jeeves.core.search.NotCorrectEqualsOverride;
import com.jeeves.core.search.Parser;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String key = "123.java";
        Map<String, String> map = new HashMap<>();
        String code = " public class A {\n" +
                "        public int a = 10;\n" +
                "        public String str;\n" +
                "\n" +
                "        @Override\n" +
                "        public boolean equals() {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "        \n" +
                "        public A (int a, String str)\n" +
                "        {\n" +
                "            this.a = a;\n" +
                "            this.str = str;\n" +
                "        }\n" +
                "\n" +
                "        public A (String str, int a)\n" +
                "        {\n" +
                "            this.a = a;\n" +
                "            this.str = str + String.valueOf(a);\n" +
                "        }\n" +
                "    }";

        map.put(key, code);

        Parser parser = new NotCorrectEqualsOverride(new CustomCodePreparation(map));

        System.out.println(parser.execute(key).toString());

    }

}