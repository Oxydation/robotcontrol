/**
 * Created by Mathias on 15/12/2015.
 */
public abstract class ProportionalBaseController extends BaseController {

    public abstract double[][] getMatrix();

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

    public abstract double[] calcSpeed(double[] sensors) ;
}
