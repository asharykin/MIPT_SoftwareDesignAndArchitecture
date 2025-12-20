# MIPT Zoo App

## Описание проекта

Консольное приложение для управления зоопарком

## Функциональность

1. Добавление животных и предметов инвентаря
2. Подсчёт количества животных в зоопарке и предметов в инвентаре
3. Расчёт массы пищи в килограммах, которую потребляют все животные в зоопарке за день
4. Вывод списка животных, которые могут быть отправлены в контактный зоопарк

## Применение принципов SOLID

1. Single Responsibility Principle:
   - Каждый класс имеет одну ответственность
   - `AnimalRepository` - взаимодействие с хранилищем животных
   - `VeterenaryClinic` - проверка здоровья животных
   - `MonkeyFactory/RabbitFactory/TigerFactory/WolfFactory` - создание обезьян/кроликов/тигров/волков
   - ...

2. Open/Closed Principle:
   - Иерархия классов животных/фабрик животных открыта для расширения
   - Базовый класс `Animal/AnimalFactory`
   - Подклассы `Predator/PredatorFactory` и `Herbo/HerboFactory`
   - Конкретные реализации (`Monkey/MonkeyFactory`, `Rabbit/RabbitFactory`, `Tiger/TigerFactory`, `Wolf/WolfFactory`)
   - В дальнейшем при добавлении нового вида животных достаточно будет создать новый класс и новую фабрику, существующий код меня будет не нужно  

3. Liskov Substitution Principle:
   - Все подклассы животных и предметов могут использоваться вместо их базовых классов
   - `Thing` и его наследники
   - `Animal` и его наследники
   - Метод `createAnimal()` в `AnimalFactory` возвращает `Animal`, но в наследниках тип возвращаемого значения у каждого свой
   - `Monkey` в `createAnimal()` у `MonkeyFactory`, `Rabbit` у `RabbitFactory` и так далее

4. Interface Segregation Principle:
   - Интерфейсы разделены на специфические части по функциональности
   - `IInventory` - для нумерации животных и предметов инвентаря (`getNumber()`)
   - `IAlive` - для определения массы пищи в килограммах, которое потребляет животное за день (`getFood()`)
   - `IKind` - для определения уровня доброты травоядного животного (`getKindness()`)

5. Dependency Inversion Principle:
   - Зависимости инвертированы через внедрение зависимостей
   - `AnimalRepository`, `VetClinicService` и список `AnimalFactory` (абстрактный тип) в `ZooService`
   - `ThingRepository` и список `ThingFactory` (абстрактный тип) в `InventoryService`
   - `ZooService` и `InventoryService` в `ConsoleApplication`
   - Использование Spring для внедрения зависимостей

## Инструкции по запуску

1. Соберите проект в jar-файл с помощью Maven:

```bash
mvn clean package
```

Для удобства проверки это уже было сделано. Готовый jar находится в папке `target` проета

2. Запустите полученный jar-файл с помощью JVM (Java 17+):

```bash
java -jar target/zoo-app.jar
```