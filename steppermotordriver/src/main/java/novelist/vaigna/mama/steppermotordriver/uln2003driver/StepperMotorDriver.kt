package novelist.vaigna.mama.steppermotordriver.uln2003driver


/**
 * Created by Abid Hasan on 18/12/17.
 */

abstract class StepperMotorDriver : AutoCloseable{

    open var direction : Direction = Direction.CLOCKWISE

    abstract fun open()

    abstract fun performStep(stepDuration: StepDuration)

}