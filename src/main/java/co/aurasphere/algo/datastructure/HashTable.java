package co.aurasphere.algo.datastructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashTable<K, V> {
	
	private int currentSize = 16;
	
	private List<HashTableNode<K, V>>[] table = new List[currentSize];
	
//	 Implement with array using linear probing
//
//	 hash(k, m) - m is size of hash table
//	 add(key, value) - if key already exists, update value
//	 exists(key)
//	 get(key)
//	 remove(key)
	
	public int hash(K k, int m) {
		return (Objects.hash(k) & 0x7FFFFFFF) % m;
	}
	
	public void add(K key, V value) {
		int index = hash(key, currentSize);
		List<HashTableNode<K, V>> collection = table[index];
		if (collection == null) {
			collection = new java.util.LinkedList<HashTableNode<K, V>>();
			table[index] = collection;
		}
		collection.add(new HashTableNode<K, V>(key, value));
	}
	
	public boolean exists(K key) {
		return get(key) != null;
	}
	
	public V get(K key) {
		int index = hash(key, currentSize);
		List<HashTableNode<K, V>> collection = table[index];
		if (collection == null) {
			return null;
		}
		for (HashTableNode<K, V> element : collection) {
			if (element.getKey().equals(key)) {
				return element.getValue();
			}
		}
		return null;
	}
	
	public void remove(K key) {
		int index = hash(key, currentSize);
		List<HashTableNode<K, V>> collection = table[index];
		if (collection == null) {
			return;
		}
		Iterator<HashTableNode<K, V>> iterator = collection.iterator();
		while (iterator.hasNext()) {
			HashTableNode<K, V> nextElement = iterator.next();
			if (nextElement.getKey().equals(key)) {
				iterator.remove();
				return;
			}
		}
	}
	
	@Override
	public String toString() {
		return Arrays.toString(table);
	}
	
	private class HashTableNode<K, V> {
		
		private K key;
		
		private V value;
		
		private HashTableNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}
	
	public static void main(String[] args) {
		HashTable<String, String> map = new HashTable<>();
		map.add("test", "elementOne");
		map.add("banana", "mango");
		map.add("newElement", "ElementValue");
		map.add("hello", "world");
		map.add("turtle", "hare");
		map.add("hi", "earth");
		
		System.out.println(map.get("newElement"));
		System.out.println(map.get("banana"));
		System.out.println(map.get("test"));
		System.out.println(map.get("hello"));
		System.out.println(map);
		
		map.remove("hello");
		System.out.println(map.get("hello"));
		System.out.println(map);
	}

}