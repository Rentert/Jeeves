package com.jeeves.analisys.manager.parsers;

import com.jeeves.shared.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Aleksandrov Oleg
 */
public class NotCorrectEqualsOverrideParserTest extends ParserTest
{
    private final String key = "123.java";

    private Result run(final String code)
    {
        ParserInterface parser = new NotCorrectEqualsOverrideParser(init(code));

        return parser.execute(key);
    }

    @Test
    // чек с кодом в котором есть антипаттерн
    public void testWithAntiPattern ()
    {

        String code = "class A\n" +
                "    {\n" +
                "        public A(int a){\n" +
                "            \n" +
                "        }\n" +
                "        \n" +
                "        private int d(int d) {\n" +
                "            return 1;\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public boolean equals(int obj) {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "    }";

        Assert.assertTrue(run(code).isAntipatternFound());

    }

    @Test
    // чек с кодов в котором нет антиптатерно
    public void testWithOutAntiPattern ()
    {

        String code = "class A\n" +
                "    {\n" +
                "        public A(int a){\n" +
                "            \n" +
                "        }\n" +
                "        \n" +
                "        private int d(int d) {\n" +
                "            return 1;\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public boolean equals(Object obj) {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "    }";

        Assert.assertFalse(run(code).isAntipatternFound());

    }

    @Test
    // чек с кодом в котором нет функции equals
    public void testWithEquals ()
    {

        String code = "class A\n" +
                "    {\n" +
                "        public A(int a){\n" +
                "            \n" +
                "        }\n" +
                "        \n" +
                "        private int d(int d) {\n" +
                "            return 1;\n" +
                "        }\n" +
                "\n" +
                "        @Override\n" +
                "        public boolean equals(Object obj) {\n" +
                "            return super.equals(obj);\n" +
                "        }\n" +
                "    }";

        Assert.assertFalse(run(code).isAntipatternFound());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithNullData ()
    {
        new NotCorrectEqualsOverrideParser(null);
    }
}
