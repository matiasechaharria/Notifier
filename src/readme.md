Enunciado de Prueba Técnica: "Service Alert System"
Descripción del Problema
El equipo de Logística necesita un sistema centralizado para notificar a los usuarios sobre el estado de sus paquetes. Actualmente, enviamos alertas por Email, pero el negocio requiere expandirse rápidamente a SMS y Push Notifications. El sistema debe ser lo suficientemente flexible para soportar futuros canales (como WhatsApp) sin alterar la lógica del despachador central.

Requerimientos de Código
1. Dominio de Notificaciones:

Define una estructura jerárquica para las notificaciones. Cada una debe tener un usuario y un mensaje.

Implementa las clases: EmailNotification, SMSNotification y PushNotification.

Restricción: Utiliza una Sealed Class para garantizar que no se herede de forma externa y permitir un manejo exhaustivo de tipos.

2. Abstracción de Canales:

Crea una interfaz NotificationChannel (o CanalNotificacion) que exponga un método send(notification: Notification): String.

Este método debe devolver un mensaje de confirmación (log) que indique el éxito y el destino (ej: el email o el teléfono utilizado).

3. El Orquestador (Notifier):

Diseña una clase Notifier.

Inyección: Esta clase debe recibir por constructor la lista de canales habilitados (ej: List<NotificationChannel>).

Procesamiento: Debe exponer un método dispatch(notifications: List<Notification>): List<String> que tome una lista de mensajes pendientes y los envíe por todos los canales que correspondan.