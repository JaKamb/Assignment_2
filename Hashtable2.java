public class Hashtable2{
	public HashNode[] buckets;
	public int size;
	public int num_buckets;
	public Hashtable2(int num_buckets){
		this.buckets = new HashNode[num_buckets];
		this.num_buckets = num_buckets;
		this.size = 0;
	}
	public Hashtable2(){
		this(10);
	}
	public int getBucket(Object key){
		int hashCode = key.hashCode();
		return Math.abs(hashCode%num_buckets);
	}
	public boolean containsKey(Object key){
		if(size > 0){
			if(buckets[getBucket(key)].equals(null)){
				return false;
			}
			else if(buckets[getBucket(key)].key.equals(key)){
				return true;
			}
		}
		return false;
	}
	public Object get(Object key){
		if(containsKey(key) && buckets[getBucket(key)].key.equals(key)){
				return buckets[getBucket(key)].val;
		}
		else{
			return null;
		}
	}
	public void put(Object key, Object val){
		if(size >= num_buckets){
			doubleBuckets();
			put(key,val);
		}
		else if(containsKey(key) && buckets[getBucket(key)].key.equals(key)){
			buckets[getBucket(key)].changeVal(val);
		}
		else{
			int bucket_id = getBucket(key);
			HashNode node = new HashNode(key, val);
			buckets[bucket_id] = node;
			++size;
		}
	}
	public Object remove(Object key){
		int bucket_id = getBucket(key);
		HashNode node = buckets[bucket_id];
		if(containsKey(key) && buckets[getBucket(key)].key.equals(key)){
			Object value = buckets[getBucket(key)].val;
			buckets[getBucket(key)] = null;
			size--;
			return value;
		}
		return null;
	}
	public void doubleBuckets(){
		HashNode[] temp = new HashNode[num_buckets*2];
		for(int i = 0; i < buckets.length; i++){
			temp[i] = buckets[i];
		}
		buckets = temp;
		num_buckets = num_buckets*2;
	}
	public static void main(String[] args){
		Hashtable2 yo = new Hashtable2();
		Object key = "yikes";
		Object val = "help";
		for(int i = 0; i < yo.num_buckets; i++){
			System.out.println(yo.buckets[i]);
		}
		System.out.println(yo.getBucket(key));
		yo.put(key,val);
		yo.put("yikes","this sucks");
		System.out.println(yo.containsKey(key));
		System.out.println(yo.get(key));
		System.out.println(yo.remove(key));
		System.out.println(Long.MAX_VALUE);
	}

	public class HashNode{
		public Object key;
		public Object val;
		public HashNode(Object key, Object val){
			this.key = key;
			this.val = val;
		}
		public void changeKey(Object key){
			this.key = key;
		}
		public void changeVal(Object val){
			this.val = val;
		}
		public Object getVal(){
			return this.val;
		}
		public Object getKey(){
			return this.key;
		}
	}
}
