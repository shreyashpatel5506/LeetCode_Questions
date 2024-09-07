class Solution {
    function search($nums, $target) {
        $r=-1;
        for($i=0;$i< count($nums);$i++){
            if($nums[$i]==$target){
                return $i;
            }
            else{
                $r == -1;
            }
            
        }
        return $r;
    
    }
}
