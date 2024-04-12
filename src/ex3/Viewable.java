package ex3;
/** Creator
* (шаблон проектування
* Factory Method)<br>
* Оголошує метод,
* "фабрикуючий" об'єкти
* @author xone
* @version 1.0
* @see Viewable#getView()
*/
public interface Viewable {
/** Створює об'єкт, що реалізує {@linkplain View} */
public View getView();
}