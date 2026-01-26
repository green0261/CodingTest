import java.util.HashMap;
import java.util.ArrayList;
class Solution {
    public int[] twoSum(int[] nums, int target) {

        //결과를 반환할 배열
        int[] array = new int[2];

        //HashMap 생성
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        //배열 값을 map으로 변환(키: num, 값: 해당 num의 idx)
        for(int i = 0 ; i < nums.length ; i++){
            int num = nums[i];
            ArrayList<Integer> idxList = map.getOrDefault(num,new ArrayList<Integer>());
            idxList.add(i);
            map.put(num,idxList);
        }

        for(int i = 0 ; i < nums.length ; i++){
            int x = nums[i];    //기준 값
            int y = target - x; //찾아야 하는 값
            
            ArrayList<Integer> idxList = map.get(y);

            //해당 값이 존재하지 않거나 단일 값인 경우
            if(idxList == null || x == y && idxList.size() == 1) continue;

            array[0] = i;
            if(idxList.size() == 1){
                array[1] = idxList.get(0);
                break;
            }else{
                array[1] = idxList.get(1);
                break;
            }

        }

        return array;
    
    }
}
