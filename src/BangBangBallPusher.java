/**
 * Created by Mathias on 14/12/2015.
 */
public class BangBangBallPusher extends BangBangBaseController {
    private static int OBSTACLE_DISTANCE = 200;
    /**
     * Constructor
     */
    public BangBangBallPusher() {
        super();
        enableDistanceSensors();
    }

    @Override
    public void run() {
        while (step(TIME_STEP) != -1) {
            boolean leftObstacle = _distanceSensors[S_FRONT_LEFT].getValue() > OBSTACLE_DISTANCE
                    || _distanceSensors[S_LEFT].getValue() > OBSTACLE_DISTANCE
                    || _distanceSensors[S_BACK_LEFT].getValue() > OBSTACLE_DISTANCE;

            boolean rightObstacle = _distanceSensors[S_FRONT_RIGHT].getValue() > OBSTACLE_DISTANCE
                    || _distanceSensors[S_RIGHT].getValue() > OBSTACLE_DISTANCE
                    || _distanceSensors[S_BACK_RIGHT].getValue() > OBSTACLE_DISTANCE;

            if (leftObstacle) {
                driveLeft();
            } else if (rightObstacle) {
                driveRight();
            }else{
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
        BangBangBallPusher controller = new BangBangBallPusher();
        controller.run();
    }
}
