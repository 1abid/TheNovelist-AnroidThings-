package novelist.mama.vaigna.thenovelist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import novelist.vaigna.mama.steppermotordriver.uln2003driver.driver.ULN2003Resolution
import novelist.vaigna.mama.steppermotordriver.uln2003driver.driver.ULN2003StepperMotor
import novelist.vaigna.mama.steppermotordriver.uln2003driver.listener.RotationListener
import novelist.vaigna.mama.steppermotordriver.uln2003driver.motor.Direction

class MainActivity : AppCompatActivity() {

    val TAG = "ULN2003StepperMotor"

    val in1Pin = "GPIO2_IO02"
    val in2Pin = "GPIO2_IO05"
    val in3Pin = "GPIO2_IO01"
    val in4Pin = "GPIO5_IO00"

    private lateinit var stepper: ULN2003StepperMotor



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        stepper = ULN2003StepperMotor(in1Pin, in2Pin, in3Pin, in4Pin)
        stepper.rotate(degrees = 180.0,
                direction = Direction.CLOCKWISE,
                resolutionId = ULN2003Resolution.FULL.id,
                rpm = 5.0,
                rotationListener = object : RotationListener {
                    override fun onStarted() {
                        Log.i(TAG, "first rotation started")
                    }

                    override fun onFinishedSuccessfully() {
                        Log.i(TAG, "first rotation finished")
                    }

                    override fun onFinishedWithError(degreesToRotate: Double, rotatedDegrees: Double, exception: Exception) {
                        Log.e(TAG, "error, degrees to rotate: {$degreesToRotate}  rotated degrees: {$rotatedDegrees}")
                    }
                })

    }

    override fun onPause() {
        stepper.close()
        super.onPause()
    }
}
