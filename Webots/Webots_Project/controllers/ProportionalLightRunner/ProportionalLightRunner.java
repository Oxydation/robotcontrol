/**
 * Created by Mathias on 07/12/2015.
 */
public class ProportionalLightRunner extends BaseController {
    private static int K_STEERING = 10;
    private static int NUMBER_OF_MOTORS = 2;
    private static int RANGE = 512;

    private static int[][] controllerMatrix = {
            {0, 0, 1, 1},
            {1, 1, 0, 0}};

    /**
     * Constructor
     */
    public ProportionalLightRunner() {
        super();
        enableLightSensors();
    }

    @Override
    public void run() {
        // Steering angle command = Ksteering * (reference distance – sonar distance)
        while (step(TIME_STEP) != -1) {
            double[] sensors = new double[]{
                    _lightSensors[S_FRONT_RIGHT].getValue(),
                    _lightSensors[S_RIGHT].getValue(),
                    _lightSensors[S_LEFT].getValue(),
                    _lightSensors[S_FRONT_LEFT].getValue()
            };
            double[] speeds = calcSpeed(sensors);
            setSpeed(speeds[0], speeds[1]);
        }
    }

    private double[] calcSpeed(double[] sensors) {
        double[] speeds = new double[NUMBER_OF_MOTORS];

        for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
            speeds[i] = 0.0;

            for (int sensor = 0; sensor < sensors.length; sensor++) {
                speeds[i] += controllerMatrix[i][sensor] * (sensors[sensor]);
            }
        }
        return speeds;
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        ProportionalLightRunner controller = new ProportionalLightRunner();
        controller.run();
    }

}
