package com.jeeves.analisys.manager.parsers;

import com.jeeves.shared.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Aleksandrov Oleg
 */
public class OverrideEqualsWithOutHashCodeParserTest extends ParserTest
{
    private final String key = "123.java";

    private Result run(final String code) {
        ParserInterface parser = new OverrideEqualsWithOutHashCodeParser(init(code));

        return parser.execute(key);

    }

    @Test
    // проверка антипаттерн присутсвует
    public void testFoundAntipattern()
    {

        String code = "class A{\n" +
                "        @Override\n" +
                "        public boolean equals() {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "    }";

//        String code = " public class A {\n" +
//                "        public int a = 10;\n" +
//                "        public String str;\n" +
//                "\n" +
//                "        @Override\n" +
//                "        public boolean equals() {\n" +
//                "            return super.equals(obj);\n" +
//                "        }\n" +
//                "        \n" +
//                "        public A (int a, String str)\n" +
//                "        {\n" +
//                "            this.a = a;\n" +
//                "            this.str = str;\n" +
//                "        }\n" +
//                "\n" +
//                "        public A (String str, int a)\n" +
//                "        {\n" +
//                "            this.a = a;\n" +
//                "            this.str = str + String.valueOf(a);\n" +
//                "        }\n" +
//                "    }";

        Result result = run(code);

        Assert.assertTrue(result.isAntipatternFound());
    }

    @Test
    // проверка если нет equals
    public void testWithOutEquals()
    {

        String code = "    class A{\n" +
                "        @Override\n" +
                "        public int hashCode() {\n" +
                "            return super.hashCode();\n" +
                "        }\n" +
                "    }";

        Result result = run(code);

        Assert.assertFalse(result.isAntipatternFound());

    }

    @Test
    // проверка если нет ни equals и hasCode
    public void testWithOutEqualsAndHashCode()
    {

        String code = "    class A{\n" +
                "        @Override\n" +
                "        public int hash() {\n" +
                "            return 0;\n" +
                "        }\n" +
                "    }";

        Result result = run(code);

        Assert.assertFalse(result.isAntipatternFound());
    }

    @Test
    // проверка если оба метода есть
    public void testNotFoundAntipattern()
    {
        String code = "    class A{\n" +
                "\n" +
                "        @Override\n" +
                "        public boolean equals(Object obj) {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public int hashCode() {\n" +
                "            return super.hashCode();\n" +
                "        }\n" +
                "    }";

        Result result = run(code);

        Assert.assertFalse(result.isAntipatternFound());
    }

    @Test(expected = IllegalArgumentException.class)
    // проверки при аргумене равном null
    public void testWithNullData()
    {
        new OverrideEqualsWithOutHashCodeParser(null);
    }
}
