class Solution {

    /**
     * @param Integer[] $hours
     * @param Integer $target
     * @return Integer
     */
    function numberOfEmployeesWhoMetTarget($hours, $target) {
        $count=0;
        for($i=0;$i<count($hours);$i++){
            if($hours[$i]>=$target){
                $count++;
            }
        }
        return $count;
    }
}