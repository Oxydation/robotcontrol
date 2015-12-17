/**
 * Created by Mathias on 07/12/2015.
 */
public class ProportionalLightRunner extends ProportionalBaseController {
    private static int NUMBER_OF_MOTORS = 2;

    private static double[][] controllerMatrix = {
            {1, 1, 1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, -1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}};

    /**
     * Constructor
     */
    public ProportionalLightRunner() {
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
        ProportionalLightRunner controller = new ProportionalLightRunner();
        controller.run();
    }

    @Override
    public double[][] getMatrix() {
        return controllerMatrix;
    }

    @Override
    public double[] getConstantVector() {
        return new double[0];
    }

//    public double calcSingleSpeed(int k, double sensorValue){
//
//    }

    @Override
    public double[] calcSpeed(double[] sensors) {
        double[] speeds = new double[NUMBER_OF_MOTORS];

        for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
            speeds[i] = 0.0;

            for (int sensor = 0; sensor < sensors.length; sensor++) {
                if (sensor < 8) {
                    // Maybe make this line abstract because this is the part which changes
                    speeds[i] += getMatrix()[i][sensor] * normalizeLight(sensors[sensor]);
                }
            }
        }
        return speeds;
    }
}
