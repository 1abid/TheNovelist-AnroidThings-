package novelist.vaigna.mama.steppermotordriver.uln2003driver.motor

import novelist.vaigna.mama.steppermotordriver.uln2003driver.driver.StepperMotorDriver
import novelist.vaigna.mama.steppermotordriver.uln2003driver.listener.RotationListener
import novelist.vaigna.mama.steppermotordriver.uln2003driver.listener.StepsListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by abidhasan on 19/12/17.
 */
abstract class StepperMotor : AutoCloseable {

    protected open val executor: ExecutorService
    protected open lateinit var stepperMotorDriver : StepperMotorDriver


    init {
        executor = Executors.newSingleThreadExecutor()
    }


    override fun close() {
        executor.shutdownNow()
        stepperMotorDriver.close()
    }

    fun rotate(degrees: Double, direction: Direction, resolutionId: Int, rpm: Double) {
        rotate(degrees, direction, resolutionId, rpm, null)
    }

    fun rotate(degrees: Double, direction: Direction, resolutionId: Int, rpm: Double, rotationListener: RotationListener? = null) {
        if (degrees < 0) {
            throw IllegalArgumentException("degrees less than 0: {$degrees}")
        }
        if (rpm < 0) {
            throw IllegalArgumentException("rpm less than 0: {$rpm}")
        }

        val stepsToPerform = getStepsFromDegrees(degrees, resolutionId)
        val executionDuration = getExecutionDurationNanos(rpm, resolutionId, stepsToPerform)
        val motorRunner = getMotorRunner(stepsToPerform, direction, resolutionId, executionDuration)
        motorRunner.stepListener = object : StepsListener {
            override fun onStarted() {
                rotationListener?.onStarted()
            }

            override fun onFinishedSuccessfully() {
                rotationListener?.onFinishedSuccessfully()
            }

            override fun onFinishedWithError(stepsToPerform: Int, performedSteps: Int, exception: Exception) {
                rotationListener?.onFinishedWithError(degrees, getDegreesFromSteps(performedSteps, resolutionId), exception)
            }

        }

        executor.submit(motorRunner)
    }

    protected abstract fun getMotorRunner(stepsToPerform: Int, direction: Direction, resolutionId: Int, executionDurationNanos: Long): MotorRunner

    protected abstract fun getStepsFromDegrees(degrees: Double, resolutionId: Int): Int

    protected abstract fun getDegreesFromSteps(steps: Int, resolutionId: Int): Double

    protected abstract fun getStepDurationMillisForRPM(rpm: Double, resolutionId: Int): Double

    protected abstract fun getStepsPerRevolution(resolutionId: Int): Int

    protected abstract fun getExecutionDurationNanos(rpm: Double, resolutionId: Int, steps: Int): Long

    companion object {
        val CIRCLE_DEGREES = 360
        val MINUTE_MILLIS = 60 * 1000
        val NANOS_IN_SECOND = 1000000
    }
}