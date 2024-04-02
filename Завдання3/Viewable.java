package Завдання3;
/** Creator
* (шаблон проектирования
* Factory Method)<br>
* Объявляет метод,
* "фабрикующий" объекты
* @author xone
* @version 1.0
* @see Viewable#getView()
*/
public interface Viewable {
/** Создаёт объект, реализующий {@linkplain View} */
public View getView();
}