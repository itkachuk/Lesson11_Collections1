package com.lohika.itkachuk.javatc.lesson11;

import java.util.*;

public class MyArrayList<E> extends AbstractList<E> implements List<E> {
	
	private E[] elementData;
	private int size;
	
	public MyArrayList(int capacity) {
		elementData = (E[]) new Object[capacity];
	}

	public MyArrayList() {
		this(10);
	}

	private void ensureCapacity() {
		if (elementData.length == size) { // array is full - need to extend
			E[] newElementData = (E[]) new Object[size * 2];
			System.arraycopy(elementData, 0, newElementData, 0, size);
			elementData = newElementData;
		}		
	}

    private void trimToSize() {
        if (size < elementData.length) {
            Arrays.copyOf(elementData, size);
        }
    }
	
	private void validateIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("array size: " + size + ", index: " + index);
		}
	}

	@Override
	public boolean add(E e) {
		ensureCapacity();
		elementData[size++] = e;
		return true;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		validateIndex(index);
		ensureCapacity();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
        int oldSize = size;
		Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            this.add((E)iterator.next());
        }
		if (size != oldSize) return true;
        else return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
        int oldSize = size;
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            this.add(index++, (E)iterator.next());
        }
        if (size != oldSize) return true;
        else return false;
	}

	@Override
	public void clear() {
		for (int i=0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		for (int i=0; i < size; i++) {
			if (elementData[i].equals(o)) return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
		while (iterator.hasNext()) {
            if (!this.contains(iterator.next())) return false;
        }
		return true;
	}

	@Override
	public E get(int index) {
		validateIndex(index);
		return elementData[index];
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (elementData[i].equals(o)) return i;
		}
		return -1;
	}

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0 ; i--) {
            if (elementData[i].equals(o)) return i;
        }
        return -1;
    }

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

    // Iterator methods implemented in AbstractList class
    /*
    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    } */

	@Override
	public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) {
                System.arraycopy(elementData, i+1, elementData, i, size-i-1);
                elementData[size] = null;
                size--;
                return true;
            }
        }
		return false;
	}

	@Override
	public E remove(int index) {
		validateIndex(index);
        E oldElement = elementData[index];
        System.arraycopy(elementData, index+1, elementData, index, size-index-1);
        elementData[size] = null;
        size--;
		return oldElement;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
        int oldSize = size;
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            this.remove(iterator.next());
        }
        if (size != oldSize) return true;
        else return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
        int oldSize = size;
        Iterator<?> iterator = c.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (!this.contains(next))
                this.remove(next);
        }
        if (size != oldSize) return true;
        else return false;
	}

	@Override
	public E set(int index, E element) {
		validateIndex(index);
        E oldElement = elementData[index];
        elementData[index] = element;
		return oldElement;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		validateIndex(fromIndex);
        validateIndex(toIndex);
        if (fromIndex >= toIndex) {
            throw new IllegalArgumentException("fromIndex should be less than toIndex");
        }

        int newSubListSize = toIndex - fromIndex;
        E[] subArray = (E[]) new Object[newSubListSize];
        System.arraycopy(elementData, fromIndex, subArray, 0, newSubListSize);
        return Arrays.asList(subArray);
	}

	@Override
	public Object[] toArray() {
		trimToSize();
        return elementData;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
        throw new UnsupportedOperationException("Method not implemented");
		//return null;
	}

}
