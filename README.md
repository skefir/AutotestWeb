**Autotest demo project**

## Замечания по реализации
Наложенные условия использования Java8, несколько замусоривают хелперы по элементам, при переходе на Java9,
используемые только внутри иерархий элементы можно сделать приватными. В Java8 нет метода takeWhile, пришлось
подключить библиотеку rxJava.

## Конфигурация проекта
Конфигурация проекта выполняется через файл autotest.properties, можно изменить имя и путь к
конфигурационному файлу через системную переменную autotest.config.file
Переменные конфигурации

* **urls.metaUrl** - базовый адрес тестируемой системы по умолчанию - https://www.mql5.com/en/economic-calendar
* **seleinide.browser.name** - имя браузера по умолчанию - chrome
* **seleinide.browser.version** - версия браузера по умолчанию - 101
* **seleinide.browser.useragent** - значение хидера user-agent по умолчанию - Mozilla/5.0 (compatible; Googlebot/2.1; +https://www.google.com/bot.html)
* **seleinide.report.screenshots** - флаг добавления в отчет скриншотов по уммолчанию - true
* **seleinide.report.savePageSource** - флаг добавления в отчет html snapshot'а страницы по умолчанию - false
* **seleinide.report.includeSelenideSteps** - флаг добавления в отчет шагов selenide по умолчанию - false
* **seleinide.timout** - таймаут seleinide по умолчанию -  10000
* **selenide.browser.size** - разрешения браузера по умолчанию - 1920x1080  
* **seleinide.driverPath** - путь к драйверу браузера если пуст будет использоваться автоматическая настройка через DriverManager
* **selenide.remote.url** - путь к серверу удаленного запуска, если пуст запуск происходит локально
* **selenide.browser.headless** - флаг запуска браузера в режиме без UI по умолчанию - true

## Получение отчета
для сборки проекта необходим установленный **gradle**
для генерации отчета в дериктории проекта запустить **gradle clean test** при этом текстовый отчет будет 
сгененрирован в файл **report.log**
для запуска в браузере **html allure** отчета выполнить команду **gradle allureServe** содержимое таблицы истории
доступна в качестве аттачмента в шаге **Получаем содержимое таблицы истории**

