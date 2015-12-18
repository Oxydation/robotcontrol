/**
 * Created by Mathias on 07/12/2015.
 */
public abstract class BangBangBaseController extends BaseController {

    protected static int MAX_SENSOR_VALUE = 200;

    protected static int MIN_SPEED = 0; // min. motor speed
    protected static int MAX_SPEED = 1000; // max. motor speed

    /**
     * Robot drives to the right
     */
    protected abstract void driveRight();

    /**
     * Robot drives to the left
     */
    protected abstract void driveLeft();

    /**
     * Robot drives forward
     */
    protected abstract void driveForward();
}
