package com.wusy.loggerproject

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.orhanobut.logger.Logger
import com.wusy.loggerproject.permission.PermissionsResultAction
import android.Manifest.permission
import android.Manifest.permission.READ_PHONE_STATE
import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import com.wusy.loggerproject.permission.PermissionsManager


class MainActivity : Activity() {
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()
        Logger.i("吴思源打印")
        Logger.e("来来来下注了")
        Logger.d("牛皮")

    }

    private fun requestPermissions() {
        if (!//写入权限
            //电话拨打权限
            PermissionsManager.getInstance().hasAllPermissions(
                this,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        )
        ;
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), object : PermissionsResultAction() {

                override fun onGranted() {
                }

                override fun onDenied(permission: String) {
                }
            })
    }

    override fun onRequestPermissionsResult(permsRequestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults)
    }
}
