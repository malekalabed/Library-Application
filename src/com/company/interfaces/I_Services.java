package com.company.interfaces;

import java.util.List;

public interface I_Services<T> {

   List<T>getAll();

   void add(T obj);

   void removeObj(String unique_identifier);

   T find(String unique_identifier);

   void editInfo(String propertyToEdit, String id, String value1);


}
