Хоменко Максим
В данной программе сперва пользователь выбирает, работать ему с уже созданной симуляцией или создать новую. При работе с симуляциями пользователь может добавлять растений и животных, просматривать их, удалять и редактировать. Данные записываются в файл, каждая симуляция сохраняется отдельно, чтоб пользователь мог запустить на основе любых сохранённых данных. Также есть возможность прогноза, пользователь вводит данные об климатических параметрах и ресурсах, у каждого организма есть необходимые для его жизни данные, далее происходит сравнение и выводы. Также при работе с симуляциями запускается фоновый поток, который каждые 2 минуты вызывает синхронизированный метод. В данном методе организмы взаимодействуют( съедают друг друга) и изменяются климатические условия, которые записываются в лог файл.
