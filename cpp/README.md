# Основные инструменты параллельного программирования на C++


## Запуск проектов
Здесь описан способ запуска любого из проектов.

Предустановка:
```shell
sudo apt install build-essential cmake
sudo apt-get install libboost-all-dev
```

Запуск
```shell
cd <project_dir>
mkdir build && cd build && cmake .. && make
```

## Описание проектов
### simple_threading
Суммирование чисел от 0 до 1e9 с одним потоком и с несколькими потоками 

### interrupt
Возможность останавливать поток

### lock
Накапливание суммы с разных потоков с синхронизацией и без неё