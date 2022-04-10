package finalProject;

import java.util.ArrayList;

public class TST <Value>{
	private Node <String>root;
	private int size;

	private class Node<String>
	{
		private String val;
		private char c;
		private Node<String> left, mid, right;

	}
	public void put(String key, String val)
	{
		size++;
		root = put(root,key,val,0);
	}
	private Node<String> put(Node<String> x, String key, String val, int d)
	{
		char c = key.charAt(d);
		if(x == null) {
			x = new Node();
			x.c=c;
		}
		if(c<x.c)
		{
			x.left = put(x.left, key, val, d);
		}
		else if(c>x.c)
		{
			x.right = put(x.right,key,val,d);
		}
		else if(d<key.length()-1)
		{
			x.mid = put(x.mid, key, val, d+1);
		}
		else
		{
			x.val = val;
		}
		return x;
	}
	public boolean contains(String key)
	{
		return get(key) != null;
	}
	public String get(String key)
	{
		Node<String> x = get(root, key, 0);
		if (x==null)
		{
			return null;
		}
		return x.val;

	}
	private Node<String> get(Node<String> x, String key, int d)
	{
		if(x == null)
		{
			return null;
		}
		char c = key.charAt(d);
		if(c<x.c)
		{
			return get(x.left, key, d);
		}
		else if (c>x.c)
		{
			return get(x.right, key, d);
		}
		else if(d<key.length()-1)
		{
			return get(x.mid, key, d+1);
		}
		else 
		{
			return x;
		}
	}
	public int size()
	{
		return size;
	}
	public ArrayList<String> checkPrefix(String prefix)
	{
		if(prefix==null)
		{
			ArrayList<String> nullEntry = new ArrayList<>();
			nullEntry.add("This is an empty string");
			return nullEntry;
		}
		ArrayList<String> values = new ArrayList<String>();
		Node<String> x = get(root, prefix, 0);
		if (x == null) 
		{
			return values;
		}
		else if(x.val!=null)
		{
			values.add(x.val);
		}
		collect(x.mid, new StringBuilder(prefix), values);
		if (values.get(0) == null) {
			ArrayList<String> notRecognised = new ArrayList<>();
			notRecognised.add("Sorry, this stop address was not recognised");
			return notRecognised;
		}
		return values;
	}
	public void collect(Node<String> x, StringBuilder prefix, ArrayList<String>values)
	{
		if (x == null)
			return;
		collect(x.left,prefix,values);
		if(x.val!=null)
		{
			values.add(x.val);

		}
		collect(x.mid, prefix.append(x.c), values);
		prefix.deleteCharAt(prefix.length()-1);
		collect(x.right, prefix, values);

	}
}