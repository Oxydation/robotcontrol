/**
 * Created by Jasmin on 16.12.2015.
 */
public class ProportionalWallRunner extends ProportionalBaseController {

    // Love: b)
    private static double[][] controllerMatrix = {
            // FRONT_RIGHT, MIDDLE_RIGHT, RIGHT, BACK_RIGHT, BACK_LEFT, LEFT, MIDDLE_LEFT, FRONT_LEFT
            {0, 0, 0, 0, 0, 0, 0, 0, 1, -1, -1, 1, 0, 0, 0, 0}, // Motor left
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, -1, -1} // Motor right
    };

    public ProportionalWallRunner() {
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
        return new double[]{0, 0, 0, 0, 0, 0, 0, 0, 110, 130, 150, 100, 100, 150, 130, 100};
    }

    public static void main(String[] args) {
        ProportionalWallRunner controller = new ProportionalWallRunner();
        controller.run();
    }
}
