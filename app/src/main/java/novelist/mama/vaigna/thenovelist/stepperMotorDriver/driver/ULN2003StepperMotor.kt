package novelist.vaigna.mama.steppermotordriver.uln2003driver.driver

import android.util.Log
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.Direction
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.MotorRunner
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.StepperMotor


/**
 * Created by Abid Hasan on 18/12/17.
 */

class ULN2003StepperMotor(in1GpioId : String,
                          in2GpioId : String,
                          in3GpioId : String,
                          in4GpioId : String) : StepperMotor() {

    val uln2003 : ULN2003Board

    init {
        uln2003 = ULN2003Board(in1GpioId , in2GpioId , in3GpioId , in4GpioId)
        stepperMotorDriver = uln2003
        stepperMotorDriver.open()

    }



    override fun getMotorRunner(stepsToPerform: Int, direction: Direction, resolutionId: Int, executionDurationNanos: Long): MotorRunner {
        return ULN2003MotorRunner(uln2003 , stepsToPerform , direction ,getResolution(resolutionId) , executionDurationNanos)
    }

    private fun getResolution(resolutionId: Int): ULN2003Resolution {
        return ULN2003Resolution.getFromId(resolutionId)
    }

    override fun getStepsFromDegrees(degrees: Double, resolutionId: Int): Int {
        var step = (degrees / CIRCLE_DEGREES.toDouble() * getStepsPerRevolution(resolutionId).toDouble()).toInt()
        Log.i("Step from degree" , step.toString())
        return step
    }

    override fun getDegreesFromSteps(steps: Int, resolutionId: Int): Double {
        return steps.toDouble() * CIRCLE_DEGREES.toDouble() / getStepsPerRevolution(resolutionId).toDouble()
    }

    override fun getStepDurationMillisForRPM(rpm: Double, resolutionId: Int): Double {
        return MINUTE_MILLIS.toDouble() / getStepsPerRevolution(resolutionId).toDouble() / rpm
    }

    override fun getStepsPerRevolution(resolutionId: Int): Int {
        return if (getResolution(resolutionId) == ULN2003Resolution.FULL)
            HALF_STEPS_PER_REVOLUTION
        else
            FULL_STEPS_PER_REVOLUTION
    }

    override fun getExecutionDurationNanos(rpm: Double, resolutionId: Int, steps: Int): Long {
        return (getStepDurationMillisForRPM(rpm, resolutionId) * steps.toDouble() * NANOS_IN_SECOND.toDouble()).toLong()
    }


    companion object {
        /**
         * @see <a href="http://42bots.com/tutorials/28byj-48-stepper-motor-with-uln2003-driver-and-arduino-uno/">28BYJ-48 Stepper Motor tutorial</a>
         */
        val FULL_STEPS_PER_REVOLUTION = 4076
        val HALF_STEPS_PER_REVOLUTION = FULL_STEPS_PER_REVOLUTION / 2

    }
}