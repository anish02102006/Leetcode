Intuition
To achieve O(1) time complexity for both operations, we need to think about what data structures give us constant-time access and updates.

A hash table immediately comes to mind for O(1) lookups - we can store key-value pairs and retrieve them instantly. However, a hash table alone doesn't maintain any ordering information. We need to track which items were used recently and which weren't.

For maintaining order, we could consider using an array or list where the most recently used item is at one end and the least recently used is at the other. But here's the problem: when we access an item in the middle, we'd need to move it to the front, which takes O(n) time in an array.

This is where the doubly linked list becomes crucial. With a doubly linked list:

We can remove a node from anywhere in the list in O(1) time (if we have a reference to the node)
We can add a node to the head or tail in O(1) time
The order of nodes naturally represents the usage order
The key insight is combining these two data structures:

Hash table: Maps keys to node references in the linked list, giving us O(1) access to any node
Doubly linked list: Maintains the usage order, with the head being most recently used and tail being least recently used
When we get or put an existing key, we:

Find the node instantly via the hash table (O(1))
Remove it from its current position in the list (O(1))
Add it to the head of the list (O(1))
When the cache is full and we need to evict, we simply remove the tail node (O(1)).

The dummy head and tail nodes in the implementation are a clever trick to avoid null checks and edge cases when the list is empty or has only one element. They act as sentinels, making the add and remove operations uniform regardless of the list state.


Solution Approach
The implementation uses a hash table combined with a doubly linked list to achieve O(1) operations.

Data Structures:

Node Class: Represents each cache entry with:

key and val: Store the key-value pair
prev and next: Pointers to maintain the doubly linked list
LRUCache Class maintains:

cache: A hash table (dictionary) mapping keys to their corresponding nodes
head and tail: Dummy sentinel nodes to simplify list operations
size: Current number of items in the cache
capacity: Maximum allowed items
Key Helper Methods:

remove_node(node): Removes a node from its current position in the list

node.prev.next = node.next
node.next.prev = node.prev
This bypasses the node by connecting its neighbors directly to each other.

add_to_head(node): Adds a node right after the dummy head

node.next = self.head.next
node.prev = self.head
self.head.next = node
node.next.prev = node
This inserts the node between the head and the first actual node.

Main Operations:

get(key):

Check if key exists in the hash table
If not found, return -1
If found, move the node to the head (marking it as most recently used):
Remove it from current position
Add it to the head
Return the node's value
put(key, value):

If key exists: Update the existing node
Remove it from current position
Update its value
Move it to the head
If key doesn't exist: Create a new node
Create new node with the key-value pair
Add it to the hash table
Add it to the head of the list
Increment size
If capacity exceeded: Evict the LRU item
The LRU item is at tail.prev (the node just before the dummy tail)
Remove it from the hash table
Remove it from the linked list
Decrement size
The dummy head and tail nodes eliminate edge cases:

The list is never truly empty (always has head and tail)
No null checks needed when adding or removing nodes
The actual cache items exist between head and tail
This design ensures all operations maintain O(1) time complexity while keeping track of usage order efficiently.

Ready to land your dream job?
Unlock your dream job with a 5-minute evaluator for a personalized learning plan!
Example Walkthrough
Let's walk through a concrete example with an LRU cache of capacity 2 to see how the data structure works internally.

Initial State:

Cache capacity: 2
Hash table: {} (empty)
Linked list: [head] ↔ [tail] (only dummy nodes)
Operation 1: put(1, 10)

Create new node with key=1, value=10
Add to hash table: {1: node1}
Insert node after head: [head] ↔ [1,10] ↔ [tail]
Size becomes 1
Operation 2: put(2, 20)

Create new node with key=2, value=20
Add to hash table: {1: node1, 2: node2}
Insert node after head: [head] ↔ [2,20] ↔ [1,10] ↔ [tail]
Size becomes 2
Note: Node 2 is now most recently used (closest to head)
Operation 3: get(1) → returns 10

Look up key 1 in hash table - found!
Remove node1 from its current position: [head] ↔ [2,20] ↔ [tail]
Add node1 after head: [head] ↔ [1,10] ↔ [2,20] ↔ [tail]
Return value 10
Note: Node 1 is now most recently used
Operation 4: put(3, 30) (capacity exceeded!)

Size is 2, capacity is 2 → need to evict
LRU item is at tail.prev which is node2 (key=2)
Remove node2 from hash table: {1: node1}
Remove node2 from list: [head] ↔ [1,10] ↔ [tail]
Create new node with key=3, value=30
Add to hash table: {1: node1, 3: node3}
Insert node3 after head: [head] ↔ [3,30] ↔ [1,10] ↔ [tail]
Operation 5: get(2) → returns -1

Look up key 2 in hash table - not found!
Return -1 (key 2 was evicted earlier)
Final State:

Hash table: {1: node1, 3: node3}
Linked list: [head] ↔ [3,30] ↔ [1,10] ↔ [tail]
Most recently used: key 3
Least recently used: key 1
This example demonstrates how:

The hash table provides O(1) lookups
The linked list maintains usage order (head = most recent, tail = least recent)
Every access (get or put) moves items to the head
Eviction always happens at the tail when capacity is exceeded

/**
 * Node class for doubly linked list implementation
 * Each node stores a key-value pair and pointers to previous and next nodes
 */
class Node {
    int key;
    int value;
    Node previous;
    Node next;

    /**
     * Default constructor for sentinel nodes
     */
    Node() {
    }

    /**
     * Constructor with key and value parameters
     * @param key   The key for this node
     * @param value The value associated with the key
     */
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * LRU (Least Recently Used) Cache implementation
 * Uses a HashMap for O(1) lookup and a doubly linked list for O(1) removal/addition
 * Most recently used items are kept at the head, least recently used at the tail
 */
class LRUCache {
    private int currentSize;
    private int maxCapacity;
    private Node dummyHead;  // Sentinel node for head
    private Node dummyTail;  // Sentinel node for tail
    private Map<Integer, Node> cacheMap;  // HashMap for O(1) access to nodes

    /**
     * Initialize the LRU cache with given capacity
     * @param capacity Maximum number of key-value pairs the cache can hold
     */
    public LRUCache(int capacity) {
        this.maxCapacity = capacity;
        this.currentSize = 0;
      
        // Initialize sentinel nodes
        this.dummyHead = new Node();
        this.dummyTail = new Node();
      
        // Connect sentinel nodes
        dummyHead.next = dummyTail;
        dummyTail.previous = dummyHead;
      
        // Initialize the cache map
        this.cacheMap = new HashMap<>();
    }

    /**
     * Get the value associated with the key
     * Move the accessed node to head (mark as most recently used)
     * @param key The key to look up
     * @return The value if key exists, -1 otherwise
     */
    public int get(int key) {
        // Check if key exists in cache
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
      
        // Get the node from cache
        Node targetNode = cacheMap.get(key);
      
        // Move node to head (mark as most recently used)
        removeNodeFromList(targetNode);
        addNodeToHead(targetNode);
      
        return targetNode.value;
    }

    /**
     * Add or update a key-value pair in the cache
     * @param key   The key to insert or update
     * @param value The value to associate with the key
     */
    public void put(int key, int value) {
        // Case 1: Key already exists - update value and move to head
        if (cacheMap.containsKey(key)) {
            Node existingNode = cacheMap.get(key);
          
            // Remove from current position
            removeNodeFromList(existingNode);
          
            // Update value
            existingNode.value = value;
          
            // Move to head (mark as most recently used)
            addNodeToHead(existingNode);
        } 
        // Case 2: New key - create new node and add to cache
        else {
            // Create new node
            Node newNode = new Node(key, value);
          
            // Add to cache map
            cacheMap.put(key, newNode);
          
            // Add to head of linked list
            addNodeToHead(newNode);
          
            // Increment size
            currentSize++;
          
            // Check if capacity exceeded
            if (currentSize > maxCapacity) {
                // Remove least recently used node (from tail)
                Node lruNode = dummyTail.previous;
              
                // Remove from cache map
                cacheMap.remove(lruNode.key);
              
                // Remove from linked list
                removeNodeFromList(lruNode);
              
                // Decrement size
                currentSize--;
            }
        }
    }

    /**
     * Remove a node from its current position in the doubly linked list
     * @param node The node to remove
     */
    private void removeNodeFromList(Node node) {
        // Update previous node's next pointer
        node.previous.next = node.next;
      
        // Update next node's previous pointer
        node.next.previous = node.previous;
    }

    /**
     * Add a node right after the dummy head (mark as most recently used)
     * @param node The node to add at head
     */
    private void addNodeToHead(Node node) {
        // Set node's pointers
        node.next = dummyHead.next;
        node.previous = dummyHead;
      
        // Update dummy head's next node
        dummyHead.next = node;
      
        // Update the previous pointer of what was the first node
        node.next.previous = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
