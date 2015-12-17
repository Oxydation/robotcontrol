/**
 * Created by Jasmin on 16.12.2015.
 */
public class ProportionalWallRunner extends ProportionalBaseController {
    private static int NUMBER_OF_MOTORS = 2;

    private static double[][] controllerMatrix = {
            // FRONT_RIGHT, MIDDLE_RIGHT, RIGHT, BACK_RIGHT, BACK_LEFT, LEFT, MIDDLE_LEFT, FRONT_LEF
            {0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, -1, -1}
    };

    /**
     * Constructor
     */
    public ProportionalWallRunner() {
        super();
        enableLightSensors();
        enableDistanceSensors();
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        ProportionalWallRunner controller = new ProportionalWallRunner();
        controller.run();
    }

    @Override
    public double[][] getMatrix() {
        return controllerMatrix;
    }

    @Override
    public double[] getConstantVector() {
        return new double[]{100, 100};
    }

    private double[] getTargetSensorValues() {
        return new double[]{0, 0, 0, 0, 0, 0, 0, 0, 110, 130, 150, 100, 100, 150, 130, 100};
    }

    @Override
    public double[] calcSpeed(double[] sensors) {
        double[] speeds = new double[NUMBER_OF_MOTORS];

        for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
            speeds[i] = 0.0;

            for (int sensor = 0; sensor < sensors.length; sensor++) {
                // Maybe make this line abstract because this is the part which changes
                if (sensor < 8) {
                    speeds[i] += getMatrix()[i][sensor] * normalizeLight(sensors[sensor] - getTargetSensorValues()[sensor]);
                } else {
                    speeds[i] += getMatrix()[i][sensor] * (sensors[sensor] - getTargetSensorValues()[sensor]);
                }
            }
            speeds[i] += getConstantVector()[i];
        }

        return speeds;
    }

}
