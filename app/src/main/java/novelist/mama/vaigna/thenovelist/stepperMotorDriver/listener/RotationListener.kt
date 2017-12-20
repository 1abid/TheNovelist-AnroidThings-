package novelist.vaigna.mama.steppermotordriver.uln2003driver.listener


/**
 * Created by Abid Hasan on 20/12/17.
 */

interface RotationListener {

    fun onStarted() {
    }

    fun onFinishedSuccessfully() {
    }

    fun onFinishedWithError(degreesToRotate: Double, rotatedDegrees: Double, exception: Exception) {
    }
}