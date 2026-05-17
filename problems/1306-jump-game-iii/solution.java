class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (arr[curr] == 0) {
                return true;
            }
            int forward = curr + arr[curr];
            if (forward < n && !visited[forward]) {
                visited[forward] = true;
                queue.add(forward);
            }
            int backward = curr - arr[curr];
            if (backward >= 0 && !visited[backward]) {
                visited[backward] = true;
                queue.add(backward);
            }
        }
        
        return false;
    }
}