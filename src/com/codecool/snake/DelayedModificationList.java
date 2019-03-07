package com.codecool.snake;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class DelayedModificationList<T> {
    private List<T> objects = new LinkedList<>();
    private List<T> newObjects = new LinkedList<>();
    private List<T> oldObjects = new LinkedList<>();


    public void add(T obj) {
        newObjects.add(obj);
    }

    public void addAll(List<T> objs) {
        for(T obj : objs) {
            add(obj);
        }
    }

    void remove(T obj) {
        oldObjects.add(obj);
    }

    public List<T> getList() {
        return Collections.unmodifiableList(objects);
    }

    public boolean isEmpty() {
        return newObjects.isEmpty() && objects.isEmpty();
    }


    public void doPendingModifications() {
        objects.addAll(newObjects);
        newObjects.clear();
        objects.removeAll(oldObjects);
        oldObjects.clear();
    }

    public T getLast() {
        if(!newObjects.isEmpty()) return newObjects.get(newObjects.size()-1);
        if(!objects.isEmpty()) return objects.get(objects.size()-1);
        return null;
    }

    void clear() {
        objects.clear();
        newObjects.clear();
        oldObjects.clear();
    }
}