package Chapter6.encapsulation;

/**
 * concrete vehicle interface
 * @author Tosca
 * @date 26/1/2020
 */
public interface VehicleI {
  // 我们可以可以根据接口推断出数据形态, 封装的并不完全
  double getFuelTankCapacityInGallons();
  double getGallonsOfGasoline();
}
