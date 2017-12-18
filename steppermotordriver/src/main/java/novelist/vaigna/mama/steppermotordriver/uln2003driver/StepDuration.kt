package novelist.vaigna.mama.steppermotordriver.uln2003driver


/**
 * Created by Abid Hasan on 18/12/17.
 */

data class StepDuration(val millis:Long = 1 , val nano:Long = 0) {

    init {
        if(millis < 0)
            throw IllegalArgumentException("millis less than 0 :{$millis}")
        if(nano < 0)
            throw IllegalArgumentException("nanos less than 0 :{$nano}")
    }

}