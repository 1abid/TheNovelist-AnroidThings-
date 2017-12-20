package novelist.vaigna.mama.steppermotordriver.uln2003driver.Gpio

import com.google.android.things.pio.Gpio

/**
 * Created by abidhasan on 19/12/17.
 */
class StepperMotorGpio(val gpio: Gpio) : AutoCloseable {

    init {
        gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
        gpio.setActiveType(Gpio.ACTIVE_HIGH)
    }


    var value: Boolean
        set(value) {
            gpio.value = value
        }get() = gpio.value

    override fun close() {
        gpio.close()
    }
}