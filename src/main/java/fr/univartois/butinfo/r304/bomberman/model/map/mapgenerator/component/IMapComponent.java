package fr.univartois.butinfo.r304.bomberman.model.map.mapgenerator.component;

public interface IMapComponent {
    void add(IMapComponent component);
    void remove(IMapComponent component);
    IMapComponent getChild(int index);
    void display();
}
