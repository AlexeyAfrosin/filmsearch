package com.afrosin.filmsearch.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.afrosin.filmsearch.R


class MainBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action.equals(ACTION_CONNECTIVITY_CHANGE)) {
            val str = when (checkForInternet(context)) {
                true -> context.getString(R.string.connection_on)
                else -> context.getString(R.string.connection_off)
            }

            StringBuilder().apply {
                append(context.getString(R.string.system_message))
                append(context.getString(R.string.system_action, str))
                toString().also {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
        }


    }

    // Self explanatory method
    private fun checkForInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

}