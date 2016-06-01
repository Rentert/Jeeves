package com.jeeves.analisys.manager;

import com.jeeves.analisys.manager.parsers.*;
import com.jeeves.core.preparation.CodePreparation;
import com.jeeves.core.preparation.CustomCodePreparation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class APIUser {

    public static void main(String[] args) {
        List<ParserInterface> parsers = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        String k = "123.java";
        String code = " public class A {\n" +
                "        public int a = 10;\n" +
                "        public String str;\n" +
                "\n" +
                "        public boolean equals(A o) {\n" +
                "            if (this == o) return true;\n" +
                "            if (o == null || getClass() != o.getClass()) return false;\n" +
                "\n" +
                "            A a1 = (A) o;\n" +
                "\n" +
                "            if (a != a1.a) return false;\n" +
                "            return str != null ? str.equals(a1.str) : a1.str == null;\n" +
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
        map.put(k, code);

        CodePreparation codePreparation = new CustomCodePreparation(map);

        parsers.add(new UseOpenFieldsParser(codePreparation));
        parsers.add(new NotCorrectEqualsOverrideParser(new CustomCodePreparation(map)));
        parsers.add(new ConstructorArgDiffOnlyOrderParser(new CustomCodePreparation(map)));
        parsers.add(new CopyPastProgrammingParser(new CustomCodePreparation(map)));
        parsers.add(new CreationManyConstrictorForMandatoryFieldsParserInterface(codePreparation));
        parsers.add(new NotCorrectClassStringUseParserInterface(codePreparation));
        parsers.add(new NotCorrectEqualsUseParser(codePreparation));
        parsers.add(new OverrideEqualsWithOutHashCodeParser(new CustomCodePreparation(map)));
        parsers.add(new UseReturnOnTryCatchFinallyParser(codePreparation));

        parsers.forEach(parser -> {
            System.out.println(parser.execute(k).toString());
        });
    }
}
