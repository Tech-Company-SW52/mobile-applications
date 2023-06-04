package com.fastporte.Interface

import com.fastporte.models.User

interface EditProfileDialogListener {
    fun onDialogDataSaved(user: User)
}