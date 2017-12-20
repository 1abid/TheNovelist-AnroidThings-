package novelist.vaigna.mama.steppermotordriver.uln2003driver.driver

import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.Direction
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.MotorRunner


/**
 * Created by Abid Hasan on 20/12/17.
 */

class ULN2003MotorRunner(val uln2003: ULN2003Board,
                         steps: Int,
                         direction: Direction,
                         val resolution: ULN2003Resolution,
                         executionDurationNanos: Long) : MotorRunner(uln2003, steps, direction, executionDurationNanos) {
    override fun applyResolution() {
       uln2003.resolution = resolution
    }
}