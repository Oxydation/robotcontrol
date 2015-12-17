/**
 * Created by Jasmin on 16.12.2015.
 */
public class ProportionalBallPusher extends ProportionalBaseController {
    private static int NUMBER_OF_MOTORS = 2;

    private static double[][] controllerMatrix = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0.5, 0.1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0.1, 0.5, 0.5, 0, 0, 0, 0, 0}
    };

    /**
     * Constructor
     */
    public ProportionalBallPusher() {
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
        ProportionalBallPusher controller = new ProportionalBallPusher();
        controller.run();
    }

    @Override
    public double[][] getMatrix() {
        return controllerMatrix;
    }

    @Override
    public double[] getConstantVector() {
        return new double[]{0, 0, 0, 0, 0, 0, 0, 0, 100, 50, 0, 0, 0, 0, 50, 100};
    }

    @Override
    public double[] calcSpeed(double[] sensors) {
        double[] speeds = new double[NUMBER_OF_MOTORS];

        for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
            speeds[i] = 0.0;
            for (int sensor = 0; sensor < sensors.length; sensor++) {
                // Maybe make this line abstract because this is the part which changes
                speeds[i] += getMatrix()[i][sensor] * normalizeLight(sensors[sensor]);
            }
            if (speeds[i] > 1000) {
                speeds[i] = 1000;
            }
        }
        return speeds;
    }

}
