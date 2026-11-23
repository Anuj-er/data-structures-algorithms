<h2><a href="https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests">1606. Find Servers That Handled Most Number of Requests</a></h2>

<p>You have <code>k</code> servers numbered from <code>0</code> to <code>k-1</code> that are being used to handle multiple requests simultaneously. Each server has infinite computational capacity but <strong>cannot handle more than one request at a time</strong>. The requests are assigned to servers according to a specific algorithm:</p>

<ul>
	<li>The <code>i<sup>th</sup></code> (0-indexed) request arrives.</li>
	<li>If all servers are busy, the request is dropped (not handled at all).</li>
	<li>If the <code>(i % k)<sup>th</sup></code> server is available, assign the request to that server.</li>
	<li>Otherwise, assign the request to the next available server (wrapping around the list of servers and starting from 0 if necessary). For example, if the <code>i<sup>th</sup></code> server is busy, try to assign the request to the <code>(i+1)<sup>th</sup></code> server, then the <code>(i+2)<sup>th</sup></code> server, and so on.</li>
</ul>

<p>You are given a <strong>strictly increasing</strong> array <code>arrival</code> of positive integers, where <code>arrival[i]</code> represents the arrival time of the <code>i<sup>th</sup></code> request, and another array <code>load</code>, where <code>load[i]</code> represents the load of the <code>i<sup>th</sup></code> request (the time it takes to complete). Your goal is to find the <strong>busiest server(s)</strong>. A server is considered <strong>busiest</strong> if it handled the most number of requests successfully among all the servers.</p>

<p>Return <em>a list containing the IDs (0-indexed) of the <strong>busiest server(s)</strong></em>. You may return the IDs in any order.</p>

<p>&nbsp;</p>
<p><span class="float-right -my-0.5 ml-2 inline-flex select-none items-center" data-example-lang-controls=""><span role="group" class="inline-flex h-6 items-center rounded-sd-md bg-sd-muted p-0.5" data-example-lang-toggle=""><button type="button" aria-pressed="true" class="h-5 max-w-[10rem] truncate whitespace-nowrap rounded-sd-sm border px-2 text-xs font-normal leading-none border-sd-border bg-sd-background text-sd-foreground">Original</button><button type="button" aria-pressed="false" class="h-5 max-w-[10rem] truncate whitespace-nowrap rounded-sd-sm border px-2 text-xs font-normal leading-none border-transparent text-sd-muted-foreground hover:text-sd-foreground">Java</button></span></span><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/08/load-1.png" style="width: 389px; height: 221px;">
<pre><strong>Input:</strong> k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3] 
<strong>Output:</strong> [1] 
<strong>Explanation:</strong> 
All of the servers start out available.
The first 3 requests are handled by the first 3 servers in order.
Request 3 comes in. Server 0 is busy, so it's assigned to the next available server, which is 1.
Request 4 comes in. It cannot be handled since all servers are busy, so it is dropped.
Servers 0 and 2 handled one request each, while server 1 handled two requests. Hence server 1 is the busiest server.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> 
The first 3 requests are handled by first 3 servers.
Request 3 comes in. It is handled by server 0 since the server is available.
Server 0 handled two requests, while servers 1 and 2 handled one request each. Hence server 0 is the busiest server.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre><strong>Input:</strong> k = 3, arrival = [1,2,3], load = [10,12,11]
<strong>Output:</strong> [0,1,2]
<strong>Explanation:</strong> Each server handles a single request, so they are all considered the busiest.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= arrival.length, load.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arrival.length == load.length</code></li>
	<li><code>1 &lt;= arrival[i], load[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>arrival</code> is <strong>strictly increasing</strong>.</li>
</ul>


---

# 🛍️ Find-Servers-That-Handled-Most-Number-of-Requests | Explained

## Approach 1: **Heap‑Based Simulation with “Encoded” (time, server) Keys**

### Intuition
Imagine a data centre with `k` identical servers arranged in a circle.  
Each incoming request `i` arrives at time `arrival[i]` and needs `load[i]` seconds to finish.  
The request first tries the server whose index equals `i % k`. If that server is busy it walks clockwise until it finds a free one; if none are free it is dropped.

The naïve way would be to keep a boolean array of size `k` and scan forward for every request – `O(k·n)` in the worst case.  
Instead we *encode* two pieces of information – the **finish time** of a request **and** the **server index** – into a single integer and push it onto a **min‑heap**.  
Because the heap always yields the *earliest‑finishing* job, we can:

* release all jobs that have already finished at the current request’s arrival time,
* keep a secondary heap of **free servers** (also encoded) so that the “best” free server (the one with the smallest index ≥ `i % k`, wrapped around) can be retrieved in `O(log k)`.

The encoding `end * k + server` guarantees that the heap orders first by finish time (`end`) and, for ties, by server index – exactly what we need for “release jobs in chronological order”.

### Algorithm Visualized
```mermaid
flowchart TD
    A[Start] --> B{i < n?}
    B -->|yes| C[Get arrival time t = arrival[i]]
    C --> D{busy heap not empty \\and busy[0] < (t+1)*k ?}
    D -->|yes| E[Pop earliest job]
    E --> F{more finished jobs?}
    F -->|yes| G[Push previous job into free heap]
    G --> E
    F -->|no| H[Push last finished job into free heap and get best free server]
    D -->|no| I{free heap non‑empty?}
    I -->|yes| J[Take best free server]
    I -->|no| K[All servers busy → jump i forward]
    K --> L[bisect arrival to next possible i]
    L --> B
    H --> M[Encode new busy entry & push]
    J --> M
    M --> N[record handled server]
    N --> O[i = i+1]
    O --> B
    B -->|no| P[Compute multimode of handled list]
    P --> Q[Return result]
```

### Approach
1. **Edge case** – if the number of requests `n` ≤ `k`, every request gets its own distinct server (`0 … n‑1`). Return that list directly.
2. **Initialisation**  
   * Encode the first `k` requests (one per server) as `busy = (arrival[x] + load[x]) * k + x`.  
   * `heapify(busy)` creates a min‑heap sorted by *finish time* then *server id*.  
   * `free` is an empty min‑heap that will hold servers that have become idle.  
   * `handled` records every server that successfully handled a request; it starts with the first `k` servers.
3. **Main loop** – iterate `i` from `k` to `n‑1` (the remaining requests). For each request:
   * `t = arrival[i]` – current arrival time.
   * Compute `done = (t + 1) * k`. Any entry in `busy` with value `< done` has a finish time ≤ `t` (because `(finish * k + server) < (t+1) * k` ⇔ `finish ≤ t`). Those jobs are finished **before** the current request arrives.
   * **Release finished jobs**:  
        * If the heap top satisfies the condition, pop it (`job`).  
        * While more jobs satisfy the condition, push the previously popped one into `free` (its server becomes available) and pop the next.  
        * After the loop, the *last* popped job is still needed for the current request; we push it back into `free` while simultaneously extracting the **best** free server (`x`) using `heappushpop`. The expression `i + (job - i) % k` re‑encodes the server index relative to the current request, preserving the clockwise‑search rule.
   * **If no finished jobs** but `free` already contains servers (because some finished earlier), simply pop the smallest encoded value and take its server (`% k`).
   * **If both `busy` and `free` are empty** (all servers are busy), we cannot serve request `i`. Instead of scanning forward one‑by‑one, we *jump* `i` to the index of the first request whose arrival time is **≥** the finish time of the earliest busy job. This is done with `bisect_left(arrival, busy[0] // k, i + 1)`.
   * Once a server `x` is chosen, push the new busy entry `(t + load[i]) * k + x` onto `busy`, record `x` in `handled`, and advance `i`.
4. After the loop, `handled` contains the server index for every successfully processed request.  
   The problem asks for the *most‑used* servers, i.e. the mode(s) of this list. `multimode(handled)` (Python 3.11+ `statistics.multimode`) returns all values that appear the maximum number of times.
5. Return that list.

### Detailed Code Analysis
```python
class Solution:
    def busiestServers(self, k: int, arrival: list[int], load: list[int]) -> list[int]:
        n = len(arrival)
```
* `n` – total number of incoming requests.

```python
        if n <= k:  # every request finds its own server free
            return list(range(n))
```
* When requests are fewer than servers, each gets a distinct server `0 … n‑1`. Early exit.

```python
        busy = [(t + d) * k + x for x, t, d in zip(range(k), arrival, load)]  # first lap: request x takes server x
```
* For the first `k` requests we *force* server `x` to handle request `x`.  
* `(t + d)` is the finish time. Multiplying by `k` and adding `x` creates a single integer that sorts by finish time, then by server id.

```python
        heapify(busy)  # entries: end * k + server
```
* Turn `busy` into a min‑heap. The smallest value corresponds to the job that will finish the soonest.

```python
        free, handled = [], list(range(k))
```
* `free` – min‑heap of **encoded free servers** (same encoding as above). Initially empty because the first `k` servers are busy.  
* `handled` – list of servers that have already processed a request; seeded with the first `k` servers.

```python
        i = k
        while i < n:
            t = arrival[i]
```
* Start processing from request `k` (the first request *after* the initial batch). `t` is its arrival time.

```python
            done = (t + 1) * k  # entries below this have finished by time t
```
* Any entry `v` in `busy` where `v < done` satisfies `v // k <= t`. Hence those jobs are finished **no later than** `t`.

```python
            if busy and busy[0] < done:
                job = heappop(busy)
```
* If the earliest finishing job is already done, pop it to start releasing servers.

```python
                while busy and busy[0] < done:  # release every finished job but the last
                    heappush(free, i + (job - i) % k)
                    job = heappop(busy)
```
* Continue popping while the next job is also finished.  
* For each popped job *except the last* we push its **encoded server** into `free`.  
* Encoding: `i + (job - i) % k`  
  * `job - i` isolates the original server index (`job % k`) minus `i`.  
  * `% k` wraps it into `[0, k-1]`.  
  * Adding `i` shifts the base so that the ordering respects the clockwise search rule for the **current** request `i`.

```python
                x = heappushpop(free, i + (job - i) % k) % k  # the last one goes in, the best comes out: one sift
```
* The *last* finished job is also pushed into `free`, but we immediately need the **best** free server for request `i`.  
* `heappushpop` does both in a single `O(log k)` operation: insert the last server, then pop the smallest encoded value, which corresponds to the server with the smallest index ≥ `i % k` (wrapping).  
* `% k` extracts the raw server id.

```python
            elif free:
                x = heappop(free) % k
```
* No jobs finished right now, but we already have idle servers from earlier releases. Pop the smallest encoded server.

```python
            else:  # all busy: jump to the first request after the earliest finish
                i = bisect_left(arrival, busy[0] // k, i + 1)
                continue
```
* All `k` servers are still busy and none have finished by `t`.  
* `busy[0] // k` is the earliest finish time.  
* `bisect_left` finds the first request whose arrival is **≥** that finish time, starting the search after the current `i`.  
* `continue` restarts the loop with the new `i`, effectively skipping all requests that would be dropped.

```python
            heappush(busy, (t + load[i]) * k + x)
            handled.append(x)
            i += 1
```
* Encode the newly started job (`finish = t + load[i]`, server `x`) and push it onto `busy`.  
* Record that server `x` handled request `i`.  
* Move to the next request.

```python
        return multimode(handled)
```
* `multimode` returns **all** server indices that appear the most often in `handled`. Those are the busiest servers.

### Code
```python
class Solution:
    def busiestServers(self, k: int, arrival: list[int], load: list[int]) -> list[int]:
        n = len(arrival)
        if n <= k:  # every request finds its own server free
            return list(range(n))

        busy = [(t + d) * k + x for x, t, d in zip(range(k), arrival, load)]  # first lap: request x takes server x
        heapify(busy)  # entries: end * k + server
        free, handled = [], list(range(k))

        i = k
        while i < n:
            t = arrival[i]
            done = (t + 1) * k  # entries below this have finished by time t
            if busy and busy[0] < done:
                job = heappop(busy)
                while busy and busy[0] < done:  # release every finished job but the last
                    heappush(free, i + (job - i) % k)
                    job = heappop(busy)
                x = heappushpop(free, i + (job - i) % k) % k  # the last one goes in, the best comes out: one sift
            elif free:
                x = heappop(free) % k
            else:  # all busy: jump to the first request after the earliest finish
                i = bisect_left(arrival, busy[0] // k, i + 1)
                continue
            heappush(busy, (t + load[i]) * k + x)
            handled.append(x)
            i += 1

        return multimode(handled)
```

### Complexity
| Component | Cost |
|-----------|------|
| **Initial encoding & heapify** | `O(k)` time, `O(k)` space |
| **Main loop** | Each request is processed **once**. Inside the loop: <br>• Each job is pushed to `busy` once and popped once → `O(log k)` per request.<br>• Each server can be pushed/popped from `free` at most once per request it becomes idle → also `O(log k)`. <br>Overall **`O(n log k)`** time. |
| **Jump optimization** (`bisect_left`) | Binary search on the `arrival` array → `O(log n)` per jump, but jumps happen at most `k` times (once per distinct finish), so still bounded by `O(n log k)`. |
| **Multimode** | `O(n)` time to count frequencies (Python’s implementation). |
| **Total Time** | **`O(n log k)`** (dominant term). |
| **Extra Space** | `busy` heap ≤ `k` elements, `free` heap ≤ `k` elements, `handled` list of size ≤ `n`. → **`O(k + n)`**, which is `O(n)` in the worst case. |

## 