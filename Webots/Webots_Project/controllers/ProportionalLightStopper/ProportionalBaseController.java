/**
 * Created by Mathias on 15/12/2015.
 */
public abstract class ProportionalBaseController extends BaseController {
    public static int NUMBER_OF_MOTORS = 2;

    public abstract double[][] getMatrix();

    public abstract double[] getConstantVector();

    public abstract double[] getTargetSensorValues();

    protected double[] getSensorValues() {
        double[] sensors = new double[]{
                _lightSensors[S_FRONT_RIGHT].getValue(),
                _lightSensors[S_MIDDLE_RIGHT].getValue(),
                _lightSensors[S_RIGHT].getValue(),
                _lightSensors[S_BACK_RIGHT].getValue(),
                _lightSensors[S_BACK_LEFT].getValue(),
                _lightSensors[S_LEFT].getValue(),
                _lightSensors[S_MIDDLE_LEFT].getValue(),
                _lightSensors[S_FRONT_LEFT].getValue(),
                _distanceSensors[S_FRONT_RIGHT].getValue(),
                _distanceSensors[S_MIDDLE_RIGHT].getValue(),
                _distanceSensors[S_RIGHT].getValue(),
                _distanceSensors[S_BACK_RIGHT].getValue(),
                _distanceSensors[S_BACK_LEFT].getValue(),
                _distanceSensors[S_LEFT].getValue(),
                _distanceSensors[S_MIDDLE_LEFT].getValue(),
                _distanceSensors[S_FRONT_LEFT].getValue()
        };

        return sensors;
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            double[] sensors = getSensorValues();
            double[] speeds = calcSpeed(sensors);
            setSpeed(speeds[0], speeds[1]);
        }
    }

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

    protected double normalizeLight(double light) {
        //max und min light eventl. noch anpassen
        int maxLight = 50;
        int minLight = 4000;
        double output = 1000 - ((light - maxLight) * 1000) / (minLight - maxLight);
        if (output < -1000) {
            output = -1000;
        }
        if (output > 1000) {
            output = 1000;
        }
        return output;
    }
}
