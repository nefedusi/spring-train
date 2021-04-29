Эксперименты с индексами БД DB indexes
СУБД: H2

Сначала операции без индексов, потом с индексами:
getDataWithTwoMethods called, size = 10000
insert time without index = 31380, insert time with index = 31378
search time without index = 6104, search time with index = 1280

В обратном порядке (сначала операции с индексами, потом без индексов):
getDataWithTwoMethods called, size = 10000
insert time without index = 28517, insert time with index = 28611
search time without index = 6222, search time with index = 1396


