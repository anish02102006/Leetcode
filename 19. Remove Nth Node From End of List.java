class Solution {
    int cur = 0;
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        head.next = removeNthFromEnd(head.next, n);
        return ++this.cur == n ? head.next : head; 
    }
}
