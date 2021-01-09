package com.appetiser.meowvies.helper

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog

/**
 * DialogHelper is a collection of methods that creates different
 * dialogs accordingly.
 * E.g. Alert Dialog with positive only button, with negative, etc
 * This can be customizable depending on specific needs
 */
object DialogHelper{

    /**
     * This method can be used to create a dialog with positive and
     * negative buttons (e.g. Ok/Cancel)
     * @param context application context
     * @param message refers to the specific message that should be shown
     * @param actionPositive refers to the label of the positive button
     * @param actionNegative refers to the label of the negative button
     * @param cancellable if you want to enable user to cancel the modal when clicked outside
     * @param successCallback when the user clicks the success button, this can triggers any
     * @param failCallback when the user clicks the fail button, this can triggers any
     */
    fun createOkCancelDialog(
        context: Context?,
        message: String?,
        actionPositive: String?,
        actionNegative: String?,
        cancellable: Boolean,
        successCallback: SuccessCallback,
        failCallback: FailCallback
    ): Dialog? {
        val alertDialog =
            AlertDialog.Builder(context!!)
                .setMessage(message)
                .setPositiveButton(actionPositive)
                { _, _ ->
                    successCallback.invoke()
                }
                .setNegativeButton(actionNegative)
                { _, _ ->
                    failCallback.invoke()
                }
        alertDialog.setCancelable(cancellable)
        return alertDialog.create()
    }
}