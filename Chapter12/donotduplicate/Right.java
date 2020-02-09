package Chapter12.donotduplicate;

/**
 * @author Tosca
 * @date 9/2/2020
 */
public class Right {
  private float errorThreshold;
  private RenderedOp image;

  public void scaleToOneDimension(float desiredDimension, float imageDimension) {
    if (Math.abs(desiredDimension - imageDimension) < errorThreshold) {
      return;
    }
    float scalingFactor = desiredDimension / imageDimension;
    scalingFactor = (float) (Math.floor(scalingFactor * 100) * 0.01f);

    replaceImage(new RenderedOp());
  }

  public synchronized void rotate(int degrees) {
    replaceImage(new RenderedOp());
  }

  private void replaceImage(RenderedOp renderedOp) {
    image.dispose();
    System.gc();
    image = renderedOp;
  }
}
