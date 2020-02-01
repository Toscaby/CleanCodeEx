package Chapter14;

public class ArgsException extends Exception {
  private ErrorCode errorCode = ErrorCode.OK;
  private String errorParameter = "TILT";
  private char errorArgumentId = '\0';

  public ArgsException() {}

  public ArgsException(String msg) {
    super(msg);
  }

  public ArgsException(String msg, ErrorCode errorCode) {
    this(msg);
    this.errorCode = errorCode;
  }

  public ArgsException(String msg, ErrorCode errorCode, char errorArgumentId) {
    this(msg, errorCode);
    this.errorArgumentId = errorArgumentId;
  }

  public ArgsException(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public ArgsException(ErrorCode errorCode, String errorParameter) {
    this(errorCode);
    this.errorParameter = errorParameter;
  }

  public ArgsException(ErrorCode errorCode, String errorParameter, char errorArgumentId) {
    this(errorCode, errorParameter);
    this.errorArgumentId = errorArgumentId;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(ErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public char getErrorArgumentId() {
    return errorArgumentId;
  }

  public void setErrorArgumentId(char errorArgumentId) {
    this.errorArgumentId = errorArgumentId;
  }

  public String getErrorParameter() {
    return errorParameter;
  }

  public void setErrorParameter(String errorParameter) {
    this.errorParameter = errorParameter;
  }

  public String errorMessage() throws Exception {
    switch (errorCode) {
      case OK:
        throw new Exception("TILT: Should not get here.");
      case UNEXPECTED_ARGUMENT:
        return String.format("Argument -%c unexpected.", errorArgumentId);
      case MISSING_BOOLEAN:
        return String.format("Could not find boolean parameter for -%c.", errorArgumentId);
      case MISSING_STRING:
        return String.format("Could not find string parameter for -%c.", errorArgumentId);
      case MISSING_INTEGER:
        return String.format("Could not find integer parameter for -%c.", errorArgumentId);
      case INVALID_INTEGER:
        return String.format("Could not parse integer parameter for -%c.", errorParameter);
      case MISSING_DOUBLE:
        return String.format("Could not find double parameter for -%c.", errorArgumentId);
      case INVALID_DOUBLE:
        return String.format("Could not parse double parameter for -%c.", errorParameter);
    }
    return "";
  }

  public enum ErrorCode {
    OK,
    MISSING_BOOLEAN,
    MISSING_STRING,
    MISSING_INTEGER,
    INVALID_INTEGER,
    MISSING_DOUBLE,
    INVALID_DOUBLE,
    INVALID_ARGUMENT_NAME,
    INVALID_FORMAT,
    UNEXPECTED_ARGUMENT
  }
}
