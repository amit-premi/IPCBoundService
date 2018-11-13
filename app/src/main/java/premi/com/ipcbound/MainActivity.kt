package premi.com.ipcbound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mIsBound: Boolean = false
    lateinit var mLoopBindService: LoopBindService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Call for UI Initialization
        initializeUI()
    }

    private fun initializeUI() {
        vTxtResponseValue.text = "Hi"
        vBtnBind.setOnClickListener {
            if (mIsBound) {
                vTxtResponseValue.text = mLoopBindService.getRandomNumber().toString()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this@MainActivity, LoopBindService::class.java)
        bindService(intent, mServiceConnect, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(mServiceConnect)
    }

    private val mServiceConnect = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, serviceBinder: IBinder?) {
            val mLoopBinder: LoopBindService.LoopBinder = serviceBinder as LoopBindService.LoopBinder
            mLoopBindService = mLoopBinder.getService()
            mIsBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mIsBound = false
        }
    }
}
