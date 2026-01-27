# Two Sum 문제 풀이

 **문제 링크**  
https://leetcode.com/problems/two-sum

---

##  문제 설명
`int[] nums`와 `int target`이 주어질 때  합이 target이 되는 두 숫자의 인덱스를 반환하는 문제

---

## 문제 풀이

### 1. 선형 탐색
가장 간단한 방법으로는 두 개의 반복문을 이용한 선형 탐색이 있다.

```java
        for(int i = 0 ; i < nums.length ; i++){ //인덱스 i
            for(int j = i + 1 ; j < nums.length ; j++){ //인덱스 j(i+1)
                if(nums[i]+nums[j] == target){ //조건에 맞는 요소를 찾아 리턴 배열에 담는다
                    array[0] = i;
                    array[1] = j;
                }
            }
        }
```

하지만 이 방법은 배열의 모든 요소에 하나하나 접근하기 때문에 효율성이 많이 떨어진다.

시간복잡도를 줄이기 위해 배열을 정렬하여 이진 탐색하는 방법도 생각해봤지만 그렇게 되면 값의 인덱스가 달라져 이 문제와는 적합하지 않았다.

위 두 가지를 제외하고 가능한 다른 방법들을 생각해보다가 최종적으로 hashMap을 이용해보기로 했다.

---

### 2. hashMap을 이용한 풀이
 

#### 1. `int[]nums`의 요소를 키로, 요소의 인덱스를 값으로 가지는 HashMap을 생성한다.

* 요소값이 중복될 수 있기 때문에 map의 값 타입은 ArrayList으로 설정

```java
HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
```

#### 2. `int[] nums`의 값과 인덱스를 map에 저장한다.
```java

        for(int i = 0 ; i < nums.length ; i++){

            int num = nums[i];
            ArrayList<Integer> idxList = map.getOrDefault(num,new ArrayList<Integer>());
            idxList.add(i); //인덱스를 map의 값으로 저장
            map.put(num,idxList); // key:num, value:index
    
        }

```
이 과정을 거치면 이러한 구조로 바뀌게 된다.
<img width="740" height="412" alt="그림01" src="https://github.com/user-attachments/assets/44c2270f-986a-468f-8c98-2772199fc58d" />


#### 3. 탐색할 y 값 구하기
int[] nums의 값 하나를 x로 두고, x와 합했을 때 target이 되는 y값을 구한다

```java
for(int i = 0 ; i < nums.length ; i++){
    int x = nums[i];
    int y = target - x;
...
}
```


#### 4.map에서 y를 찾아 배열에 담아 반환한다.
```java
for(int i = 0 ; i < nums.length ; i++){

            ...

            if(!map.containsKey(y)) continue; // y가 존재하지 않으면 continue
            
            ArrayList<Integer> idxList = map.get(y);
            array[0] = i;
            int yIdx = idxList.get(0); //y의 인덱스

            if(i == yIdx){            //x == y인 경우
                if(idxList.size() > 1){  //동일 값이 2개인 경우
                    array[1] = idxList.get(1); //두 번째로 저장된 인덱스 값 저장
                    break;
                }else{                
                    continue;      //같은 인덱스의 값이므로 continue
                }
                
            }
            
            array[1] = idxList.get(0); //x != y인 경우 첫 번째 인덱스 값 저장
            break;
        }
        ...
        return array;
```

최종 런타임 >>>

<img width="241" height="86" alt="image" src="https://github.com/user-attachments/assets/a49205f3-3bd3-4ec0-9654-1a7392be928a" />


---
## 참고할만한 풀이

 값의 저장과 탐색을 하나의 반복문에서 처리하는 방식.
 
 나처럼 저장과 탐색을 두 개의 반복문으로 나눠서 진행하는 것보다 이 방법이 코드도 간결하고 더 효율적인 방식인 것 같다.

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(int i=0;i<n;i++){
            
            int t=target-nums[i];
            if(mp.containsKey(t)){
                return new int[]{i,mp.get(t)};
            }
            mp.put(nums[i],i);
        }
        return null;
    }
}
```









