/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode();
        ListNode before = root;

        ListNode curr1 = l1;
        ListNode curr2 = l2;

        int x = 0; //10의 자리 숫자를 저장할 변수
    
        while(curr1 != null || curr2 != null || x != 0){

            //현재 노드의 값 가져오기
            int val1 = curr1 == null ? 0 : curr1.val;
            int val2 = curr2 == null ? 0 : curr2.val;
            
            int sum = val1 + val2 + x;            //합산
            
            ListNode node = new ListNode(sum%10); //일의 자리 수를 값으로 가진 노드 생성
            before.next = node;                   //이전 노드의 다음 노드로 연결
            before = node;                        
            
            x = sum/10;                           //합계가 두 자릿수인 경우 1이 대입됨

            //다음 노드를 현재 노드로 설정
            curr1 = curr1 == null ? null : curr1.next;
            curr2 = curr2 == null ? null : curr2.next;

        }
        
         return root.next;
        
    }
}
