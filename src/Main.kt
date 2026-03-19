// notifications
sealed class Notification(val user: String,
                          val message: String){
    abstract fun getChannel(channelFactory: FactoryChannel): NotificationChannel
}

class EmailNotification(user: String, message: String):Notification(user, message ){
    override fun getChannel(channelFactory: FactoryChannel): NotificationChannel {
        return channelFactory.createEmailChannel()
    }

}
class PushNotification(user: String, message: String):Notification(user, message ){
    override fun getChannel(channelFactory: FactoryChannel): NotificationChannel {
        return channelFactory.createPushChannel()
    }

}
class SmsNotification(user: String, message: String):Notification(user, message ){
    override fun getChannel(channelFactory: FactoryChannel): NotificationChannel {
        return channelFactory.createSmsChannel()
    }

}
// notificationsChannels
interface NotificationChannel{
    fun send(notification: Notification): String
}

class EmailChannel:NotificationChannel{
    override fun send(notification: Notification): String {
        val email = notification as EmailNotification
        return "EMAIL send ${email.user} message ${email.message}"
    }
}
class PushChannel:NotificationChannel{
    override fun send(notification: Notification): String {
        val push = notification as PushNotification
        return "Push send ${push.user} message ${push.message}"
    }
}
class SmsChannel:NotificationChannel{
    override fun send(notification: Notification): String {
        val sms = notification as SmsNotification
        return "SMS send ${sms.user} message ${sms.message}"
    }
}

// FactoryChannel
interface FactoryChannel{
    fun createEmailChannel(): NotificationChannel
    fun createPushChannel(): NotificationChannel
    fun createSmsChannel(): NotificationChannel
}

class NotifierProviderFactory: FactoryChannel{
    override fun createEmailChannel(): NotificationChannel {
        return EmailChannel()
    }

    override fun createPushChannel(): NotificationChannel {
        return PushChannel()
    }

    override fun createSmsChannel(): NotificationChannel {
        return SmsChannel()
    }

}

//
class Notifier(val factoryChannel : FactoryChannel){

    fun dispatch(notifications: List<Notification>): List<String>{
        var resultList = emptyList<String>()
        for (notification in notifications){
            val channel = notification.getChannel(channelFactory = factoryChannel)
            resultList += channel.send(notification)
        }
        return resultList
    }
}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val notifier = Notifier(NotifierProviderFactory())
    val notifications = listOf<Notification>(
        EmailNotification("Esteban", "mando mail"),
        PushNotification("Pablo", "mando push"),
        SmsNotification("samanta", "mando sms")
    )
    val result = notifier.dispatch(notifications)
    print(result)

}