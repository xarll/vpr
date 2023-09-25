# Лаб 1

## Задание 1

Создайте JavaFX-приложение, содержащее в главном окне
панель, кнопку "Сохранить" и группу кнопок с зависимой фиксацией
(ToggleButton), расположенных вертикально. Каждая кнопка должна
содержать какое-то изображение. Найдите или создайте для этого
графические (jpg, png, gif, …) файлы небольшого размера, например,
64x64 пикселя. Если какая-то из кнопок ToggleButton выбрана, щелчок
правой кнопкой мыши внутри панели должен приводить к отображению
в позиции курсора такой же картинки, что и на кнопке. Все изображения,
полученные после щелчков мышью, накапливаются, образуя некоторую
составную картинку. Нажатие на кнопку "Сохранить" должно приводить
к выводу стандартного диалога SaveDialog и к дальнейшему
сохранению составной картинки в указанном пользователем файле. 

<details>
  <summary>Решение</summary>

  ![image](https://github.com/xarll/vpr/assets/76239707/95defb39-b55f-4dff-976c-76cdca700954)

  
  <details>
  <summary>Сцена UI</summary>
      
      public class UiMain {
  
      private final HBox root;
      ImageView imageView;
      Pane imagePane;
      ToggleGroup buttonGroup;
      VBox toggleButtonLayout;
  
      Button saveButton;
  
      UiMain(HBox root) { this.root = root; }
  
      void setup_ui() {
  
          // Панель для отображения изображений
          imagePane = new Pane();
          imagePane.minHeight(200);
          imagePane.minWidth(200);
          imagePane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
          HBox.setHgrow(imagePane, Priority.ALWAYS);
  
          imageView = new ImageView();
          imagePane.getChildren().add(imageView);
          root.getChildren().add(imagePane);
  
          // Управление
          VBox control_layout = new VBox();
          control_layout.setAlignment(Pos.CENTER);
          control_layout.setMinWidth(120);
          control_layout.setSpacing(100);
          root.getChildren().add(control_layout);
  
  
          // Кнопки с img
          this.buttonGroup = new ToggleGroup();
          this.toggleButtonLayout = new VBox();
          this.toggleButtonLayout.setAlignment(Pos.CENTER);
          VBox.setMargin(this.toggleButtonLayout, new javafx.geometry.Insets(10, 10, 10, 10));
          control_layout.getChildren().add(toggleButtonLayout);
  
  
          saveButton = new Button("Сохранить");
          saveButton.setAlignment(Pos.CENTER);
          VBox.setMargin(saveButton, new javafx.geometry.Insets(10, 10, 10, 10));
          control_layout.getChildren().add(saveButton);
  
          }
      }
  </details>


  <details>
    
  <summary>Логика представления</summary>
    
        package main.views.main;
        
        import javafx.embed.swing.SwingFXUtils;
        import javafx.scene.control.ToggleButton;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.image.WritableImage;
        import javafx.scene.input.MouseButton;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.Pane;
        import javafx.stage.FileChooser;
        import main.controllers.MainController;
        import javafx.scene.Scene;
        import javafx.scene.layout.HBox;
        
        import javax.imageio.ImageIO;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        
        
        public class MainView extends Scene {
        
        
            private final UiMain ui;
            private final MainController controller;
            private Image selectedImage;
        
            public MainView(MainController controller) {
                super(new HBox(), 400, 300);
                this.controller = controller;
        
                this.ui = new UiMain((HBox) this.getRoot());
                this.ui.setup_ui();
        
                // Регистрация событий
                this.ui.imagePane.setOnMouseClicked(this::imageViewMouseClicked);
                this.ui.saveButton.setOnMouseClicked(this::saveButtonMouseClicked);
            }
        
            private void imageViewMouseClicked(MouseEvent event) {
                if ((event.getButton() == MouseButton.SECONDARY) && (this.selectedImage != null)) {
                    ImageView newImageView = new ImageView(selectedImage);
        
                    newImageView.setLayoutX(event.getX() - 15);
                    newImageView.setLayoutY(event.getY() - 15);
        
                    ((Pane)this.ui.imageView.getParent()).getChildren().add(newImageView);
                }
            }
        
            private void saveButtonMouseClicked(MouseEvent event) {
                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.png"));
                File file = fc.showSaveDialog(null);
        
                if (file != null) {
                    WritableImage writableImage = new WritableImage((int) this.ui.imagePane.getWidth(), (int) this.ui.imagePane.getHeight());
                    this.ui.imagePane.snapshot(null, writableImage);
        
                    try {
                        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(bufferedImage, "png", file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
        
                }
            }
        
            public void loaded() {
                List<String> images = new ArrayList<String>();
                images.add("file:/home/jkearnsl/IdeaProjects/lab1_1/src/main/resources/close-32.png");
                images.add("file:/home/jkearnsl/IdeaProjects/lab1_1/src/main/resources/logo-64.png");
        
                for (String image_path : images) {
                    Image image = new Image(image_path);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(32);
                    imageView.setFitHeight(32);
        
                    ToggleButton button = new ToggleButton();
                    button.setGraphic(imageView);
                    button.setToggleGroup(this.ui.buttonGroup);
                    this.ui.toggleButtonLayout.getChildren().add(button);
        
                    button.setOnAction(event -> {
                        if (button.isSelected()) {
                            this.selectedImage = image;
                        } else
                            this.selectedImage = null;
                    });
                }
        
            }
        }
  
  </details>
  
</details>

## Задание 2

Создайте JavaFX-приложение в окне которого
отображается один из следующих графических 2D-примитивов,
выбранный случайным образом: отрезок прямой, окружность, эллипс
или прямоугольник. Вокруг примитива должна отображаться
полупрозрачная рамка с пунктирными границами (описанный
прямоугольник). Пользователь должен иметь возможность управлять
расположением на экране и размерами отображенного примитива с
помощью клавиатуры. Клавиши со стрелками должны позволять
перемещать примитив (и его рамку) по окну приложения, клавиши + и –
менять вертикальный (по сути сдвигать нижнюю границу
прямоугольника, в который вписан примитив), а клавиши < и > менять
горизонтальный размер (сдвигать правую границу рамки). 

<details>
  <summary>Решение</summary>

  ![image](https://github.com/xarll/vpr/assets/76239707/272512ae-d037-4e78-b6e3-0ddaaabd3092)


  
  Короткий код:
    
  <details>
    
  <summary>main.java</summary>


  
      package main;
      import javafx.application.Application;
      import javafx.scene.Scene;
      import javafx.scene.canvas.Canvas;
      import javafx.scene.canvas.GraphicsContext;
      import javafx.scene.input.KeyCode;
      import javafx.scene.layout.Pane;
      import javafx.scene.paint.Color;
      import javafx.stage.Stage;
      
      import java.util.Random;
      
      public class App extends Application {
          private enum PrimitiveType { LINE, CIRCLE, ELLIPSE, RECTANGLE }
      
          private PrimitiveType currentPrimitiveType;
          private double x, y, width, height;
          private boolean isDragging = false;
      
          @Override
          public void start(Stage primaryStage) {
              // Генерируем случайный тип примитива
              Random random = new Random();
              int randomType = random.nextInt(4);
              currentPrimitiveType = PrimitiveType.values()[randomType];
      
              // Устанавливаем начальные координаты и размеры примитива
              x = 100;
              y = 100;
              width = 100;
              height = 100;
      
              // Создаем холст для отображения примитива и рамки
              Canvas canvas = new Canvas(800, 600);
              GraphicsContext gc = canvas.getGraphicsContext2D();
      
              // Обновляем холст при изменении размеров окна
              primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> draw(gc));
              primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> draw(gc));
      
              // Реагируем на нажатия клавиш
              canvas.setOnKeyPressed(e -> {
                  if (e.getCode() == KeyCode.RIGHT) {
                      x += 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.LEFT) {
                      x -= 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.UP) {
                      y -= 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.DOWN) {
                      y += 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.NUMPAD8) {
                      height += 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.NUMPAD2) {
                      height -= 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.NUMPAD4) {
                      width -= 10;
                      draw(gc);
                  } else if (e.getCode() == KeyCode.NUMPAD6) {
                      width += 10;
                      draw(gc);
                  }
              });
      
              // Обрабатываем нажатие и отпускание мыши для перемещения
              canvas.setOnMousePressed(e -> {
                  if (e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height) {
                      isDragging = true;
                  }
              });
      
              canvas.setOnMouseReleased(e -> isDragging = false);
      
              canvas.setOnMouseDragged(e -> {
                  if (isDragging) {
                      x = e.getX() - width / 2;
                      y = e.getY() - height / 2;
                      draw(gc);
                  }
              });
      
              // Создаем сцену и устанавливаем ее в primaryStage
              Pane root = new Pane(canvas);
              Scene scene = new Scene(root, 800, 600);
              primaryStage.setScene(scene);
      
              // Устанавливаем фокус на сцену для обработки нажатий клавиш
              canvas.requestFocus();
      
              // Настраиваем primaryStage и показываем его
              primaryStage.setTitle("Resizable Primitive App");
              primaryStage.show();
      
              // Рисуем начальное состояние
              draw(gc);
          }
      
          // Метод для отрисовки примитива и рамки
          private void draw(GraphicsContext gc) {
              gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
      
              switch (currentPrimitiveType) {
                  case LINE:
                      gc.setStroke(Color.BLACK);
                      gc.strokeLine(x, y, x + width, y + height);
                      break;
                  case CIRCLE:
                      gc.setFill(Color.LIGHTBLUE);
                      gc.fillOval(x, y, width, height);
                      break;
                  case ELLIPSE:
                      gc.setFill(Color.LIGHTGREEN);
                      gc.fillOval(x, y, width, height);
                      break;
                  case RECTANGLE:
                      gc.setFill(Color.LIGHTCORAL);
                      gc.fillRect(x, y, width, height);
                      break;
              }
      
              // Рисуем рамку
              gc.setStroke(Color.BLACK);
              gc.setLineDashes(5);
              gc.strokeRect(x, y, width, height);
          }
      
          public static void main(String[] args) {
              launch(args);
          }
      }

      
          
  </details>
  
</details>


## Задание 3

Создайте JavaFX-приложение "Графический редактор".
Главное окно приложения должно выглядеть примерно так:

![image](https://github.com/xarll/vpr/assets/76239707/a2469807-80f3-466f-bb4c-8e6dfc5c88f1)
![image](https://github.com/xarll/vpr/assets/76239707/00e8ae5a-2a81-4a26-8235-6d7bf4d930ab)

Поддерживаемый функционал: в палитре можно выбрать примитив
(группа кнопок ToggleButton), затем, по щелчку правой кнопкой мыши в
свободной области окна (панели отображения рисунка) появляется
изображение выбранного примитива с цветом, толщиной и типом
(сплошная, пунктирная, точечная) границы, указанными в
соответствующих элементах управления правой панели (панели
управления). Для всех примитивов, кроме отрезка прямой используется
указанный на панели управления цвет заливки внутренней области.
Вокруг примитива отображается полупрозрачная рамка,
показывающая, что можно изменять его положение в панели
отображения рисунка и изменять его размеры. Изменение размеров
выполняется как в задании 2. Все изменения можно проводить только с
текущим примитивом, но сохраняются и все добавленные раньше.
Главное меню программы позволяет сохранить картинку в выбранном
пользователем файле (Файл-Сохранить…), выйти из программы (ФайлВыход) и получить справку о том, как использовать программу (ПомощьИнструкция). При сохранении в файл используются размеры картинки
(в пикселях), указанные в панели управления. 

<details>
  <summary>Решение</summary>
  
  [lab1_3.zip](./lab1_3.zip)
  
</details>


## Задание 4

![image](https://github.com/xarll/vpr/assets/76239707/72d8e34f-895c-4ed8-89d1-b043db503820)


Создайте JavaFX-приложение для отображения графиков
функций. Используйте SceneBuilder для
создания внешнего вида приложения и
добавления нужных компонентов, включая
LineChart. Приложение должно позволять
выбирать для отображения функции из
некоторого фиксированного набора. Для
графика функции должны задаваться
толщина линии, диапазон значений
аргумента, шаг (постоянный) изменения
аргумента. Должна быть возможность
одновременного отображения графиков
нескольких функций. Для каждой из таких
функций нужно предусмотреть возможность выбора - должен график отображаться вместе с другими или нет.


<details>
  <summary>Решение</summary>

  
  ![image](https://github.com/xarll/vpr/assets/76239707/d929be17-62e2-4375-a769-3ca93fe454f4)

  [lab1_4.zip](./lab1_4.zip)
  
</details>


## Задание 5
Создайте JavaFX-приложение для отображения данных в
виде таблицы. Используйте компонент TableView. Задайте для таблицы
заголовки, например, "Язык", "Автор" и "Год". 
Поместите в нее следующие данные, например, 

```
Си, Деннис Ритчи, 1972; 
C++, Бьерн Страуструп, 1983; 
Python, Гвидо ван Россум, 1991; 
Java, Джеймс Гослинг, 1995; 
JavaScript, Брендон Айк, 1995; 
C#, Андерс Хейлсберг, 2001; 
Scala, Мартин Одерски, 2003. 
```

Таблица должна позволять добавлять новые данные (строки), изменять имеющиеся, выбирать,
какие столбцы показывать, а какие нет. Щелчок мышкой по заголовку
столбца таблицы должен приводить к тому, что данные в ней выводятся
в отсортированном по значениям этого столбца виде (по возрастанию
или по убыванию). При этом после заголовка этого столбца должен
появится значок в виде стрелки или треугольника, показывающий в
каком направлении выполнена сортировка (по возрастанию или по
убыванию). Повторный щелчок по тому же самому заголовку должен
менять порядок сортировки. 

<details>
  <summary>Решение</summary>
  
  ![image](https://github.com/xarll/vpr/assets/76239707/91698f69-553b-415b-ad9b-df47e87d46e8)

  [lab1_5.zip](./lab1_5.zip)

  
</details>



## Задание 6

Создайте JavaFX-приложение для 3D-визуализации 
молекул. В текстовом файле с расширением xyz содержится 
информация о молекуле в следующем формате:
```
4
C4
C  0.934285  0.231605  -0.978735
C  1.14877   0.131834   0.119626
C  1.98794   0.122377  -0.623269
C  1.43534   1.08196   -0.425853
```
Здесь 4 - количество атомов в молекуле, C4 - произвольная строка с 
описанием молекулы, далее четыре строчки с 
описанием четырех атомов. Описание атома состоит 
из его названия (таблица Менделеева, например, C -
углерод) и трех координат в прямоугольной 
декартовой системе координат. 

![image](https://github.com/xarll/vpr/assets/76239707/48b00e0d-fedc-40ff-8da4-7b82ff03454c)


Пользователь выбирает нужный ему xyz-файл и в окне программы 
отображается молекула. Причем атомы изображаются 
как закрашенные сферы. Ближайшие атомы 
соединяются линией или цилиндром. Цвет и размер сфер разные для 
атомов разного типа, но одинаковые для атомов одного и того же типа. 
Программа должна позволять пользователю изменять отображаемый в 
окне размер молекулы, вращать ее относительно осей координат, 
задавать цвет для разных атомов. Кроме того, должна быть 
возможность сохранять картинку в графическом файле (png, jpg, gif).

Как построить цилиндр по двум 3D-точкам см. [тут](https://fooobar.com/questions/12325117/javafx-3d-transforming-cylinder-to-defined-start-and-end-points)

```java
public Cylinder createConnection(Point3D origin, Point3D target) {
  Point3D yAxis = new Point3D(0, 1, 0); /* цилиндр изначально расположен вертикально (высота вдоль оси OY), направляющий вектор для оси OY - (0, 1, 0) */
  Point3D diff = target.subtract(origin); /* разность векторов target и origin - вектор, направленный от origin к target */
  double height = diff.magnitude(); // расстояние между origin и target - высота цилиндра 
  Point3D mid = target.midpoint(origin); /* точка, лежащая посередине между target и origin - сюда нужно переместить цилиндр (поместить его центр) */
  Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());
  Point3D axisOfRotation = diff.crossProduct(yAxis); /* ось, вокруг которой нужно
повернуть цилиндр - направлена перпендикулярно плоскости, в которой лежат 
пересекающиеся вектора diff (направление от origin к target) и yAxis (текущее направление 
высоты цилиндра), получается как векторное произведение diff и yAxis */
  double angle = Math.acos(diff.normalize().dotProduct(yAxis)); /* угол поворота цилиндра - угол между нормализованным (длина равна 1) вектором diff и вектором yAxis */
  Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
  Cylinder line = new Cylinder(1, height); /* радиус цилиндра 1, нужно заменить на свое значение */
  line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
  return line;
}
```


<details>
  <summary>Решение</summary>
  
  <details>
  <summary>main.java</summary>
  
    ```java
  
    ```
  
  </details>
  
</details>


## Задание 7

![image](https://github.com/xarll/vpr/assets/76239707/ccb845f7-4ebb-4137-a9e3-74412391668f)


 Создайте JavaFX-приложение "Media Player". Приложение 
должно позволять пользователю открыть мультимедиа файл, на-
пример, в формате mp4, по-
казать пользователю его со-
держимое. У пользователя 
должна быть возможность 
запустить, приостановить, 
продолжить воспроизведе-
ние, изменить громкость 
звучания. Должны отобра-
жаться: название отобража-
емого файла, общая дли-
тельность воспроизводимой 
записи, длительность уже 
воспроизведенного участка. 

<details>
  <summary>Решение</summary>
  
  <details>
  <summary>main.java</summary>
  
    ```java
  
    ```
  
  </details>
  
</details>


## Задание 8

Создайте JavaFX-приложение "Test
Animation" показывающее различные эффекты анимации. 
Используйте возможности классов Animation, TimeLine, различных 
классов Transformation и т.п. Набор демонстрируемых эффектов и 
элементы GUI для их запуска/настройки придумайте 
самостоятельно.


<details>
  <summary>Решение</summary>
  
  <details>
  <summary>main.java</summary>
  
    ```java
  
    ```
  
  </details>
  
</details>

