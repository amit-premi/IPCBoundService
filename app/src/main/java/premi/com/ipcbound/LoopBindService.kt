package premi.com.ipcbound

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlin.random.Random

/**
 * Created by Amit PREMI on 13-Nov-18.
 */
class LoopBindService : Service() {

    private val mBinder : IBinder = LoopBinder()
    private val mRandomGen : Random = Random

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    fun getRandomNumber() : Int = mRandomGen.nextInt(10000)

    inner class LoopBinder : Binder() {
        fun getService(): LoopBindService = this@LoopBindService
    }
}