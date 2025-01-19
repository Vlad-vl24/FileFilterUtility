***
# Утилита фильтрации содержимого файлов</h2>

### Описание

Приложение фильтрует содержимое файлов по типу данных и сохраняет результаты в отдельные файлы. Также предоставляет крактую и полную статистику по каждому типу данных.

### Возможности

- Фильтрация строк текста по типу данных (`Integer`, `Float`, `String`).
- Сохранение результатов в отдельные файлы (`integers.txt`, `floats.txt`, `strings.txt`).
- Краткая и полная статистика по каждому типу данных.
- Режим добавления данных в существующие файлы.
- Настройка через параметры командной строки.

### Используемый стек:
- Maven 4.0.0
- JDK 17.0.4
- Apache Commons CLI 1.9.0 [ссылка на репозиторий Maven](https://mvnrepository.com/artifact/commons-cli/commons-cli/1.9.0)

### Структура:
* class Main: Точка входа в приложение.
* class FileContentFiltering: логика фильтрации и записи данных в файлы.
* class Stats: логика подсчета статистики.
* class Parser: логика обработки аргументов.

### Параметры командной строки
* -o - путь к директории для результирующих данных
* -p - префикс для имен результирующих файлов
* -a - режим добавления данных в результирующие файлы
* -s - вывод краткой статистики
* -f - вывод полной статистики

### Инструкция по запуску
1. Клонируйте репозиторий проекта.
2. Убедитесь что система сборка Maven настроена и готова к работе. ``mvn -v``
3. Соберите проект и загрузите все необходимые зависимости: `mvn clean install`
4. Запустите приложение из командной строки, используя следующий формат:
```
java -jar FileFilter.jar -f -o output -p sample- test1.txt test2.txt test3.txt
```
Здесь запускается FileFilter.jar со следующими параметрами:
* -f - определяет вывод полной статистики
* -o - задаёт путь для сохранения конечных файлов
* -p - задаёт префикс имен для конечных файлов

В конце указываются имена текстовых файлов, содержимое которых должно быть отфильтровано. 
Для корректной работы, текстовые файлы должны находится рядом с исполняемых файлом jar.

