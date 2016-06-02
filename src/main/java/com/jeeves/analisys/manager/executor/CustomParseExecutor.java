package com.jeeves.analisys.manager.executor;

import com.jeeves.analisys.manager.parsers.*;
import com.jeeves.core.preparation.CustomCodePreparation;
import com.jeeves.shared.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandrov Oleg
 */
public class CustomParseExecutor implements ParseExecutor
{
    // инициализируем все парсеры
    private List<ParserInterface> initParser (Map<String, String> map)
    {
        List<ParserInterface> parsers = new ArrayList<>();

        parsers.add(new UseOpenFieldsParser(new CustomCodePreparation(map)));
        parsers.add(new NotCorrectEqualsOverrideParser(new CustomCodePreparation(map)));
        parsers.add(new ConstructorArgDiffOnlyOrderParser(new CustomCodePreparation(map)));
        parsers.add(new CopyPastProgrammingParser(new CustomCodePreparation(map)));
        parsers.add(new CreationManyConstrictorForMandatoryFieldsParserInterface(new CustomCodePreparation(map)));
        parsers.add(new NotCorrectClassStringUseParserInterface(new CustomCodePreparation(map)));
        parsers.add(new NotCorrectClassStringUseParserInterface(new CustomCodePreparation(map)));
     //   parsers.add(new OverrideEqualsWithOutHashCodeParser(new CustomCodePreparation(map)));
        parsers.add(new UseReturnOnTryCatchFinallyParser(new CustomCodePreparation(map)));

        return parsers;
    }

    /**
     * функция execute возвращает в словаре результат
     * поиска антипаттернов, где ключ это имя анализируемого
     * файла, а значение это список результатов анализа
     * содержимого данного файла, на наличие в нем различных
     * антипаттернов.
     * Данная реализация выполняет последовательный запуск каждого из парсеров
     */
    @Override
    public Map<String, List<Result>> execute(Map<String, String> map)
    {
        List<ParserInterface> parsers = initParser(map);

        Map<String, List<Result>> result = new HashMap<>();

        map.forEach((key, value) ->
        {
            List<Result> results = new ArrayList<>();

            parsers.forEach(parser ->
            {
                results.add(parser.execute(key));
            });

            result.put(key, results);
        });


        return result;
    }

    public class A {
        public int a = 10;
        public String str;

        public boolean equals(A o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a1 = (A) o;

            if (a != a1.a) return false;
            return str != null ? str.equals(a1.str) : a1.str == null;
        }

        public A (int a, String str)
        {
            this.a = a;
            this.str = str;
        }

        public A (String str, int a)
        {
            this.a = a;
            this.str = str + String.valueOf(a);
        }
    }

    public static void main(String[] args) {
        ParseExecutor executor = new CustomParseExecutor();

        Map<String, String> map = new HashMap<>();
        String k = "123.java";
        String code = "    public class A {\n" +
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

        Map<String, List<Result>> result = executor.execute(map);

        result.forEach((key, value) ->
        {
            System.out.println(key);
            value.forEach(System.out::println);
        });

    }
}
