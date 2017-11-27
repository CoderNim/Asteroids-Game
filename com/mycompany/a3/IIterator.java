package com.mycompany.a3;

public interface IIterator {
	public boolean hasNext();
	public GameObject next();
	public GameObject getCurrent();
	public void reduceIndex();
	public void remove(GameObject collideObject);
}
