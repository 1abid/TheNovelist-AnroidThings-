package novelist.mama.vaigna.thenovelist.stepperMotorDriver

interface Awaiter {
    fun await(millis: Long, nanos: Int)
}