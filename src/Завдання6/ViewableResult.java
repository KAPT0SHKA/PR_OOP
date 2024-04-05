package Завдання6;
/** ConcreteCreator
* (шаблон проектування
* Factory Method)<br>
* Оголошує метод,
* "фабрикуючий" об'єкти
* @author xone
* @version 1.0
* @see Viewable
* @see ViewableResult#getView()
*/
public class ViewableResult implements Viewable {
/** Створює об'єкт, що відображається {@linkplain ViewResult} */
@Override
public View getView() {
return new ViewResult();
}
}