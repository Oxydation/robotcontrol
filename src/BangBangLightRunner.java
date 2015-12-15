/**
 * Created by Mathias on 14/12/2015.
 */
public class BangBangLightRunner extends BangBangBaseController {
    /**
     * Constructor
     */
    public BangBangLightRunner() {
        super();
        enableLightSensors();
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            double left = _lightSensors[S_FRONT_LEFT].getValue() + _lightSensors[S_LEFT].getValue();
            double right = _lightSensors[S_FRONT_RIGHT].getValue() + _lightSensors[S_RIGHT].getValue();

            if (left < right) {
                // drive left - reached light
                driveLeft();
            } else if (right < left) {
                // drive right - reached light
                driveRight();
            } else {
                // drive forward if nothing is in front of the robot
                driveForward();
            }
        }
    }

    @Override
    protected void driveRight() {
        setSpeed(MAX_SPEED, MIN_SPEED);
    }

    @Override
    protected void driveLeft() {
        setSpeed(MIN_SPEED, MAX_SPEED);
    }

    @Override
    protected void driveForward() {
        setSpeed(MAX_SPEED, MAX_SPEED);
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        BangBangLightRunner controller = new BangBangLightRunner();
        controller.run();
    }
}

