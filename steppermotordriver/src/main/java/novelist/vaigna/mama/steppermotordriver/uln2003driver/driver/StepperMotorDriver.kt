package novelist.vaigna.mama.steppermotordriver.uln2003driver.driver

import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.Direction
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.StepDuration


/**
 * Created by Abid Hasan on 18/12/17.
 */

abstract class StepperMotorDriver : AutoCloseable{

    open var direction : Direction = Direction.CLOCKWISE

    abstract fun open()

    abstract fun performStep(stepDuration: StepDuration)

}