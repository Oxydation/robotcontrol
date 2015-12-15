/**
 * Created by Mathias on 07/12/2015.
 */
public class ProportionalLightRunner extends BaseController {
    private static int K_STEERING = 10;
    private static int NUMBER_OF_MOTORS = 2;

    private static double[][] controllerMatrix = {
            {1,1, 1, 0, -1, 0, 0, 0},
            {0, 0, 0, -1, 0, 1, 1, 1}};

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
                    _lightSensors[S_MIDDLE_RIGHT].getValue(),
                    _lightSensors[S_RIGHT].getValue(),
                    _lightSensors[S_BACK_RIGHT].getValue(),
                    _lightSensors[S_BACK_LEFT].getValue(),
                    _lightSensors[S_LEFT].getValue(),
                    _lightSensors[S_MIDDLE_LEFT].getValue(),
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
                speeds[i] += controllerMatrix[i][sensor] * normalizeLight(sensors[sensor]);
            }
        }
        return speeds;
    }

    private double normalizeLight(double light){
        //max und min light eventl. noch anpassen
        int maxLight = 50;
        int minLight = 4000;
        double output = 1000 - ((light - maxLight) *  1000) / (minLight - maxLight);
        if(output < -1000){
            output = 0;
        }
        if(output > 1000){
            output = 1000;
        }
        return output;
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
