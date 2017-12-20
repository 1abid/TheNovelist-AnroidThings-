package novelist.vaigna.mama.steppermotordriver.uln2003driver.driver

import android.util.Log
import novelist.mama.vaigna.thenovelist.stepperMotorDriver.Awaiter
import novelist.mama.vaigna.thenovelist.stepperMotorDriver.DefaultAwaiter
import novelist.vaigna.mama.steppermotordriver.uln2003driver.Gpio.GpioFactory
import novelist.vaigna.mama.steppermotordriver.uln2003driver.Gpio.StepperMotorGpio
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.Direction
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.StepDuration
import java.io.IOException


/**
 * Created by Abid Hasan on 20/12/17.
 */

class ULN2003Board internal constructor(private val in1GpioId: String,
                                        private val in2GpioId: String,
                                        private val in3GpioId: String,
                                        private val in4GpioId: String,
                                        private val gpioFactory: GpioFactory,
                                        private val awaiter: Awaiter) : StepperMotorDriver() {


    constructor(in1GpioId: String,
                in2GpioId: String,
                in3GpioId: String,
                in4GpioId: String) : this(in1GpioId, in2GpioId, in3GpioId, in4GpioId,
            GpioFactory(), DefaultAwaiter())

    companion object {
        private val STATE_STEPS_COUNT = 8
        private val INITIAL_STATE = -1
    }


    var resolution = ULN2003Resolution.HALF

    private var in1: StepperMotorGpio ? = null
    private var in2: StepperMotorGpio ? = null
    private var in3: StepperMotorGpio ? = null
    private var in4: StepperMotorGpio ? = null

    private var currentStepState = INITIAL_STATE
    private var gpiosOpened = false

    override fun open() {
        if (gpiosOpened)
            return

        try {
            in1 = StepperMotorGpio(gpioFactory.openGpio(in1GpioId))
            in2 = StepperMotorGpio(gpioFactory.openGpio(in2GpioId))
            in3 = StepperMotorGpio(gpioFactory.openGpio(in3GpioId))
            in4 = StepperMotorGpio(gpioFactory.openGpio(in4GpioId))
        }catch (e : IOException){
            close()
            throw e
        }

        setActiveCoil(false, false, false , false)
    }


    private fun setActiveCoil(in1State : Boolean , in2State : Boolean , in3State : Boolean , in4State : Boolean){
        in1?.value = in1State
        in2?.value = in2State
        in3?.value = in3State
        in4?.value = in4State

        Log.i("pin name" , in1?.gpio?.name + " "+ in2?.gpio?.name + " "+ in3?.gpio?.name +" "+ in4?.gpio?.name)

        Log.i("pin value " , in1?.value.toString() + " "+ in2?.value.toString() + " " +
                in3?.value.toString() + " " + in4?.value.toString())
    }

    override fun performStep(stepDuration: StepDuration) {
        when (resolution) {
            ULN2003Resolution.FULL -> setNextFullStepState()
            ULN2003Resolution.HALF -> setNextHalfStepState()
        }
        setActiveCoilsDependingOnCurrentStepState()
        awaiter.await(stepDuration.millis, stepDuration.nano)
    }

    private fun setActiveCoilsDependingOnCurrentStepState() {
        when(currentStepState){
            0 -> setActiveCoil(false , false , false , true)
            1 -> setActiveCoil(false , false , true , true)
            2 -> setActiveCoil(false, false , true , false)
            3 -> setActiveCoil(false , true , true , false)
            4 -> setActiveCoil(false , true , false , false)
            5 -> setActiveCoil(true , true , false ,false)
            6 -> setActiveCoil(true , false , false, false)
            7 -> setActiveCoil(true , false , false, true)
        }
    }

    override fun close() {
        arrayOf(in1, in2, in3, in4).forEach {
            try {
                it?.close()
            } catch (e: Exception) {
            }
        }
        gpiosOpened = false
    }


    private fun setNextHalfStepState(){

        if(currentStepState == INITIAL_STATE)
            currentStepState = 0

        else{
            var nextStepState = currentStepState

            when(direction){
                Direction.CLOCKWISE -> nextStepState ++

                Direction.COUNTERCLOCKWISE -> nextStepState --
            }

            currentStepState = mapAllowedState(nextStepState)
        }
    }


    private fun setNextFullStepState(){
        if (currentStepState == INITIAL_STATE)
            currentStepState = 1

        else {

            var nextStepState = currentStepState

            when(direction){

                Direction.CLOCKWISE -> {
                    if(isCurrentStepFull())
                        nextStepState ++
                    else
                        nextStepState += 2
                }

                Direction.COUNTERCLOCKWISE ->{
                    if (isCurrentStepFull())
                        nextStepState --
                    else
                        nextStepState -= 2
                }

            }

            currentStepState = mapAllowedState(nextStepState)
        }
    }


    private fun isCurrentStepFull()
            = currentStepState % 2 == 0

    private fun mapAllowedState(nextStepState: Int): Int {

        if(nextStepState >= STATE_STEPS_COUNT)
            return nextStepState % STATE_STEPS_COUNT
        else if (nextStepState < 0)
            return nextStepState + STATE_STEPS_COUNT

        return nextStepState
    }
}