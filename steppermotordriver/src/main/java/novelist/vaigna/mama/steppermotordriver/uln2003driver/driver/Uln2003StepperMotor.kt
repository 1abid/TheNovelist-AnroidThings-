package novelist.vaigna.mama.steppermotordriver.uln2003driver.driver

import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.Direction
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.MotorRunner
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.StepperMotor


/**
 * Created by Abid Hasan on 18/12/17.
 */

class Uln2003StepperMotor(in1GpioId : String ,
                          in2GpioId : String ,
                          in3GpioId : String ,
                          in4GpioId : String) : StepperMotor() {





    override fun getMotorRunner(stepsToPerform: Int, direction: Direction, resolutionId: Int, executionDurationNanos: Long): MotorRunner {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStepsFromDegrees(degrees: Double, resolutionId: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDegreesFromSteps(steps: Int, resolutionId: Int): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStepDurationMillisForRPM(rpm: Double, resolutionId: Int): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStepsPerRevolution(resolutionId: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExecutionDurationNanos(rpm: Double, resolutionId: Int, steps: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}