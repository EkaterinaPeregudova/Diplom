# **Отчёт о проведённом тестировании**

Было протестировано поведение приложение по покупке тура в разных пользовательских сценариях.

В соответствии с тест-планом в работе были использованы 22 тест-кейса, 11 из которых не пройдено (50%).

**Выявлены следующие issues:**

1. [Покупка тура картой со статусом DECLINED](https://github.com/EkaterinaPeregudova/Diplom/issues/1)
1. [Покупка в кредит сохраняется в таблице покупок, как оплата картой](https://github.com/EkaterinaPeregudova/Diplom/issues/2)
1. [Покупка тура если поле "Владелец" заполнено кириллицей](https://github.com/EkaterinaPeregudova/Diplom/issues/3)
1. [При заполнении формы сообщение "Неверный формат", если не заполнены поля "Месяц", "Номер карты", "Год", "CVC/CVV"](https://github.com/EkaterinaPeregudova/Diplom/issues/4)
1. [Заполненное имя владельца с сообщением "Поле обязательно для заполнения", если не заполнено поле CVC/CVV](https://github.com/EkaterinaPeregudova/Diplom/issues/5)
1. [Опечатка в названии города Марракеш](https://github.com/EkaterinaPeregudova/Diplom/issues/6)


## **Общие рекомендации**

1. Исправить код приложения - запрет операций картой со статусом **DECLINED**
1. Исправить код приложения - отражение операции покупки в кредит, как операции покупки в кредит
1. Исправить код приложения - заполнения поля **Владелец** только на латинице
1. Исправить текст сообщения об ошибке **"Неверный формат"** на **"Поле обязательно для заполнения"** если не заполнены поля **Месяц, Номер карты, Год, CVC/CVV**
1. Исправить код приложения - если не заполнено **только** поле **CVC/CVV**, не показывать ошибку в поле **Владелец**
1. Исправить опечатку названии города