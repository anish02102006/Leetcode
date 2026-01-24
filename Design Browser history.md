🧠 Intuition
This problem simulates a browser’s navigation history, just like how Chrome or Firefox manages:

When you visit a new page, all the pages ahead of the current page are cleared (you can’t go forward anymore).
When you back, you move left in the history.
When you forward, you move right in the history.
So the natural representation here is using a dynamic list and a pointer that keeps track of the current index.

🛠️ Approach
We maintain:

ArrayList arr → stores visited URLs in order
int curInd → points to current page
Initialization
Add the homepage and start at index 0:

arr.add(homepage);
curInd = 0;
visit(url)
When visiting a new page:

Remove all history ahead of current index (since they become invalid).
Add the new URL.
Move current index forward.
This mimics real browser behavior.

back(steps)
Just decrease index but ensure we don’t go out of bounds:

curInd = Math.max(curInd - steps, 0);
forward(steps)
Increase index but cap at arr.size() - 1:

curInd = Math.min(curInd + steps, arr.size() - 1);
⏱️ Time Complexity
visit() → O(n) in worst case due to removal
back() and forward() → O(1)
Space → O(n)
✅ Why This Works Well
This is simple, clean, and directly models how browser history works. Easy to understand and maintain.

``Code``

class BrowserHistory {
    int curInd;
    ArrayList<String> arr;

    public BrowserHistory(String homepage) {
        arr = new ArrayList<>();
        arr.add(homepage);
        curInd = 0;
    }
    
    public void visit(String url) {
        int ind = arr.size()-1;
        while(curInd < ind){
            arr.remove(ind);
            ind--;
        }
        arr.add(url);
        curInd++;
    }
    
    public String back(int steps) {
        curInd = Math.max(curInd-steps, 0);
        return arr.get(curInd);
    }
    
    public String forward(int steps) {
        curInd = Math.min(curInd+steps, arr.size()-1);
        return arr.get(curInd);
    }
}


/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
