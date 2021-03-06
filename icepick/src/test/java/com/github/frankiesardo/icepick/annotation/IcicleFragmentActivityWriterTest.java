package com.github.frankiesardo.icepick.annotation;

import javax.lang.model.element.TypeElement;
import java.io.StringWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IcicleFragmentActivityWriterTest {

    StringWriter stringWriter = new StringWriter();
    IcicleWriter icicleWriter = new IcicleFragmentActivityWriter(stringWriter, "$$Icicle");
    TypeElement classType = mock(TypeElement.class, RETURNS_DEEP_STUBS);

    Set<IcicleField> fields = new LinkedHashSet<IcicleField>();

    @Before
    public void setUp() throws Exception {
        IcicleField icicleField = new IcicleField("username", "java.lang.String", "String");
        when(classType.getQualifiedName().toString()).thenReturn("com.frankiesardo" + "." + "TestActivity");
        when(classType.getSimpleName().toString()).thenReturn("TestActivity");

        fields.add(icicleField);
    }

    @Test
    public void shouldWriteBundlePutAndGetIntoATemplate() throws Exception {
        icicleWriter.writeClass(classType, "com.frankiesardo.SuperClass", fields);

        assertEquals(SIMPLE_CLASS, stringWriter.toString());
    }

    static final String SIMPLE_CLASS = "package com.frankiesardo;\n" +
            "\n" +
            "final class TestActivity$$Icicle {\n" +
            "\n" +
            "  private static final String BASE_KEY = \"com.frankiesardo.TestActivity$$Icicle.\";\n" +
            "\n" +
            "  private TestActivity$$Icicle() {\n" +
            "  }\n" +
            "\n" +
            "  public static void saveInstanceState(TestActivity target, android.os.Bundle outState) {\n" +
            "    com.frankiesardo.SuperClass$$Icicle.saveInstanceState(target, outState);\n" +
            "    outState.putString(" + IcicleWriter.BASE_KEY + " + \"username\", target.username);\n" +
            "  }\n" +
            "\n" +
            "  public static void restoreInstanceState(TestActivity target, android.os.Bundle savedInstanceState) {\n" +
            "    if (savedInstanceState == null) {\n" +
            "      return;\n" +
            "    }\n" +
            "    target.username = savedInstanceState.getString(" + IcicleWriter.BASE_KEY + " + \"username\");\n" +
            "    com.frankiesardo.SuperClass$$Icicle.restoreInstanceState(target, savedInstanceState);\n" +
            "  }\n" +
            "}\n";
}
