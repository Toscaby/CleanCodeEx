package fitnesse;

/**
 * @author Tosca
 * @date 27/1/2020
 */
public interface Responder {
  Response makeResponse(FitNesseContext context, Request request);
}
