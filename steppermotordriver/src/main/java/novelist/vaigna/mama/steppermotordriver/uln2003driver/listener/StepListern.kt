package novelist.vaigna.mama.steppermotordriver.uln2003driver.listener

/**
 * Created by abidhasan on 19/12/17.
 */
interface StepsListener {
    fun onStarted() {
    }

    fun onFinishedSuccessfully() {
    }

    fun onFinishedWithError(stepsToPerform: Int, performedSteps: Int, exception: Exception) {
    }
}