package novelist.vaigna.mama.steppermotordriver.uln2003driver.listener

/**
 * Created by abidhasan on 19/12/17.
 */
interface RotationListener {
    fun onStarted() {
    }

    fun onFinishedSuccessfully() {
    }

    fun onFinishedWithError(degreesToRotate: Double, rotatedDegrees: Double, exception: Exception) {
    }
}