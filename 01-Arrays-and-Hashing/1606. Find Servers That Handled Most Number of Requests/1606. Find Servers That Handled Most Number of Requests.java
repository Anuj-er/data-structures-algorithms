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