package Завдання3;
/** ConcreteCreator
* (шаблон проектирования
* Factory Method)<br>
* Объявляет метод,
* "фабрикующий" объекты
* @author xone
* @version 1.0
* @see Viewable
* @see ViewableResult#getView()
*/
public class ViewableResult implements Viewable {
/** Создаёт отображаемый объект {@linkplain ViewResult} */
@Override
public View getView() {
return new ViewResult();
}
}