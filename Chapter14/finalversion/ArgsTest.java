package Chapter14.finalversion;

import Chapter14.ArgsException;
import junit.framework.TestCase;

/**
 * @author Tosca
 * @date 1/2/2020
 */
public class ArgsTest extends TestCase {
  public void testCreateWithNoSchemaOrArguments() throws Exception {
    Args args = new Args("", new String[0]);
    assertEquals(0, args.cardinality());
  }

  public void testWithNoSchemaButWithOneArgument() throws Exception {
    try {
      new Args("", new String[]{"-x"});
      fail();
    } catch (ArgsException e) {
      assertEquals(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, e.getErrorCode());
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  public void testWithNoSchemaButWithMultipleArguments() throws Exception {
    try {
      new Args("", new String[]{"-x", "-y"});
      fail();
    } catch (ArgsException e) {
      assertEquals(ArgsException.ErrorCode.UNEXPECTED_ARGUMENT, e.getErrorCode());
      assertEquals('x', e.getErrorArgumentId());
    }
  }

  public void testNonLetterSchema() throws Exception {
    try {
      new Args("$*", new String[]{});
      fail("Args constructor should have thrown exception");
    } catch (ArgsException e) {
      assertEquals(ArgsException.ErrorCode.INVALID_ARGUMENT_NAME, e.getErrorCode());
      assertEquals('*', e.getErrorArgumentId());
    }
  }

  public void testInvalidArgumentFormat() throws Exception {
    try {
      new Args("f~", new String[]{});
      fail("Args constructor should have thrown exception");
    } catch (ArgsException e) {
      assertEquals(ArgsException.ErrorCode.INVALID_FORMAT, e.getErrorCode());
      assertEquals('~', e.getErrorArgumentId());
    }
  }

  public void testSimpleBooleanPresent() throws Exception {

  }

  public void testMultipleBooleanPresent() throws Exception {

  }

  public void testMissingBooleanPresent() throws Exception {

  }

  public void testSimpleStringPresent() throws Exception {

  }

  public void testMultipleStringPresent() throws Exception {

  }

  public void testMissingStringArgument() throws Exception {

  }

  public void testSimpleIntegerPresent() throws Exception {

  }

  public void testMultipleIntegerPresent() throws Exception {

  }

  public void testInvalidInteger() throws Exception {

  }

  public void testMissingIntegerArgument() throws Exception {

  }

  public void testSimpleDoublePresent() throws Exception {

  }

  public void testMultipleDoublePresent() throws Exception {

  }

  public void testDoubleInteger() throws Exception {

  }

  public void testMissingDoubleArgument() throws Exception {

  }

  public void testSpacesInFormat() throws Exception {

  }
}
