/**
 * Created by Mathias on 14/12/2015.
 */
public class BangBangWallRunner extends BaseController {
    private static int DISTANCE_TO_WALL = 50;

    /**
     * Constructor
     */
    public BangBangWallRunner() {
        super();
        enableDistanceSensors();
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            if (_distanceSensors[S_FRONT_LEFT].getValue() < DISTANCE_TO_WALL
                    && _distanceSensors[S_FRONT_RIGHT].getValue() < DISTANCE_TO_WALL) {
                driveForward();
            } else if (_distanceSensors[S_RIGHT].getValue() > DISTANCE_TO_WALL
                    && _distanceSensors[S_FRONT_LEFT].getValue() > DISTANCE_TO_WALL
                    && _distanceSensors[S_FRONT_RIGHT].getValue() > DISTANCE_TO_WALL) {
                driveRight();
            } else if (_distanceSensors[S_LEFT].getValue() > DISTANCE_TO_WALL
                    && _distanceSensors[S_FRONT_LEFT].getValue() > DISTANCE_TO_WALL
                    && _distanceSensors[S_FRONT_RIGHT].getValue() > DISTANCE_TO_WALL) {
                driveRight();
            } else if (_distanceSensors[S_FRONT_LEFT].getValue() > DISTANCE_TO_WALL
                    && _distanceSensors[S_FRONT_RIGHT].getValue() > DISTANCE_TO_WALL) {
                driveRight();
            } else if (_distanceSensors[S_RIGHT].getValue() < DISTANCE_TO_WALL) {
                driveLeft();
            } else if (_distanceSensors[S_LEFT].getValue() < DISTANCE_TO_WALL) {
                driveLeft();
            }

            /*

            else if (_distanceSensors[S_LEFT].getValue() >= DISTANCE_TO_WALL) {
                driveRight();
            } else if (_distanceSensors[S_LEFT].getValue() >= DISTANCE_TO_WALL) {
                driveLeft();
            }
             */
            // drive forward if nothing is in front of the robot
            //driveForward();
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
        BangBangWallRunner controller = new BangBangWallRunner();
        controller.run();
    }
}
