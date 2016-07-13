package com.jeeves.analisys.manager.parsers;

import com.jeeves.core.search.ast.parsers.OverrideEqualsWithOutHashCode;
import com.jeeves.shared.Result;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Aleksandrov Oleg
 */
public class OverrideEqualsWithOutHashCodeTest extends ParserTest
{
    private final String key = "123.java";

    private Result run(final String code)
    {
        OverrideEqualsWithOutHashCode parser = new OverrideEqualsWithOutHashCode(init(code));

        return parser.execute(key);

    }

    @Test
    // проверка антипаттерн присутсвует
    public void testFoundAntipattern()
    {
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

        Assert.assertTrue(run(code).isAntipatternFound());
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

        Assert.assertFalse(run(code).isAntipatternFound());

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

        Assert.assertFalse(run(code).isAntipatternFound());
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

        Assert.assertFalse(run(code).isAntipatternFound());
    }
}
