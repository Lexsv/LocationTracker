package ua.com.location.di.dialogFragment

import dagger.Component
import ua.com.location.presentation.dialog.MyDialog

@Component(modules = arrayOf(MyDialogPresenterIntefasModul::class,MyDialogViewProvid::class))
interface MyDialogComponent {
    fun inject(myDialog: MyDialog)
}