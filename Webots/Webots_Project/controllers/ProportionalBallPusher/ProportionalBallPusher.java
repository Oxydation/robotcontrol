/**
 * Created by Jasmin on 16.12.2015.
 */
public class ProportionalBallPusher extends ProportionalBaseController {

    private static double[][] controllerMatrix = {
            // FRONT_RIGHT, MIDDLE_RIGHT, RIGHT, BACK_RIGHT, BACK_LEFT, LEFT, MIDDLE_LEFT, FRONT_LEFT
            {0, 0, 0, 0, 0, 0, 0, 0, 0.1, 1, 1, 0, 0, 0, 0, 0}, // Motor left
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0.1}, // Motor right
    };

    public ProportionalBallPusher() {
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
        return new double[]{200, 200};
    }

    @Override
    public double[] getTargetSensorValues() {
        return new double[16];
    }

    public static void main(String[] args) {
        ProportionalBallPusher controller = new ProportionalBallPusher();
        controller.run();
    }

//
//    @Override
//    public double[] calcSpeed(double[] sensors) {
//        double[] speeds = new double[NUMBER_OF_MOTORS];
//
//        for (int i = 0; i < NUMBER_OF_MOTORS; i++) {
//            speeds[i] = 0.0;
//            for (int sensor = 0; sensor < sensors.length; sensor++) {
//                // Maybe make this line abstract because this is the part which changes
//                speeds[i] += getMatrix()[i][sensor] * normalizeLight(sensors[sensor]);
//            }
//            if (speeds[i] > 1000) {
//                speeds[i] = 1000;
//            }
//        }
//        return speeds;
//    }

}
