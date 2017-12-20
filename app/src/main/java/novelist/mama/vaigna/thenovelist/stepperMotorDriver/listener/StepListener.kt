package novelist.vaigna.mama.steppermotordriver.uln2003driver.listener


/**
 * Created by Abid Hasan on 20/12/17.
 */

interface StepListener {


    fun onStarted() {
    }

    fun onFinishedSuccessfully() {
    }

    fun onFinishedWithError(stepsToPerform: Int, performedSteps: Int, exception: Exception) {
    }
}