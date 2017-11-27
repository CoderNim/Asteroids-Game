package com.mycompany.a3;
import java.util.*;

public class ObjectCollection implements ICollection{
	private ArrayList<GameObject> Collection;
	

	public ObjectCollection(){
		Collection = new ArrayList();
	}

	//add an object into the collection
	public void add(GameObject newObject){
		Collection.add(newObject);
	}
	//remove an object from the collection
	public void remove(GameObject obj){
		Collection.remove(obj);
	}
	//Create a new iterator
	public IIterator getIterator(){
		return new ObjectIterator();
	}
	
	
	//private iterator class
	private class ObjectIterator implements IIterator{
		private int ObjIndex;
		
		public ObjectIterator(){
			ObjIndex = -1;
		}
		//Checks if the iterator is at the end of the array list and if there's anything in the array
		public boolean hasNext(){
			if(Collection.size() < 1){
				return false;
			}
			if(ObjIndex == Collection.size() - 1){
				return false;
			}
			return true;
		}
		public void remove(GameObject obj){
			Collection.remove(obj);
			ObjIndex--;
		}
		//Grabs the next object in the array and increments the index
		public GameObject next(){
			ObjIndex++;
			return(Collection.get(ObjIndex));
		}

		public GameObject getCurrent(){
			return(Collection.get(ObjIndex));
		}
		public void reduceIndex(){
			ObjIndex--;
		}
	}

}

