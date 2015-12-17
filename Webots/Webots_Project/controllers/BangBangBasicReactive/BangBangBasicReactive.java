public class BangBangBasicReactive extends BaseController {
    /**
     * Constructor
     */
    public BangBangBasicReactive() {
        super();
        enableDistanceSensors();
    }

    /**
     * Main method - in this method an instance of the controller is created and
     * the method to launch the robot is called.
     *
     * @param args
     */
    public static void main(String[] args) {
        BangBangBasicReactive controller = new BangBangBasicReactive();
        controller.run();
    }

    /**
     * User defined function for initializing and running the
     * BangBangFollowTheWall class
     */
    public void run() {
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