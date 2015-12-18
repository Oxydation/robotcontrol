/**
 * Created by Mathias on 14/12/2015.
 */
public class BangBangLightStopper extends BangBangBaseController {
    private static int MIN_DISTANCE = 800;
    private static int MAX_LIGHT = 2400;

    /**
     * Constructor
     */
    public BangBangLightStopper() {
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
        BangBangLightStopper controller = new BangBangLightStopper();
        controller.run();
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            double left = _lightSensors[S_FRONT_LEFT].getValue() + _lightSensors[S_LEFT].getValue();
            double right = _lightSensors[S_FRONT_RIGHT].getValue() + _lightSensors[S_RIGHT].getValue();
            boolean distanceReached = _distanceSensors[S_LEFT].getValue() >= MIN_DISTANCE || _distanceSensors[S_FRONT_LEFT].getValue() >= MIN_DISTANCE ||
                    _distanceSensors[S_RIGHT].getValue() >= MIN_DISTANCE || _distanceSensors[S_FRONT_RIGHT].getValue() >= MIN_DISTANCE;

            if ((_lightSensors[S_FRONT_LEFT].getValue() < MAX_LIGHT || _lightSensors[S_LEFT].getValue() < MAX_LIGHT || _lightSensors[S_FRONT_RIGHT].getValue() < MAX_LIGHT || _lightSensors[S_RIGHT].getValue() < MAX_LIGHT) && distanceReached) {
                setSpeed(MIN_SPEED, MIN_SPEED);
            } else {
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
}

