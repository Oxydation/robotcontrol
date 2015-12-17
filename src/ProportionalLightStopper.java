/**
 * Created by Jasmin on 15.12.2015.
 */
public class ProportionalLightStopper extends ProportionalBaseController {
    private static double[][] controllerMatrix = {
            // FRONT_RIGHT, MIDDLE_RIGHT, RIGHT, BACK_RIGHT, BACK_LEFT, LEFT, MIDDLE_LEFT, FRONT_LEFT
            {0, 0, 0, 0.5, 0, -1, -0.5, -1, 0, 0, 0, 0, 0, 0, 0, 0}, // Motor left
            {-1, -0.5, -1, 0, 0.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Motor right
    };

    public ProportionalLightStopper() {
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
        return new double[]{0, 0};
    }

    @Override
    public double[] getTargetSensorValues() {
        return new double[]{-2500, 0, 0, 0, 0, 0, 0, -2500, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public static void main(String[] args) {
        ProportionalLightStopper controller = new ProportionalLightStopper();
        controller.run();
    }
}
