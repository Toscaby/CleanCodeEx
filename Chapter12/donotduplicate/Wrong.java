package Chapter12.donotduplicate;

/**
 * @author Tosca
 * @date 9/2/2020
 */
public class Wrong {
  private float errorThreshold;
  private RenderedOp image;

  public void scaleToOneDimension(float desiredDimension, float imageDimension) {
    if (Math.abs(desiredDimension - imageDimension) < errorThreshold) {
      return;
    }
    float scalingFactor = desiredDimension / imageDimension;
    scalingFactor = (float) (Math.floor(scalingFactor * 100) * 0.01f);

    RenderedOp newImage = new RenderedOp();
    image.dispose();
    System.gc();
    image = newImage;
  }

  public synchronized void rotate(int degrees) {
    RenderedOp newImage = new RenderedOp();
    image.dispose();
    System.gc();
    image = newImage;
  }
}
