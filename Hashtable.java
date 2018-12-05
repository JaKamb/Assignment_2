import java.util.ArrayList;
public class Hashtable{
	public ArrayList<HashNode> buckets;
	public int itemSize;
	public int num_buckets;

	public Hashtable(){
		this(15);
	}
	public Hashtable(int num_buckets){
		this.buckets = new ArrayList<HashNode>(num_buckets);
		this.num_buckets = num_buckets;
		this.itemSize = 0;
		for(int i = 0; i < num_buckets; i++){
			buckets.add(null);
		}
	}
	public int hashBucket(Object key){
		return Math.abs(key.hashCode()%num_buckets);
	}
	public boolean containsKey(Object key){
		int bucketIndex = hashBucket(key);
		HashNode node = buckets.get(bucketIndex);
		while(node != null){
			if(node.key.equals(key)){
				return true;
			}
			node = node.next;
		}
		return false;
	}
	public Object get(Object key){
		if(containsKey(key)){
			int bucketIndex = hashBucket(key);
			HashNode node = buckets.get(bucketIndex);
			while(node != null){
				if(node.key.equals(key)){
					return node.value;
				}
				node = node.next;
			}
		}
		return null;
	}
	public void put(Object key, Object val){
		HashNode node = new HashNode(key, val);
		int bucketIndex = hashBucket(key);
		node.next = buckets.get(bucketIndex);
		buckets.set(bucketIndex,node);
		itemSize++;
	}
	public Object remove(Object key){
		if(!containsKey(key)){
			return null;
		}
		int bucketIndex = hashBucket(key);
		HashNode node = buckets.get(bucketIndex);
		HashNode prev = null;
		while(node != null){
			if(node.key.equals(key)){
				break;
			}
			prev = node;
			node = node.next;
		}
		if(node.equals(null)){
			return null;
		}
		--itemSize;
		if(prev == null){
			buckets.set(bucketIndex,node.next);
		}
		else{
			prev.next = node.next;
		}
		return node.value;
	}

	public static void main(String[] args){
		Hashtable yo = new Hashtable(15);
		String key = "Yikes";
		String val = "help";
		String falseKey = "Yookes";
		// for(int i = 0; i < yo.num_buckets; i++){
		// 	System.out.println(yo.buckets.get(i));
		// }
		System.out.println(yo.num_buckets);
		System.out.println(yo.buckets.size());
		System.out.println(key.hashCode());
		System.out.println(yo.hashBucket(key));
		yo.put(key, val);
		System.out.println(yo.containsKey(key));
		System.out.println(yo.containsKey(falseKey));
		System.out.println(yo.get(key));
		for(int i = 0; i < yo.num_buckets; i++){
			System.out.println(yo.buckets.get(i));
		}
		System.out.println(yo.remove(key));
		for(int i = 0; i < yo.num_buckets; i++){
			System.out.println(yo.buckets.get(i));
		}

	}

	public class HashNode{
		public Object key;
		public Object value;
		public HashNode next;
		public HashNode(Object key, Object value){
			this.key = key;
			this.value = value;
			this.next = null;
		}
		public void changeValue(Object value){
			this.value = value;
		}
	}
}