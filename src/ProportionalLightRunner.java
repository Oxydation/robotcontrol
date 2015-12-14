/**
 * Created by Mathias on 07/12/2015.
 */
public class ProportionalLightRunner extends BaseController {
    private static int K_STEERING = 10;

    @Override
    public void run() {
        // Steering angle command = Ksteering * (reference distance – sonar distance)
        while (step(TIME_STEP) != -1) {


            if (_distanceSensors[S_FRONT_LEFT].getValue() > MAX_SENSOR_VALUE
                    || _distanceSensors[S_LEFT].getValue() > MAX_SENSOR_VALUE) {
                // drive right - reached a wall
                driveRight();
            } else if (_distanceSensors[S_FRONT_RIGHT].getValue() > MAX_SENSOR_VALUE
                    || _distanceSensors[S_RIGHT].getValue() > MAX_SENSOR_VALUE) {
                // drive left - reached a wall
                driveLeft();
            } else {
                // drive forward if nothing is in front of the robot
                driveForward();
            }
        }
    }

    @Override
    protected void driveRight() {

    }

    @Override
    protected void driveLeft() {

    }

    @Override
    protected void driveForward() {

    }
}
