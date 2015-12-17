/**
 * Created by Mathias on 07/12/2015.
 */
public class ProportionalLightRunner extends ProportionalBaseController {

    // Aggression
    private static double[][] controllerMatrix = {
            // FRONT_RIGHT, MIDDLE_RIGHT, RIGHT, BACK_RIGHT, BACK_LEFT, LEFT, MIDDLE_LEFT, FRONT_LEFT
            {1, 1, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // Motor left
            {0, 0, 0, -1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}}; // Motor right

    /**
     * Constructor
     */
    public ProportionalLightRunner() {
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
        return new double[16];
    }

    @Override
    public double[] getTargetSensorValues() {
        return new double[16];
    }

    public static void main(String[] args) {
        ProportionalLightRunner controller = new ProportionalLightRunner();
        controller.run();
    }
}
