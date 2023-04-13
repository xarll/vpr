package Task9;

import java.util.ArrayList;

public class GraphShower {
    public static void showFuncs(Graph graph){
        for(int i = 0;i<graph.getFuncs().size();i++){
            System.out.println("Функция "+(i+1)+"\tвид: "+graph.getExplanation().getName(i));
        }
    }
    public static void showCoordinateMesh(Graph graph){
        System.out.println("Позиция осей: "+graph.getCoordinateMesh().getPosition());
        for(int i = 0;i<graph.getCoordinateMesh().getAxesNum();i++){
            System.out.println("Обозначение оси: "+graph.getCoordinateMesh().getAxisDesignation(i)+"\tИнтервал оси: "+graph.getCoordinateMesh().getAxisInterval(i));
        }
    }
    public static void showMesh(Graph graph){
        System.out.println("Интервал сетки: "+graph.getMesh().getInterval());
    }
    public static void showExplanation(Graph graph){
        for(int i = 0;i<graph.getFuncs().size();i++){
            System.out.println("Вид функции: "+graph.getExplanation().getName(i)+"\tЦвет графика: "+graph.getExplanation().getColor(i));
        }
    }
}
