package ua.com.location.di.dialogFragment

import dagger.Module
import dagger.Provides
import ua.com.location.presentation.dialog.MyDialogPresentIntefas
import ua.com.location.presentation.dialog.MyDialogPresentation
import ua.com.location.presentation.dialog.MyDialogView

@Module
class MyDialogPresenterIntefasModul(var myDialogView: MyDialogView) {
    @Provides
    fun getMyDialogPresenterIntefas():MyDialogPresentIntefas = MyDialogPresentation(myDialogView)
}