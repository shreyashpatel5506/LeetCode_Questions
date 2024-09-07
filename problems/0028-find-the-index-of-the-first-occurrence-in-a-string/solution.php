class Solution {
    function strStr($haystack, $needle) {
        $pos=strpos($haystack,$needle);
        return $pos === false ? -1 : $pos;
    }
}