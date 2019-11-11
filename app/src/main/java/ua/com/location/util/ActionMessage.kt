package ua.com.location.util

enum class ActionMessage(val result:String) {
    ERROR_WRONG_PASSWORD("Неправельный пароль"),
    ERROR_USER_NOT_FOUND("Пользаватель ненайден, зарегистрируйтесь"),
    ERROR_EMPTY_DATA("Заполните все поля"),
    ERROR_NO_COINCIDENCE("Пароли не совподают"),
    SUCCESSFUL("Успех"),
    ERROR_LENGTH("Пароль должен содержать более 6 символов"),
    ERROR_EMAIL_ALREADY_IN_USE  ("Пользователь существует")
}