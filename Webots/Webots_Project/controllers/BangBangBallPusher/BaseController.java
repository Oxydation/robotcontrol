import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;
import com.cyberbotics.webots.controller.LightSensor;

/**
 * Created by Mathias on 07/12/2015.
 */
public abstract class BaseController extends DifferentialWheels {
    protected static int TIME_STEP = 16;

    protected static int MAX_SENSOR_VALUE = 200;

    protected static int MIN_SPEED = 0; // min. motor speed
    protected static int MAX_SPEED = 1000; // max. motor speed

    protected static int S_LEFT = 0; // Sensor left
    protected static int S_FRONT_LEFT = 1; // Sensor front left
    protected static int S_FRONT_RIGHT = 2; // Sensor front right
    protected static int S_RIGHT = 3; // Sensor left
    protected static int S_BACK_LEFT = 4;
    protected static int S_BACK_RIGHT = 5;

    protected DistanceSensor[] _distanceSensors; // Array with all distance _distanceSensors
    protected LightSensor[] _lightSensors;

    public BaseController() {
        // get distance _distanceSensors and save them in array
        _distanceSensors = new DistanceSensor[]{getDistanceSensor("ps5"),
                getDistanceSensor("ps7"), getDistanceSensor("ps0"),
                getDistanceSensor("ps2"),getDistanceSensor("ps4"), getDistanceSensor("ps3")};

        // get distance _distanceSensors and save them in array
        _lightSensors = new LightSensor[]{getLightSensor("ls5"),
                getLightSensor("ls7"), getLightSensor("ls0"),
                getLightSensor("ls2"), getLightSensor("ls4"), getLightSensor("ls3")};
    }

    protected void enableLightSensors() {
        for (LightSensor lightSensor : _lightSensors) {
            lightSensor.enable(10);
        }
    }

    protected void enableDistanceSensors() {
        for (DistanceSensor distanceSensor : _distanceSensors) {
            distanceSensor.enable(10);
        }
    }

    public abstract void run();

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
