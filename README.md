# Проект по автоматизации тестирования UI для сайта компании [Додо Пицца](https://dodopizza.ru/)
<p align="center"><a href="https://dodopizza.ru/"><img src="https://upload.wikimedia.org/wikipedia/ru/9/91/Dodo_Logo.svg" align="center" width="1000" height="200"  alt="Файл:Dodo Logo.svg"/></a></p>

## Содержание:

- [Технологии и инструменты](#tools)
- [Тестовое покрытие](#cases)
- [Локальный запуск тестов](#localrun)
- [Запуск тестов в Jenkins](#remoterun)
- [Allure отчёт](#report)
- [Интеграция с Allure TestOps](#testops)
- [Интеграция с Jira](#jira)
- [Уведомления в Telegram](#telegram)
- [Видео с примером запуска тестов в Selenoid](#video)


<a id="tools"></a>

## Технологии и инструменты:

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/icons/intellij-original.svg" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/icons/github-original.svg" width="50"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/icons/java-original.svg" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/icons/gradle-original.svg" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/icons/junit-original.svg" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/icons/selenide-logo-big.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/icons/selenoid.png" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/icons/jenkins-original.svg" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="50" src="images/icons/allureReports.png" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/icons/allureTestops.svg" width="50"/></a>
<a href="https://www.atlassian.com/software/jira"><img alt="Jira" height="50" src="images/icons/jira.webp" width="50"/></a>  
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/icons/telegram.webp" width="50"/></a>
</div>

<p></p>

Автотесты для данного проекта написаны на <code>Java</code> с использованием фреймворка <code>Selenide</code>. При проектировании тестов применён паттерн <code>PageObject</code>.

В качестве фреймворка для запуска тестов используется <code>Junit5</code>, а в качестве сборщика проекта - <code>Gradle</code>.

Произведена настройка CI в <code>Jenkins</code>, при запуске прогонов из которого тесты выполняются в удалённом браузере в <code>Selenoid</code>.

По результатам каждого тестового прогона создаётся <code>Allure</code> отчёт для визуализации результатов прогона.

Для тест-менеджмента настроена интеграция с <code>Allure TestOps</code>, которая, в свою очередь, интегрирована с таск-трекером <code>Jira</code>

После прогона тестов <code>Telegram</code> бот присылает сообщение с информацией о прошедшем прогоне

<a id="cases"></a>

## Тестовое покрытие:
### Добавление блюд в корзину
🔎 Проверка добавления простого блюда в корзину из модального окна карточки блюда

🔎 Проверка добавления простого блюда в корзину из карточки блюда на главной странице

🔎 Проверка добавления пиццы с дефолтными модификаторами в корзину

🔎 Проверка добавления пиццы с измененными модификаторами в корзину

🔎 Проверка добавления комбо с дефолтными блюдами в корзину

🔎 Проверка добавления комбо с измененными блюдами в корзину


<a id="localrun"></a>

## Локальный запуск тестов
Для локального запуска тестов из IDE или из терминала необходимо выполнить следующую команду

```
gradle clean test -Denv=local
```

<a id="remoterun"></a>

## Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/C27-przh17-WebTests_unit23/)
Для запуска тестов в Jenkins нужно нажать на кнопку Build Now в соответствующей сборке

<p align="center">
<img src="images/screenshots/jenkins-screen.png">
</p>

<a id="report"></a>

## [Allure отчёт](https://jenkins.autotests.cloud/job/C27-przh17-WebTests_unit23/allure/)
### Графики

<p align="center">
<img src="images/screenshots/allureReports-graphs.png">
</p>

### Тесты

<p align="center">
<img src="images/screenshots/allureReports-tests.png">
</p>

<a id="testops"></a>

## Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/4381/test-cases?treeId=0)

<p align="center">
<img src="images/screenshots/allureTestops.png">
</p>

<a id="jira"></a>

## Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-1332)

<p align="center">
<img src="images/screenshots/jira-screen.png">
</p>

<a id="telegram"></a>

## Уведомление в Telegram

По результатам каждого прогона тестов в Jenkins отправляется сообщение в Telegram. Сообщение содержит информацию о прогоне, а также диаграмму со статистикой прохождения тестов.

<p align="center">
<img src="images/screenshots/telegram-screen.png" width="400">
</p>

<a id="video"></a>

## Видео с примером запуска тестов в Selenoid
В Allure отчёте к каждому тесту прикладываются скриншот с последнего шага, и видео прохождения теста. Примеры таких видео:

<p align="center">
<img src="images/screenshots/video.gif " width="1000">
</p>