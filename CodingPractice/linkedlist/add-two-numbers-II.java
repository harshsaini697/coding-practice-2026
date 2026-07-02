/**
 * Definition for singly-linked list.
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLinkedList(l1);
        l2 = reverseLinkedList(l2);

        ListNode head = null;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            sum = sum % 10;

            ListNode curr = new ListNode(sum);
            curr.next = head;
            head = curr;
        }

        return head;
    }

    private ListNode reverseLinkedList(ListNode head) {
        ListNode last = null;
        while (head != null) {
            // save the next connection
            ListNode temp = head.next;
            head.next = last;
            last = head;
            head = temp;
        }

        return last;
    }
}