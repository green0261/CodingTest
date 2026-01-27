# Add Two Number 문제 풀이

 **문제 링크**  
https://leetcode.com/problems/add-two-numbers

---

##  문제 설명
어떠한 숫자를 한 노드당 한 자리 숫자로 역저장해놓은 두 개의 연결 리스트가 주어졌을 때

각 연결 리스트에 저장된 숫자를 합하여 같은 형식의 연결 리스트로 반환하는 문제

---

## 문제 풀이

제약사항을 보면 연결 리스트는 최대 100개의 노드를 가질 수 있기 때문에
정직하게 전체 숫자를 구해 합산하는 건 불가능하다(오버플로우 발생)

그래서 각 자릿수끼리 합산하여 일의 자리 수는 노드에 저장,
십의 자리 수는 변수 x로 저장하여 다음 노드로 넘기는 방법을 사용했다.

<img width="545" height="467" alt="그림02" src="https://github.com/user-attachments/assets/5e8ef28b-5f3d-4a88-94e9-b68274d45f1e" />



### 1. 반환할 루트 노드와 변수 x 정의
```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(); //루트 노드(더미)
        ListNode before = root; //노드 연결을 위한 이전 노드

        //현재 노드
        ListNode curr1 = l1;
        ListNode curr2 = l2; 

        int x = 0;  //자릿수끼리 합산했을 때 나온 십의 자리 수를 저장할 변수
    
         ...
        
    }
}
```


### 2. 두 리스트의 노드를 자릿수끼리 합산하고 결과값으로 새로운 노드를 생성

```java
while(curr1 != null || curr2 != null || x != 0){ //current 노드 or 변수 x가 존재하면 반복

            //current 노드의 값을 가져옴
            int val1 = curr1 == null ? 0 : curr1.val;
            int val2 = curr2 == null ? 0 : curr2.val;
            
            int sum = val1 + val2 + x;   //두 노드의 값과 이전 합산 결과의 십의 자리수 합산
            x = sum/10;                   //십의 단위 숫자를 x에 저장

            ListNode node = new ListNode(sum%10); //결과값의 일의 자리수를 값으로 가진 노드 생성

            ...

        }
```

### 3. 결과로 반환할 리스트에 새로운 노드를 연결
```java
while(curr1 != null || curr2 != null || x != 0){

           ...

            before.next = node;          //노드를 이전 노드의 다음 순서로 연결
            before = node;               //노드를 새로운 이전 노드로 설정             


            //다음에 합산할 노드를 current 노드로 설정
            curr1 = curr1 == null ? null : curr1.next;
            curr2 = curr2 == null ? null : curr2.next;

}
```

최종 런타임 >>>

<img width="248" height="86" alt="image" src="https://github.com/user-attachments/assets/653be7af-3aa2-4b0f-83f2-dd9363d2480b" />


---
##  새로 배운 점

처음에는 결과를 반환할 때 사용할 root 노드를 null로 설정했었다.
```java
ListNode root = null;
ListNode before = null;
```

그래서 반복문을 돌릴 때마다 root 노드인지 child 노드인지를 여부를 계속 확인해야했다.

```java
ListNode node = new ListNode(sum%10);
  if(root == null){   //루트가 null이면
    root = node;      //새로운 노드를 루트로 설정
  }else{
    before.next = node;  //루트가 null이 아니면 이전 노드에 연결
}
```

그런데 다른 풀이를 보니 root 노드를 더미로 만들어 사용하는 방법이 있었다.

```java
ListNode root = new ListNode(); //루트 노드(더미)
ListNode before = root; //노드 연결을 위한 이전 노드
```
```java
ListNode node = new ListNode(sum%10); //결과값의 일의 자리수를 값으로 가진 노드 생성
before.next = node;          //노드를 이전 노드의 다음 순서로 연결
before = node;               //노드를 새로운 이전 노드로 설정   
```

이러한 방식을 사용하니 모든 노드를 동일하게 처리할 수 있어 코드의 안정성과 가독성을 높일 수 있었다.

앞으로는 노드를 다룰 때 더미 노드를 활용해야겠다.










