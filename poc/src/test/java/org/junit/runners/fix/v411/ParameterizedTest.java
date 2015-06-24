package org.junit.runners.fix.v411;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.RunWith;

public class ParameterizedTest {

    @RunWith(org.junit.runners.fix.v411.Parameterized.class)
    public static class PatchedParameterizedWithEscapeSequenceInNameTest_Junit411 {

        @org.junit.runners.fix.v411.Parameterized.Parameters(name = "{0}")
        public static Collection<Character[]> parameters() {

            final Set<Character> charactersToEscape = org.junit.runners.fix.v411.Parameterized.ESCAPE_SEQUENCES.keySet();
            final Character[][] parameters = new Character[charactersToEscape.size()][1];
            final Iterator<Character> it = charactersToEscape.iterator();

            int i = 0;
            while (it.hasNext()) {
                parameters[i++][0] = it.next();
            }

            return Arrays.asList(parameters);
        }

        public PatchedParameterizedWithEscapeSequenceInNameTest_Junit411(final Character character) {
        }

        @Test
        public void test() {
        }
    }

    @RunWith(org.junit.runners.Parameterized.class)
    public static class ParameterizedWithEscapeSequenceInNameTest {

        @org.junit.runners.Parameterized.Parameters(name = "{0}")
        public static Collection<Character[]> parameters() {

            final Set<Character> charactersToEscape = org.junit.runners.fix.v411.Parameterized.ESCAPE_SEQUENCES.keySet();
            final Character[][] parameters = new Character[charactersToEscape.size()][1];
            final Iterator<Character> it = charactersToEscape.iterator();

            int i = 0;
            while (it.hasNext()) {
                parameters[i++][0] = it.next();
            }

            return Arrays.asList(parameters);
        }

        public ParameterizedWithEscapeSequenceInNameTest(final Character character) {
        }

        @Test
        public void test() {
        }
    }

    @Test
    public void parameterizedTest_displaysEscapeSequencesInName_forJUnit411() {

        final Request request = Request.aClass(PatchedParameterizedWithEscapeSequenceInNameTest_Junit411.class);

        for (Description testMethodDescr : request.getRunner().getDescription().getChildren()) {

            final int charCount = stripBrackets(testMethodDescr.toString()).length();
            assertEquals(2, charCount);
        }
    }

    @Test
    public void parameterizedTest_doesNotProperlyDisplayEscapeSequencesInName() {

        final Request request = Request.aClass(ParameterizedWithEscapeSequenceInNameTest.class);

        for (Description testMethodDescr : request.getRunner().getDescription().getChildren()) {

            final int charCount = stripBrackets(testMethodDescr.toString()).length();
            assertEquals(1, charCount);
        }
    }

    private String stripBrackets(final String value) {
        return value.replaceAll("\\[", "").replaceAll("\\]", "");
    }
}
