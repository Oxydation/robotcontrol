/**
 * Created by Jasmin on 16.12.2015.
 */
public class ProportionalBasicReactive extends ProportionalBaseController {

    private static double[][] controllerMatrix = {
            {0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1}
    };

    public ProportionalBasicReactive() {
        super();
        enableLightSensors();
        enableDistanceSensors();
    }

    @Override
    public double[][] getMatrix() {
        return controllerMatrix;
    }

    @Override
    public double[] getConstantVector() {
        return new double[]{100, 100};
    }

    @Override
    public double[] getTargetSensorValues() {
        return new double[16];
    }

    public static void main(String[] args) {
        ProportionalBasicReactive controller = new ProportionalBasicReactive();
        controller.run();
    }
}
