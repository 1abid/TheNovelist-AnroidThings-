package novelist.vaigna.mama.steppermotordriver.uln2003driver.motor

import novelist.vaigna.mama.steppermotordriver.uln2003driver.driver.StepperMotorDriver
import novelist.vaigna.mama.steppermotordriver.uln2003driver.listener.StepsListener

/**
 * Created by abidhasan on 19/12/17.
 */
abstract class MotorRunner(val motorDriver: StepperMotorDriver,
                           val steps: Int,
                           val direction: Direction,
                           val executionDurationNanos: Long) : Runnable {

    var stepListener: StepsListener? = null
    var stepDurationNanos: Long = 0
    var executionStartNanos: Long = 0


    override fun run() {

        stepListener?.onStarted()
        motorDriver.direction = direction
        applyResolution()
        stepDurationNanos = executionDurationNanos / steps.toLong()
        executionStartNanos = System.nanoTime()
        for (i in 1..steps){
            if(Thread.currentThread().isInterrupted){
                finishWithError(i , InterruptedException())
                return
            }
            try{
                motorDriver.performStep(getStepDuration(i))
            }catch (e :InterruptedException){
                finishWithError(i , e)
                return
            }
        }

    }


    abstract protected fun applyResolution()

    private fun finishWithError(performedSteps: Int, exception: Exception)
            = stepListener?.onFinishedWithError(steps, performedSteps, exception)

    private fun getStepDuration(stepNumber: Int): StepDuration {
        val durationNanos = getStepFinishTimestamp(stepNumber) - System.nanoTime()
        var millis = durationNanos / NANOS_IN_MILLISECOND
        var nanos = durationNanos - (millis * NANOS_IN_MILLISECOND)
        if (millis < 0 || millis == 0L && nanos < MIN_STEP_DURATION_NANOS) {
            millis = 0L
            nanos = MIN_STEP_DURATION_NANOS
        }
        return StepDuration(millis, nanos.toInt())
    }

    private fun getStepFinishTimestamp(stepNumber: Int): Long
            = executionStartNanos + stepNumber.toLong() * stepDurationNanos

    companion object {
        private val NANOS_IN_MILLISECOND = 1000000L
        private val MIN_STEP_DURATION_NANOS = 2000L
    }

}