# IslandSimulation
# Module 2 Final Project Task JavaRush University

**Module 2. Java Core**  
**Level 20, Lecture 0**

Your task is to program a model of an island with variable parameters that consists of an array of locations (for example, 100x20 cells). The locations will be populated with plants and animals. Animals can:
- Eat plants and/or other animals (if suitable food is present in their location),
- Move (to adjacent locations),
- Reproduce (if a mating partner is available in the location),
- Die from starvation or be eaten.

### Object-Oriented Programming (OOP)
During the creation of various animals, it is essential to use OOP extensively: all types will derive from a single abstract class, `Animal`, which will contain behavior common to all animals. If specific animal types have unique feeding, reproduction, or movement behaviors, etc., it will be necessary to override the methods of the `Animal` class.

### What needs to be done:
1. Create a class hierarchy:
    - **Predators**: Wolf, Boa, Fox, Bear, Eagle
    - **Herbivores**: Horse, Deer, Rabbit, Mouse, Goat, Sheep, Boar, Buffalo, Duck, Goose
    - **Plants**

In the table below, the likelihood of an animal eating "food" is provided, assuming they are on the same cell. Let's look at the situation "wolf eats rabbit". In the table, the number is 60. This means that the wolf can eat the rabbit if they are on the same cell with a 60% likelihood. Multi-threaded random should be utilized.
### Food Chain Probability Matrix

| Predator/Prey | Wolf | Boa | Fox | Bear | Eagle | Horse | Deer | Rabbit | Mouse | Goat | Sheep | Boar | Buffalo | Duck | Goose | Plants |
|---------------|------|-----|-----|------|-------|-------|------|--------|-------|------|-------|------|---------|------|-------|--------|
| Wolf          | -    | 0   | 0   | 0    | 0     | 10    | 15   | 60     | 80    | 60   | 70    | 15    | 10     | 40   | 0     | 0      |
| Snake         | 0    | -   | 15  | 0    | 0     | 0     | 0    | 20     | 40    | 0    | 0     | 0     | 0      | 10   | 0     | 0      |
| Fox           | 0    | 0   | -   | 0    | 0     | 0     | 0    | 70     | 90    | 0    | 0     | 0     | 0      | 60   | 40    | 0      |
| Bear          | 0    | 80  | 0   | -    | 0     | 40    | 80   | 80     | 90    | 70   | 70    | 50    | 20     | 10   | 0     | 0      |
| Eagle         | 0    | 0   | 10  | 0    | -     | 0     | 0    | 90     | 90    | 0    | 0     | 0     | 0      | 80   | 0     | 0      |
| Horse         | 0    | 0   | 0   | 0    | 0     | -     | 0    | 0      | 0     | 0    | 0     | 0     | 0      | 0    | 0     | 100    |
| Deer          | 0    | 0   | 0   | 0    | 0     | 0     | -    | 0      | 0     | 0    | 0     | 0     | 0      | 0    | 0     | 100    |
| Rabbit        | 0    | 0   | 0   | 0    | 0     | 0     | 0    | -      | 0     | 0    | 0     | 0     | 0      | 0    | 0     | 100    |
| Mouse         | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | -     | 0    | 0     | 0     | 0      | 0    | 90    | 100    |
| Goat          | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | -    | 0     | 0     | 0      | 0    | 0     | 100    |
| Sheep         | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | -     | 0     | 0      | 0    | 0     | 100    |
| Boar          | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | -     | 0      | 0    | 90    | 100    |
| Buffalo       | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | 0     | -      | 0    | 0     | 100    |
| Duck          | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | 0     | 0      | -    | 90    | 100    |
| Caterpillar   | 0    | 0   | 0   | 0    | 0     | 0     | 0    | 0      | 0     | 0    | 0     | 0     | 0      | 0    | -     | 100    |
| Plants        | 0    | 0   | 0   | 0    | 0     | 100   | 100  | 100    | 100   | 100  | 100   | 100   | 100    | 100  | 100   | -      |
|---------------|------|-----|-----|------|-------|-------|------|--------|-------|------|-------|------|---------|------|-------|--------|

### Animal Characteristics

| Animal      | Weight (kg) | Max Animals per Cell | Max Movement (Cells/Move) | Food Required for Fullness (kg) |
|-------------|-------------|----------------------|---------------------------|---------------------------------|
| Wolf        | 50          | 30                   | 3                         | 8                               |
| Snake       | 15          | 30                   | 1                         | 3                               |
| Fox         | 8           | 30                   | 2                         | 2                               |
| Bear        | 500         | 5                    | 2                         | 80                              |
| Eagle       | 6           | 20                   | 3                         | 1                               |
| Horse       | 400         | 20                   | 4                         | 60                              |
| Deer        | 300         | 20                   | 4                         | 50                              |
| Rabbit      | 2           | 150                  | 2                         | 0.45                            |
| Mouse       | 0.05        | 500                  | 1                         | 0.01                            |
| Goat        | 60          | 140                  | 3                         | 10                              |
| Sheep       | 70          | 140                  | 3                         | 15                              |
| Boar        | 400         | 50                   | 2                         | 50                              |
| Buffalo     | 700         | 10                   | 3                         | 100                             |
| Duck        | 1           | 200                  | 4                         | 0.15                            |
| Caterpillar | 0.01        | 1000                 | 0                         | 0                               |
| Plants      | 1           | 200                  | N/A                       | N/A                             |
|-------------|-------------|----------------------|---------------------------|---------------------------------|

2. Each animal should have the methods: eat, reproduce, choose a direction of movement.

3. In the herbivore and predator classes, you can implement the eat method. However, consider that there's a herbivorous duck that eats a goose.

4. In specific classes for each species, you can refine all methods according to the peculiarities of the animal.

5. You must create at least 10 species of herbivores and another 5 species of predators (described in point 1).

### Multithreading
Sure, you can write the whole program in a single thread using only loops. But we need to reinforce multithreading in practice, so you definitely need to use threads and thread pools. One scheduled pool is for scheduled tasks like plant growth, the animal life cycle, and system statistics output tasks. Optionally, you can output statistics in one task with plants or animals. Inside the animal lifecycle task, you can use another pool of threads - a regular one. Which tasks to assign to this pool is up to you.

### Mandatory task components:
- Animal hierarchy (OOP)
- Animal behavior
- Multithreading
- Island state statistics for each cycle (in the console)

### Optional task components:
- Centralize parameters for easy simulation control.
- Graphics instead of console statistics. This can be pseudographics in the console or JavaFX, Swing, etc.
- Add other factors that can influence the simulation:
    - More types of animals
    - Different plant species
    - Custom behavior for groups of animals (e.g., wolves hunt and move not alone but in packs)
    - Terrain on the ground, especially a river that prevents some animals from moving

### Parameters (if you decide to do them):
To conveniently change various program parameters during its launch (island size, maximum allowable number of plants/animals in one cell, the probability of movement of a certain type of animal, the number of offspring in different species, etc.), you need to move all these parameters somewhere, for example, to a separate class. The following parameters should be adjustable:
- Island size
- Duration of the simulation cycle
- The number of animals of each species at the start of the simulation
- The condition for stopping the simulation (e.g., all animals died)
- The number of offspring for each animal species

### Unicode (if you decide to go with pseudographics):
For displaying animals, you can use Unicode symbols: 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐, 🐺, 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛.

---
### 🇺🇦 Ukrainian version:
---
# Завдання на підсумковий проєкт модуля 2 JavaRush University

**Модуль 2. Java Core**  
**Рівень 20, Лекція 0**

Твоє завдання — запрограмувати модель острова зі змінними параметрами, що складається з масиву локацій (наприклад, 100х20 клітинок). Локації будуть заповнені рослинами та тваринами. Тварини можуть:

- їсти рослини та/або інших тварин (якщо в їхній локації є придатна їжа)
- пересуватися (до сусідніх локацій)
- розмножуватися (якщо в локації наявна пара для них)
- вмирати від голоду чи бути з'їденими

## ООП
Під час створення різноманіття тварин потрібно максимально використовувати ООП: усі види будуть походити від одного абстрактного класу `Animal`, який міститиме поведінку, що є спільною для всіх тварин. Якщо в конкретних видів тварин будуть особливості харчування, розмноження, пересування тощо, для них буде необхідно перевизначити методи класу `Animal`.

## Що потрібно зробити:

1. **Створи ієрархію класів:**
    - Хижак: Вовк, Удав, Лисиця, Ведмідь, Орел
    - Травоїдні: Кінь, Олень, Кролик, Миша, Коза, Вівця, Кабан, Буйвол, Качка, Гусінь
    - Рослини

У таблиці нижче наведено, з якою вірогідністю тварина з'їдає "їжу", якщо вони знаходяться на одній клітинці. Наприклад, "вовк їсть кролика" у таблиці має значення 60%. Це означає, що вовк може з'їсти кролика, якщо вони на одній клітинці, з вірогідністю 60%. При реалізації цього необхідно використовувати багатопотоковий random.

### Матриця ймовірності Харчового Ланцюга

| Хижак/Жертва  | Вовк | Удав | Лисиця | Ведмідь | Орел | Кінь | Олень | Кролик | Миша | Коза | Вівця | Кабан | Буйвол | Качка | Гусінь | Рослини |
|---------------|------|------|--------|---------|------|------|-------|--------|------|------|-------|-------|--------|-------|-------|----------|
| Вовк          | -    | 0    | 0      | 0       | 0    | 10   | 15    | 60     | 80   | 60   | 70    | 15    | 10     | 40    | 0     | 0        |
| Удав          | 0    | -    | 15     | 0       | 0    | 0    | 0     | 20     | 40   | 0    | 0     | 0     | 0      | 10    | 0     | 0        |
| Лисиця        | 0    | 0    | -      | 0       | 0    | 0    | 0     | 70     | 90   | 0    | 0     | 0     | 0      | 60    | 40    | 0        |
| Ведмідь       | 0    | 80   | 0      | -       | 0    | 40   | 80    | 80     | 90   | 70   | 70    | 50    | 20     | 10    | 0     | 0        |
| Орел          | 0    | 0    | 10     | 0       | -    | 0    | 0     | 90     | 90   | 0    | 0     | 0     | 0      | 80    | 0     | 0        |
| Кінь          | 0    | 0    | 0      | 0       | 0    | -    | 0     | 0      | 0    | 0    | 0     | 0     | 0      | 0     | 0     | 100      |
| Олень         | 0    | 0    | 0      | 0       | 0    | 0    | -     | 0      | 0    | 0    | 0     | 0     | 0      | 0     | 0     | 100      |
| Кролик        | 0    | 0    | 0      | 0       | 0    | 0    | 0     | -      | 0    | 0    | 0     | 0     | 0      | 0     | 0     | 100      |
| Миша          | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | -    | 0    | 0     | 0     | 0      | 0     | 90    | 100      |
| Коза          | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | 0    | -    | 0     | 0     | 0      | 0     | 0     | 100      |
| Вівця         | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | 0    | 0    | -     | 0     | 0      | 0     | 0     | 100      |
| Кабан         | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | 0    | 0    | 0     | -     | 0      | 0     | 90    | 100      |
| Буйвол        | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | 0    | 0    | 0     | 0     | -      | 0     | 0     | 100      |
| Качка         | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | 0    | 0    | 0     | 0     | 0      | -     | 90    | 100      |
| Гусінь        | 0    | 0    | 0      | 0       | 0    | 0    | 0     | 0      | 0    | 0    | 0     | 0     | 0      | 0     | -     | 100      |
| Рослини       | 0    | 0    | 0      | 0       | 0    | 100  | 100   | 100    | 100  | 100  | 100   | 100   | 100    | 100   | 100   | -        |
|---------------|------|------|--------|---------|------|------|-------|--------|------|------|-------|-------|--------|-------|-------|----------|

### Характеристики Тварин

| Тварина  | Вага (кг) | Макс. кіл-ть тварин у клітці | Макс. переміщення (клітинок/рух) | Їжа необхідна для ситості (кг) |
|----------|-----------|------------------------------|---------------------------------|---------------------------------|
| Вовк     | 50        | 30                           | 3                               | 8                               |
| Удав     | 15        | 30                           | 1                               | 3                               |
| Лисиця   | 8         | 30                           | 2                               | 2                               |
| Ведмідь  | 500       | 5                            | 2                               | 80                              |
| Орел     | 6         | 20                           | 3                               | 1                               |
| Кінь     | 400       | 20                           | 4                               | 60                              |
| Олень    | 300       | 20                           | 4                               | 50                              |
| Кролик   | 2         | 150                          | 2                               | 0.45                            |
| Миша     | 0.05      | 500                          | 1                               | 0.01                            |
| Коза     | 60        | 140                          | 3                               | 10                              |
| Вівця    | 70        | 140                          | 3                               | 15                              |
| Кабан    | 400       | 50                           | 2                               | 50                              |
| Буйвол   | 700       | 10                           | 3                               | 100                             |
| Качка    | 1         | 200                          | 4                               | 0.15                            |
| Гусінь   | 0.01      | 1000                         | 0                               | 0                               |
| Рослини  | 1         | 200                          | N/A                             | N/A                             |
|----------|-----------|------------------------------|---------------------------------|---------------------------------|

2. У кожної тварини повинні бути методи: поїсти, розмножитися, вибрати напрямок руху.

3. У класах травоїдних та хижаків можна реалізувати метод поїсти. Проте враховуйте, що є травоїдна качка, яка їсть гуску.

4. У конкретних класах для кожного виду можна докладніше розробити всі методи, враховуючи особливості тварини.

5. Потрібно створити мінімум 10 видів травоїдних та ще 5 видів хижаків (описані в п.1).

### Багатопотоковість
Звісно, можна було б написати всю програму у одному потоці, використовуючи тільки цикли. Але нам треба використати багатопотоковість на практиці, тому обов'язково використовуйте потоки та пул потоків. Один запланований пул потрібен для задач, таких як зростання рослин, життєвий цикл тварин та завдання виведення статистики системи. Можливо, ви можете вивести статистику разом із завданням для рослин або тварин. Усередині завдання життєвого циклу тварин ви можете використовувати ще один пул потоків - звичайний. Які завдання передавати цьому пулу на виконання - вирішуйте самі.

### Обов'язкові частини завдання:
- Ієрархія тварин (ООП)
- Поведінка тварин
- Багатопотоковість
- Статистика щодо стану острова на кожному такті (у консолі)

### Необов'язкові частини завдання:
- Перемістіть параметри до окремого місця для зручного керування симуляцією.
- Графіка замість консольної статистики. Може бути як псевдографіка у консолі, так і JavaFX, Swing тощо.
- Додайте інші фактори, які можуть впливати на симуляцію:
    - Більше видів тварин
    - Різні види рослин
    - Особлива поведінка для груп тварин (наприклад, вовки полюють не самостійно, а у зграї)
    - Рельєф на землі, зокрема річка, яка заважає деяким тваринам рухатися
- Параметри (якщо вирішите реалізувати цей пункт):
    - Розмір острова
    - Тривалість одного такту симуляції
    - Кількість тварин кожного виду на початку симуляції
    - Умова для зупинки симуляції (наприклад, всі тварини померли)
    - Кількість дитинчат у кожного виду тварин
- Юнікод (якщо вирішите реалізувати псевдографіку):
    - Для відображення тварин можна використовувати символи юнікоду: 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐, 🐺, 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛.
