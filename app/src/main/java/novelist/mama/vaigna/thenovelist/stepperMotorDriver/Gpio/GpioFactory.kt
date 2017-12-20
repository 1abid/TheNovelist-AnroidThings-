package novelist.vaigna.mama.steppermotordriver.uln2003driver.Gpio

import com.google.android.things.pio.PeripheralManagerService

/**
 * Created by abidhasan on 19/12/17.
 */
open class GpioFactory {
    open fun openGpio(gpioPinName : String) = PeripheralManagerService().openGpio(gpioPinName)
}