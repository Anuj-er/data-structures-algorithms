class Solution {
public:
    struct comp {
        bool operator()(const vector<int>& a, const vector<int>& b) const {
            if (a[0] == b[0]) {
                return a[1] > b[1]; // descending by end eg: [[1,2],[1,4],[3,4]]
            }
            return a[0] < b[0]; // ascending by start
        }
    };

    int removeCoveredIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), comp());

        int count = 0;
        int startPoint = intervals[0][0];
        int endPoint = intervals[0][1];

        for (int i = 1; i < intervals.size(); i++) {
            if (startPoint <= intervals[i][0] && intervals[i][1] <= endPoint) {
                count++;
            } else {
                startPoint = intervals[i][0];
                endPoint = intervals[i][1];
            }
        }

        return intervals.size() - count;
    }
};