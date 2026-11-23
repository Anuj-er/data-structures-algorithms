class Solution:
    def countServers(self, grid: list[list[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        row_masks = [0] * rows
        col_masks = [0] * cols
        for row in range(rows):
            for col in range(cols):
                if grid[row][col]:
                    row_masks[row] |= (1 << col)
                    col_masks[col] |= (1 << row)
        ans = 0
        for row in range(rows):
            for col in range(cols):
                if grid[row][col]:
                    row_connected = row_masks[row] & (row_masks[row] - 1)
                    col_connected = col_masks[col] & (col_masks[col] - 1)
                    if row_connected or col_connected:
                        ans += 1
        return ans