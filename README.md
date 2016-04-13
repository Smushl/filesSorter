Сортировка файлов по дате создания. Тыкаем в кнопочку, выбирается каталог.
Файлы из него раскидываются в каталоги вида YYYY-MM-DD по дате создания.
Сделал для сортировки фоток
Нужно доделать: сортировать CR2 и JPG по EXIF, и найти в видео метадату

http://juravskiy.ru/?p=1054
https://drewnoakes.com/code/exif/
https://github.com/drewnoakes/metadata-extractor/wiki

Для Raw и Jpg сделал, для видосов пока нет.
В JPG дата может быть в ExifSubIFDDirectory или в ExifIFD0Directory, надо учитывать
и не только в TAG_DATETIME_ORIGINAL, но и в TAG_DATETIME

2016-04-13
Пришел к выводу, что только SubIFD TAG_DATETIME_ORIGINAL может гарантировать дату съемки. Лишнее убрал