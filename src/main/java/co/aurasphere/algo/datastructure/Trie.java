package co.aurasphere.algo.datastructure;

import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	private TrieNode head = new TrieNode();
	
	public void insert(String word) {
		TrieNode nextNode = head;
		for (char c : word.toCharArray()) {
			Map<Character, TrieNode> childrens = nextNode.getChildren();
			if (childrens.get(c) == null) {
				childrens.put(c, new TrieNode());
				nextNode = childrens.get(c);
			} else {
				nextNode = childrens.get(c);
			}
		}
		nextNode.setWord(true);
	}
	
	public boolean isPresent(String word) {
		TrieNode nextNode = head;
		for (char c : word.toCharArray()) {
			nextNode = nextNode.getChildren().get(c);
			if (nextNode == null) {
				return false;
			}
		}
		return nextNode.isWord;
	}
	
	@Override
	public String toString() {
		return head.toString();
	}
	
	private class TrieNode {
		
		private HashMap<Character, TrieNode> children = new HashMap<>();
		
		private boolean isWord;

		public HashMap<Character, TrieNode> getChildren() {
			return children;
		}

		public void setChildren(HashMap<Character, TrieNode> children) {
			this.children = children;
		}

		public boolean isWord() {
			return isWord;
		}

		public void setWord(boolean isWord) {
			this.isWord = isWord;
		}

		@Override
		public String toString() {
			return children + (isWord ? "*" : "");
		}
		
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("banana");
		trie.insert("banaka");
		trie.insert("porato");
		trie.insert("porridge");

		System.out.println(trie.isPresent("banana"));
		System.out.println(trie.isPresent("bana"));
		
		System.out.println(trie);
	}

}
